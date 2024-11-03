package com.nozomi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nozomi.mapper.PostMapper;
import com.nozomi.pojo.Post;
import com.nozomi.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PostServiceImpl extends ServiceImpl<PostMapper, Post> implements PostService {

    @Autowired
    private PostMapper postMapper;

//    @Log
    @Override
    public int add(Post post) {
        post.setCreateTime(LocalDateTime.now());
        post.setUpdateTime(LocalDateTime.now());
        return postMapper.insert(post);
    }
//
//    @Log
    @Override
    public int update(Post post) {
        post.setUpdateTime(LocalDateTime.now());
        return postMapper.update(post,null);
    }
}
