package com.ubom.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ubom.anno.Log;
import com.ubom.mapper.BookMapper;
import com.ubom.pojo.Book;
import com.ubom.pojo.BookSearchFactor;
import com.ubom.pojo.PageBean;
import com.ubom.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookMapper bookMapper;
//    @Override
//    public List<Book> List() {
//        return bookMapper.List();
//    }

    @Log
    @Override
    public void add(Book book) {
        //设置创建时间
        book.setCreateTime(LocalDateTime.now());
        book.setUpdateTime(LocalDateTime.now());
        bookMapper.insert(book);
    }

    @Log
    @Override
    public void delete(List<Integer> ids) {
        bookMapper.deleteById(ids);
    }

    @Log
    @Override
    public void update(Book book) {
        book.setUpdateTime(LocalDateTime.now());

        bookMapper.update(book);
    }

//    @Override
//    public Long count() {
//        return bookMapper.count();
//    }

    @Override
    public PageBean page(Integer page, Integer pageSize, BookSearchFactor searchFactor, LocalDate beginDate, LocalDate endDate) {
//        //1.获取总记录数
//        Long total = bookMapper.count();
//        //2.获取当前页数据
//        Integer start = (page-1)*pageSize;
//        List<Book> data = bookMapper.page(start,pageSize);
//
//        return new PageBean(total,data);

        //1.设置分页参数
        PageHelper.startPage(page,pageSize);
        //2.执行查询
        List<Book> bookList = bookMapper.list(searchFactor, beginDate, endDate);
        Page<Book> p=(Page<Book>)bookList;
        //3.返回结果
        return new PageBean(p.getTotal(),p.getResult());
    }

    @Log
    @Override
    public void deleteByUserId(List<Integer> ids) {
        bookMapper.deleteByUserId(ids);
    }
}
