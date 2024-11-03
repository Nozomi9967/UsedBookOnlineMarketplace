package com.nozomi.controller;

import com.nozomi.pojo.Result;
import com.nozomi.pojo.User;
import com.nozomi.service.UserService;
import com.nozomi.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LoginController {

    @Autowired
    private UserService userService;

    //注册
    @PostMapping("/register")
    public Result add(@RequestBody User user)
    {
        log.info("注册用户：{}",user);
        int result=userService.add(user);
        log.info("注册成功");
        return Result.success("注册成功");
    }

    //登录
    @PostMapping("/login")
    public Result login(@RequestParam String username, @RequestParam String password)
    {
        log.info("用户登录：{}",username);
        User user=userService.login(username,password);
        if (user==null){
            return Result.fail("用户名或密码错误");
        }
        String jwt= JwtUtils.JwtGen(user.getId(),username);
        log.info("登陆成功,{}的令牌为{}",username,jwt);
        return Result.success(jwt);
    }





}
