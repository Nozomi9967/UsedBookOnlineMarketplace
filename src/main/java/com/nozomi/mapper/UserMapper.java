package com.nozomi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nozomi.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    /*
    * 操你妈，找了两天的bug，结果是不用加user.  真傻逼
    *
    * 新增用户*/
//    @Insert("insert into t_user(username,password,user_type,create_time,update_time) values (#{username},#{password},#{userType},#{createTime},#{updateTime})")
//    void add(User user);
//
//    /*
//    * 更新用户*/
//    void update(User user);
//
//    /*
//    * 删除用户*/
//    void deleteById(List<Integer> ids);
//
//    /*
//    * 查询所有用户*/
//    List<User> list(UserSearchFactor userSearchFactor, LocalDate beginDate, LocalDate endDate);
//
//    /*
//    * 登录*/
//    @Select("select * from t_user where username=#{username} and password=#{password}")
//    List<User> login(String username, String password);
}
