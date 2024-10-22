package com.ubom.service;

import com.ubom.pojo.Book;
import com.ubom.pojo.BookSearchFactor;
import com.ubom.pojo.PageBean;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface BookService {

//    获取所有二手书
//    List<Book> List();


//    添加二手书
    void add(Book book);

//    删除二手书
    void delete(List<Integer> ids);

//    修改二手书
    void update(Book book);

//    查询二手书总数
//    Long count();

    PageBean page(Integer page, Integer pageSize, BookSearchFactor bookSearchFactor, LocalDate beginDate, LocalDate endDate);

//    根据用户id删除二手书
    void deleteByUserId(List<Integer> ids);
}
