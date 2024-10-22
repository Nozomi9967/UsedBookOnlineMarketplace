package com.ubom.mapper;

import com.ubom.pojo.Book;
import com.ubom.pojo.BookSearchFactor;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface BookMapper {

    @Insert("insert into t_book(bookname,price,author,description,image,user_id,create_time,update_time)"
            +"values(#{bookName},#{price},#{author},#{description},#{image},#{userId},#{createTime},#{updateTime})")
    void insert(Book book);

    //    查询全部二手书信息
    List<Book> list(BookSearchFactor searchFactor, LocalDate beginDate, LocalDate endDate);

    /*
    * 根据id批量删除二手书
    * @param id
    * */
    void deleteById(List<Integer> ids);

    /*
    * 更新二手书信息
    *
    * 本方法重置Book类信息
    *
    * 如果为null，那么使用动态sql语句进行选择性修改
    * */
    void update(Book book);

    //    根据用户id批量删除二手书
    void deleteByUserId(List<Integer> ids);

    /*
    * 查询二手书总数
    *
    * 返回int类型
    * */
//    @Select("select count(*) from t_book")
//    Long count();
//
//    /*
//    * 分页查询
//    *
//    * 返回List<Book>类型
//    * */
//    @Select("select * from t_book limit #{start},#{pageSize}")
//    List<Book> page(Integer start, Integer pageSize);
}
