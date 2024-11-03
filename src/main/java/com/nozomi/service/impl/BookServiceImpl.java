package com.nozomi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nozomi.mapper.BookMapper;
import com.nozomi.pojo.Book;
import com.nozomi.pojo.BookSearchFactor;
import com.nozomi.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements BookService {

    @Autowired
    private BookMapper bookMapper;

//    @Log
    @Override
    public void add(Book book) {
        //设置创建时间
        book.setCreateTime(LocalDateTime.now());
        book.setUpdateTime(LocalDateTime.now());
        bookMapper.insert(book);
    }

//    @Log
    @Override
    public int delete(List<Integer> ids) {
        LambdaQueryWrapper<Book> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.in(Book::getId,ids);
        return bookMapper.delete(queryWrapper);
    }

//    @Log
    @Override
    public int update(Book book) {
        book.setUpdateTime(LocalDateTime.now());

        return bookMapper.update(book,null);
    }

//    @Override
//    public Long count() {
//        return bookMapper.count();
//    }

    @Override
    public Page page(Integer page, Integer pageSize, BookSearchFactor searchFactor, LocalDate beginDate, LocalDate endDate) {
//        //1.获取总记录数
//        Long total = bookMapper.count();
//        //2.获取当前页数据
//        Integer start = (page-1)*pageSize;
//        List<Book> data = bookMapper.page(start,pageSize);
//
//        return new PageBean(total,data);

//        //1.设置分页参数
//        PageHelper.startPage(page,pageSize);
//        //2.执行查询
//        List<Book> bookList = bookMapper.list(searchFactor, beginDate, endDate);
//        Page<Book> p=(Page<Book>)bookList;
//        //3.返回结果
//        return new PageBean(p.getTotal(),p.getResult());

        Page<Book> p=new Page<>(page,pageSize);
        LambdaQueryWrapper<Book> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.between(searchFactor.getBottomPrice()!=null&&searchFactor.getTopPrice()!=null,Book::getPrice,searchFactor.getBottomPrice(),searchFactor.getTopPrice())
                .like(searchFactor.getBookName()!=null&& StringUtils.isNotBlank(searchFactor.getBookName()),Book::getBookname,searchFactor.getBookName())
                .like(searchFactor.getAuthor()!=null&&StringUtils.isNotBlank(searchFactor.getAuthor()),Book::getAuthor,searchFactor.getAuthor())
                .between(beginDate!=null&&endDate!=null,Book::getCreateTime,beginDate,endDate);
        return bookMapper.selectPage(p, lambdaQueryWrapper);

    }
//
//    @Log
//    @Override
//    public void deleteByUserId(List<Integer> ids) {
//        bookMapper.deleteByUserId(ids);
//    }
}
