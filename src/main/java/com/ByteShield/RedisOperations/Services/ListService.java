package com.ByteShield.RedisOperations.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ListService {
    private final RedisTemplate<String,Object> redisTemplate;
    private static final String KEY="user:login:ip:";
    private static final int MAX_ENTRIES = 5;

    public void loadUserLogin(String userId,String ipAddress){
        String key=KEY+userId;
        redisTemplate.opsForList().leftPush(key,ipAddress);
        redisTemplate.opsForList().trim(key,0,MAX_ENTRIES-1);
    }

    public List<Object> getRecent(String userId){
        String key=KEY+userId;
        return redisTemplate.opsForList().range(key,0,-1);
    }
}
