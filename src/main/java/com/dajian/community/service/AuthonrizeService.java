package com.dajian.community.service;

import com.dajian.community.dto.GithubUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class AuthonrizeService {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private static final String KEY_PREFIX="user:token";


}
