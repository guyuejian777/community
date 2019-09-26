package com.dajian.community.controller;

import com.dajian.community.dto.AccessTokenDTO;
import com.dajian.community.dto.GithubUser;
import com.dajian.community.mapper.UserMapper;
import com.dajian.community.model.User;
import com.dajian.community.properties.ClientProperties;
import com.dajian.community.provider.GithubProvider;
import com.dajian.community.service.AuthonrizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.UUID;

@Controller
@EnableConfigurationProperties(ClientProperties.class)
public class AuthorizeController {

    @Autowired
    private ClientProperties clientProperties;

    @Autowired
    private GithubProvider githubProvider;

    @Autowired
    private UserMapper userMapper;


    @GetMapping("/callback")
    public String callback(@RequestParam("code") String code,
                           @RequestParam("state") String state,
                           HttpServletRequest request){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientProperties.getId());
        accessTokenDTO.setClient_secret(clientProperties.getSecret());
        accessTokenDTO.setCode(code);
        accessTokenDTO.setState(state);
        accessTokenDTO.setRedirect_uri(clientProperties.getRedirectUri());

        String accessToken = githubProvider.getAccessToken(accessTokenDTO);


        GithubUser githubUser = githubProvider.getUser(accessToken);
        if (githubUser!=null){
            //登录成功，写入cookie和session
            User user = new User();
            user.setToken(UUID.randomUUID().toString());
            user.setName(githubUser.getName());
            user.setAccountId(githubUser.getId().toString());
            Date createdTime = new Date();
            user.setCreatedTime(createdTime);
            user.setModifiedTime(user.getCreatedTime());
            userMapper.insert(user);
            request.getSession().setAttribute("user", githubUser);
            return "redirect:/";
        }else {
            //登录失败，重新登录
            return "redirect:/";
        }
    }
}
