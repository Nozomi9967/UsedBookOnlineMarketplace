package com.ubom.controller;

import com.ubom.pojo.Result;
import com.ubom.pojo.User;
import com.ubom.service.UserService;
import com.ubom.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
        userService.add(user);
        log.info("注册成功");
        return Result.success("注册成功");
    }

    //登录
    @PostMapping("/login")
    public Result login(@RequestParam String username, @RequestParam String password)
    {
        log.info("用户登录：{}",username);
        List<User> userList=userService.login(username,password);
        if (userList.size()==0){
            return Result.fail("用户名或密码错误");
        }
        String jwt= JwtUtils.JwtGen(userList.get(0).getId(),username);
        log.info("登陆成功,{}的令牌为{}",username,jwt);
        return Result.success(jwt);
    }





}
