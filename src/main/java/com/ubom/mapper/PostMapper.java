package com.ubom.mapper;

import com.ubom.pojo.Post;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PostMapper {

    /*
    *新增帖子
    * */
    @Insert("insert into t_post(headline,content,image,create_time,update_time,user_id,like_count,comment_count)" +
            " values(#{headline},#{content},#{image},#{createTime},#{updateTime},#{userId},#{likeCount},#{commentCount})")
    void insert(Post post);

    /*
    * 更新帖子*/
    void update(Post post);
}
