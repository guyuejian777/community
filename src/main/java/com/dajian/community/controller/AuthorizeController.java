package com.dajian.community.controller;

import com.dajian.community.dto.AccessTokenDTO;
import com.dajian.community.dto.GithubUser;
import com.dajian.community.properties.ClientProperties;
import com.dajian.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@EnableConfigurationProperties(ClientProperties.class)
public class AuthorizeController {

    @Autowired
    private ClientProperties clientProperties;

    @Autowired
    private GithubProvider githubProvider;

    @GetMapping("/callback")
    public String callback(@RequestParam("code") String code,
                           @RequestParam("state") String state){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientProperties.getId());
        accessTokenDTO.setClient_secret(clientProperties.getSecret());
        accessTokenDTO.setCode(code);
        accessTokenDTO.setState(state);
        accessTokenDTO.setRedirect_uri(clientProperties.getRedirectUri());

        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser user = githubProvider.getUser(accessToken);
        System.out.println(user.getName());
        return "index";
    }
}
