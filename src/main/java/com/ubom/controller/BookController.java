package com.ubom.controller;

import com.ubom.pojo.Book;
import com.ubom.pojo.BookSearchFactor;
import com.ubom.pojo.PageBean;
import com.ubom.pojo.Result;
import com.ubom.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@RestController
public class BookController {
    @Autowired
    private BookService bookService;

    //查询所有书
//    @GetMapping("/getallbooks")
//    public Result List(){
//        List<Book> books = bookService.List();
//        return Result.success(books);
//    }

    //查询对应页码的二手书，可携带附加条件
    @GetMapping("/books")
    public Result page(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "5") Integer pageSize,
                       @ModelAttribute BookSearchFactor bookSearchFactor,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate beginDate,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate){
        PageBean Pagebean = bookService.page(page,pageSize,bookSearchFactor,beginDate,endDate);
        return Result.success(Pagebean);
    }

    //添加书籍
    @PostMapping("/books")
    public Result add(@RequestBody Book book){
        bookService.add(book);
        return Result.success("添加成功");
    }

    //删除书籍
    @DeleteMapping("/books/{ids}")
    public Result delete(@PathVariable List<Integer> ids){
        bookService.delete(ids);
        return Result.success("删除成功");
    }

    //修改书籍
    @PutMapping("/books")
    public Result update(@RequestBody Book book){
        bookService.update(book);
        return Result.success("修改成功");
    }

    //查询书籍总数
//    @GetMapping("/books/count")
//    public Result count(){
//        Long count = bookService.count();
//        return Result.success(count);
//    }


}
