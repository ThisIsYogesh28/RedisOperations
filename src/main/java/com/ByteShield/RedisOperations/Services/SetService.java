package com.ByteShield.RedisOperations.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class SetService {
    @Autowired
    private final RedisTemplate<String,Object> redisTemplate;
    private final String KEY="likes:post:";

    public void likePost(String postId,String userId){
        String key=KEY+postId;
        redisTemplate.opsForSet().add(key,userId);
    }
    public void unLikePost(String postId,String userId){
        String key=KEY+postId;
        redisTemplate.opsForSet().remove(key,userId);
    }
    public Set<String> getUserWhoLiked(String postId){
        String key=KEY+postId;
        return (Set<String>) (Set<?>) redisTemplate.opsForSet().members(key);
    }
    public boolean HasUserLiked(String postId,String userId){
        String key=KEY+postId;
        return Boolean.TRUE.equals(redisTemplate.opsForSet().isMember(key,userId));
    }
    public long getLikeCount(String postId){
        String key=KEY+postId;
        return redisTemplate.opsForSet().size(key);
    }
}
