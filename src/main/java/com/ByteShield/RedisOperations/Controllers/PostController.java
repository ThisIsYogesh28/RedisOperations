package com.ByteShield.RedisOperations.Controllers;

import com.ByteShield.RedisOperations.Services.SetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/posts")
public class PostController {
    @Autowired
    private SetService setService;
    @PostMapping("/{postId}/like")
    public String like(@PathVariable String postId, @RequestParam String userId){
        setService.likePost(postId,userId);
        return "Liked!!";

    }
    @DeleteMapping("/{postId}/unlike")
    public String unLike(@PathVariable String postId,@RequestParam String userId){
        setService.unLikePost(postId,userId);
        return "Unliked!!";
    }
    @GetMapping("/{postId}/likes")
    public Set<String> getLikes(@PathVariable String postId){
        return setService.getUserWhoLiked(postId);
    }

    @GetMapping("/{postId}/liked")
    public boolean hasLiked(@PathVariable String postId,@RequestParam String userId){
        return setService.HasUserLiked(postId,userId);
    }

    @GetMapping("/{postId}/like-count")
    public long getLikesCount(@PathVariable String postId){
        return setService.getLikeCount(postId);
    }

}
