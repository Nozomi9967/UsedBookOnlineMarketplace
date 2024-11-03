package com.nozomi.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nozomi.pojo.Book;
import com.nozomi.pojo.BookSearchFactor;
import com.nozomi.pojo.Result;
import com.nozomi.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@RestController
public class BookController {
    @Autowired
    private BookService bookService;


    //查询对应页码的二手书，可携带附加条件
    @GetMapping("/books")
    public Result page(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "5") Integer pageSize,
                       @ModelAttribute BookSearchFactor bookSearchFactor,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate beginDate,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate){
        Page p = bookService.page(page,pageSize,bookSearchFactor,beginDate,endDate);
        return Result.success(p);
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
        int result=bookService.delete(ids);
        return Result.success("删除成功");
    }

    //修改书籍
    @PutMapping("/books")
    public Result update(@RequestBody Book book){
        int result=bookService.update(book);
        return Result.success("修改成功");
    }

    //查询书籍总数
//    @GetMapping("/books/count")
//    public Result count(){
//        Long count = bookService.count();
//        return Result.success(count);
//    }


}
