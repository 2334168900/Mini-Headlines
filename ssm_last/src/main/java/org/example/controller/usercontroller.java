package org.example.controller;


import org.example.service.UserService;
import org.example.stu.User;
import org.example.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("user")
public class usercontroller {

    @Autowired
    private UserService userService;

    @PostMapping("login")
    public Result login(@RequestBody User user) {
        Result result = userService.login(user);
        return result;
    }

    @GetMapping("getUserInfo")
    public Result log(@RequestHeader String token) {
        Result userlist = userService.userlist(token);
        return userlist;
    }

    @PostMapping("checkUserName")
    public Result zhuce(@RequestParam String username){
        Result un= userService.username(username);
        return un;
    }

    @PostMapping("regist")
    public Result regist(@RequestBody User user){
        Result zhuce = userService.zhuce(user);
        return zhuce;
    }

    @GetMapping("checkLogin")
    public Result checklogin(@RequestHeader String token){
        Result result =userService.chlogin(token);
        return result;
    }
}
