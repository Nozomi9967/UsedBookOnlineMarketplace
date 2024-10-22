package com.ubom.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ubom.anno.Log;
import com.ubom.mapper.BookMapper;
import com.ubom.mapper.UserMapper;
import com.ubom.pojo.PageBean;
import com.ubom.pojo.User;
import com.ubom.pojo.UserSearchFactor;
import com.ubom.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    BookMapper bookMapper;

    @Log
    @Override
    public void add(User user) {
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        userMapper.add(user);
    }

    @Log
    @Override
    public void update(User user) {
        user.setUpdateTime(LocalDateTime.now());
        userMapper.update(user);
    }


    /*
    * spring事务管理
    * 默认只回滚RunTimeException,rollbackFor用于指定*/
    @Log
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(List<Integer> ids) {
        userMapper.deleteById(ids);
        bookMapper.deleteByUserId(ids);
    }



    @Override
    public PageBean page(Integer page, Integer pageSize, UserSearchFactor userSearchFactor, LocalDate beginDate, LocalDate endDate) {
        //1.设置分页参数
        PageHelper.startPage(page,pageSize);
        //2.执行查询
        List<User> userList = userMapper.list(userSearchFactor, beginDate, endDate);
        Page<User> p=(Page<User>)userList;
        return new PageBean(p.getTotal(),p.getResult());
    }

    @Override
    public List<User> login(String username, String password) {
        return userMapper.login(username,password);
    }
}
