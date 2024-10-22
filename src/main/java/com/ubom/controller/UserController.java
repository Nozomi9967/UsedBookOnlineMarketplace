package com.ubom.controller;

import com.ubom.pojo.PageBean;
import com.ubom.pojo.Result;
import com.ubom.pojo.User;
import com.ubom.pojo.UserSearchFactor;
import com.ubom.service.BookService;
import com.ubom.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
public class UserController {

    @Autowired
    private UserService userService;


    //修改用户信息
    @PutMapping("/users/update")
    public Result update(@RequestBody User user){
        log.info("修改用户信息：{]",user);
        userService.update(user);
        log.info("修改成功");
        return Result.success("修改成功");
    }

    //删除用户
    @DeleteMapping("/users/{ids}")
    public Result delete(@PathVariable List<Integer> ids)
    {
        log.info("删除用户：{}",ids);
        userService.delete(ids);
        log.info("删除成功");
        return Result.success("删除成功");
    }

    //查询所有用户（管理员）
    @GetMapping("/users/all")
    public Result list(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "5") Integer pageSize,
                       @ModelAttribute UserSearchFactor userSearchFactor,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate beginDate,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate){
        log.info("查询所有用户");
        PageBean pageBean = userService.page(page,pageSize,userSearchFactor,beginDate,endDate);
        return Result.success(pageBean);
    }
}
