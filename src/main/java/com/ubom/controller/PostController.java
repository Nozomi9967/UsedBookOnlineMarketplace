package com.ubom.controller;

import com.ubom.anno.Log;
import com.ubom.pojo.Post;
import com.ubom.pojo.Result;
import com.ubom.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class PostController {

    @Autowired
    private PostService postService;

    //新增帖子
    @Log
    @PostMapping("/posts")
    public Result add(@RequestBody Post post) {
        log.info("新增帖子：{}",post);
        postService.add(post);
        log.info("新增帖子成功");
        return Result.success("新增帖子成功");
    }

    //修改帖子内容
    @PutMapping("/posts")
    public Result update(@RequestBody Post post) {
        log.info("修改帖子：{}",post);
        postService.update(post);
        log.info("修改帖子成功");
        return Result.success("修改帖子成功");
    }

    //

}
