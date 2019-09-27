package com.dajian.community.service;

import com.dajian.community.mapper.UserMapper;
import com.dajian.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public User queryUserByToken(String token){
        return userMapper.queryUserByToken(token);
    }

}
