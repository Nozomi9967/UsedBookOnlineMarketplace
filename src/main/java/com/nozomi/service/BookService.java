package com.nozomi.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.nozomi.pojo.Book;
import com.nozomi.pojo.BookSearchFactor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface BookService extends IService<Book> {

////    获取所有二手书
////    List<Book> List();
//
//
//    添加二手书
    void add(Book book);
//
//    删除二手书
    int delete(List<Integer> ids);

//    修改二手书
    int update(Book book);
//
////    查询二手书总数
////    Long count();
//
    Page page(Integer page, Integer pageSize, BookSearchFactor bookSearchFactor, LocalDate beginDate, LocalDate endDate);
//
////    根据用户id删除二手书
//    void deleteByUserId(List<Integer> ids);
}
