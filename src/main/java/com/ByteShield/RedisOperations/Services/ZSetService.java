package com.ByteShield.RedisOperations.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class ZSetService {
    @Autowired
    private final RedisTemplate<String ,Object> redisTemplate;
    private static final String KEY="post:likes:leaderboard";

    public void like(String postId){

        redisTemplate.opsForZSet().incrementScore(KEY,postId,1.0);
    }
    public Double getLikes(String postId){
        return redisTemplate.opsForZSet().score(KEY,postId);
    }
    public Set<ZSetOperations.TypedTuple<Object>> getTopPosts(int count){
        return redisTemplate.opsForZSet().rangeByScoreWithScores(KEY,0,count-1);
    }
    public Long getPostRank(String postId){
        Long rank=redisTemplate.opsForZSet().rank(KEY,postId);

        return rank!=null?rank+1:null;
    }
    public void removePost(String postId){
        redisTemplate.opsForZSet().remove(KEY,postId);
    }

}
