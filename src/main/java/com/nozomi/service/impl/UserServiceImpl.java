package com.nozomi.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nozomi.mapper.BookMapper;
import com.nozomi.mapper.UserMapper;
import com.nozomi.pojo.User;
import com.nozomi.pojo.UserSearchFactor;
import com.nozomi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    BookMapper bookMapper;

//    @Log
    @Override
    public int add(User user) {
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        return userMapper.insert(user);
    }

//    @Log
    @Override
    public int update(User user) {
        user.setUpdateTime(LocalDateTime.now());
        return userMapper.update(user,null);
    }


    /*
    * spring事务管理
    * 默认只回滚RunTimeException,rollbackFor用于指定*/
//    @Log
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(List<Integer> ids) {
        userMapper.deleteByIds(ids);
        bookMapper.deleteByUserId(ids);
    }



    @Override
    public Page page(Integer page, Integer pageSize, UserSearchFactor userSearchFactor, LocalDate beginDate, LocalDate endDate) {
//        //1.设置分页参数
//        PageHelper.startPage(page,pageSize);
//        //2.执行查询
//        List<User> userList = userMapper.list(userSearchFactor, beginDate, endDate);
//        Page<User> p=(Page<User>)userList;
//        return new PageBean(p.getTotal(),p.getResult());
        Page<User> p=new Page<>(page,pageSize);
        LambdaQueryWrapper<User> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.like(userSearchFactor.getUsername()!=null&& StringUtils.isNotBlank(userSearchFactor.getUsername()),User::getUsername,userSearchFactor.getUsername())
                .like(userSearchFactor.getNickname()!=null&&StringUtils.isNotBlank(userSearchFactor.getNickname()),User::getNickname,userSearchFactor.getNickname())
                .like(userSearchFactor.getEmail()!=null&&StringUtils.isNotBlank(userSearchFactor.getEmail()),User::getEmail,userSearchFactor.getEmail())
                //用户类型必须指定一种，不能填null
                .eq(User::isUserType,userSearchFactor.isUserType())
                .between(beginDate!=null&&endDate!=null,User::getCreateTime,beginDate,endDate);
        return userMapper.selectPage(p,queryWrapper);
    }

    @Override
    public User login(String username, String password) {
        LambdaQueryWrapper<User> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(StringUtils.isNotBlank(username),User::getUsername,username)
                .eq(StringUtils.isNotBlank(password),User::getPassword,password);
        return userMapper.selectOne(lambdaQueryWrapper);
    }
}
