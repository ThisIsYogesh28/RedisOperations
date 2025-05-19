package com.ByteShield.RedisOperations.Controllers;

import com.ByteShield.RedisOperations.Services.ZSetService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class ZSetController {

    private final ZSetService zSetService;


    @PostMapping("/{postId}/likeZ")
    public ResponseEntity<String> likePost(@PathVariable String postId) {
        zSetService.like(postId);
        return ResponseEntity.ok("Post liked successfully: " + postId);
    }


    @GetMapping("/{postId}/likesZ")
    public ResponseEntity<Double> getLikes(@PathVariable String postId) {
        Double likes = zSetService.getLikes(postId);
        return ResponseEntity.ok(likes != null ? likes : 0.0);
    }


    @GetMapping("/{postId}/rank")
    public ResponseEntity<String> getRank(@PathVariable String postId) {
        Long rank = zSetService.getPostRank(postId);
        return rank != null
                ? ResponseEntity.ok("Rank of post " + postId + ": #" + rank)
                : ResponseEntity.ok("Post not found in leaderboard.");
    }


    @GetMapping("/top/{count}")
    public ResponseEntity<Set<ZSetOperations.TypedTuple<Object>>> getTopPosts(@PathVariable int count) {
        return ResponseEntity.ok(zSetService.getTopPosts(count));
    }


    @DeleteMapping("/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable String postId) {
        zSetService.removePost(postId);
        return ResponseEntity.ok("Post removed: " + postId);
    }
}
