package com.ubom.service;

import com.ubom.pojo.PageBean;
import com.ubom.pojo.User;
import com.ubom.pojo.UserSearchFactor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface UserService {

    void add(User user);

    void update(User user);

    void delete(List<Integer> ids);


    PageBean page(Integer page, Integer pageSize, UserSearchFactor userSearchFactor, LocalDate beginDate, LocalDate endDate);

    List<User> login(String username, String password);
}
