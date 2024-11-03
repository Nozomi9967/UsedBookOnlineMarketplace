package com.nozomi.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.nozomi.pojo.User;
import com.nozomi.pojo.UserSearchFactor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface UserService extends IService<User> {

    int add(User user);

    int update(User user);

    void delete(List<Integer> ids);


    Page page(Integer page, Integer pageSize, UserSearchFactor userSearchFactor, LocalDate beginDate, LocalDate endDate);

    //查询是否有对应用户，成功返回该user对象
    User login(String username, String password);
}
