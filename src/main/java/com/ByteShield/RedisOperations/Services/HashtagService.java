package com.ByteShield.RedisOperations.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class HashtagService {
    private final RedisTemplate<String,Object> redisTemplate;
    private static final String KEY="trending:hashtags:";
    public void add(String tag){
        double now=(double) Instant.now().getEpochSecond();
        redisTemplate.opsForZSet().incrementScore(KEY,tag,now);
    }
    public Set<ZSetOperations.TypedTuple<Object>> getRecentHashtag(String tagId){
        double oneHourAgo=(double) Instant.now().minusSeconds(3600).getEpochSecond();
        double now=(double) Instant.now().getEpochSecond();
        Set<ZSetOperations.TypedTuple<Object>> recentTags=redisTemplate.opsForZSet().rangeByScoreWithScores(KEY,oneHourAgo,now);
        return recentTags;
    }
    public Long angTag(){
       return redisTemplate.opsForZSet().removeRange(KEY,0,9);
    }

}
