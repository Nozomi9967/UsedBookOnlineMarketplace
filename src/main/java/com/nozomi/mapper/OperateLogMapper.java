package com.nozomi.mapper;

import com.nozomi.pojo.OperateLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OperateLogMapper {

    //插入日志数据
    @Insert("insert into operate_log(operate_user,operate_time,class_name,method_name,method_params,method_result,cost_time)" +
            " values(#{operateUser},#{operateTime},#{className},#{methodName},#{methodParams},#{returnValue},#{costTime})")
    void insert(OperateLog operateLog);

}
