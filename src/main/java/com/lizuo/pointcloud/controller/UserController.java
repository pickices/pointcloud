package com.lizuo.pointcloud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class UserController {

    public static Map user = new HashMap<String,String>();
    static {
        user.put("lizuo","11111111");
    }

    @ResponseBody
    @GetMapping("/login")
    @CrossOrigin(origins = "*")
    public String login(@RequestParam(value = "username") String userName, @RequestParam(value = "password")String password){
        if(!user.containsKey(userName)){
            return "user not exited";
        }
        if(!user.get(userName).equals(password)){
            return "wrong username or password";
        }
        return "success";
    }
}
