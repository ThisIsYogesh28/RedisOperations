package com.ByteShield.RedisOperations.Controllers;

import com.ByteShield.RedisOperations.Services.ListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private ListService listService;

    @PostMapping("/log/{userId}")
    public String log(@PathVariable String userId,@RequestParam String ipAddress){
        listService.loadUserLogin(userId,ipAddress);
        return "Login ip logged";
    }

    @GetMapping("/recent/{userId}")
    public List<Object> getRecent(@PathVariable String userId){
        return listService.getRecent(userId);
    }
}
