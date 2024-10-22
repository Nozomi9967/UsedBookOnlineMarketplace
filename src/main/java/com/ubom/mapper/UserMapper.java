package com.ubom.mapper;

import com.ubom.pojo.User;
import com.ubom.pojo.UserSearchFactor;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface UserMapper {

    /*
    * 操你妈，找了两天的bug，结果是不用加user.  真傻逼
    *
    * 新增用户*/
    @Insert("insert into t_user(username,password,user_type,create_time,update_time) values (#{username},#{password},#{userType},#{createTime},#{updateTime})")
    void add(User user);

    /*
    * 更新用户*/
    void update(User user);

    /*
    * 删除用户*/
    void deleteById(List<Integer> ids);

    /*
    * 查询所有用户*/
    List<User> list(UserSearchFactor userSearchFactor, LocalDate beginDate, LocalDate endDate);

    /*
    * 登录*/
    @Select("select * from t_user where username=#{username} and password=#{password}")
    List<User> login(String username, String password);
}
