package com.ubom.service.impl;

import com.ubom.anno.Log;
import com.ubom.mapper.PostMapper;
import com.ubom.pojo.Post;
import com.ubom.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostMapper postMapper;

    @Log
    @Override
    public void add(Post post) {
        post.setCreateTime(LocalDateTime.now());
        post.setUpdateTime(LocalDateTime.now());
        postMapper.insert(post);
    }

    @Log
    @Override
    public void update(Post post) {
        post.setUpdateTime(LocalDateTime.now());
        postMapper.update(post);
    }
}
