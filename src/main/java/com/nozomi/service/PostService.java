package com.nozomi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nozomi.pojo.Post;

public interface PostService extends IService<Post> {

    int add(Post post);

    int update(Post post);
}
