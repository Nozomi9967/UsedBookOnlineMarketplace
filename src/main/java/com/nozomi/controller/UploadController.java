package com.nozomi.controller;

import com.aliyun.oss.AliOSSUtil;
import com.aliyun.oss.enums.OssFileNameEnum;
import com.nozomi.pojo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class UploadController {

    @Autowired
    private AliOSSUtil aliOSSUtil;

    // 书籍上传图片
    @PostMapping("/books/upload")
    public Result BookUpload(MultipartFile image) throws IOException {

        String url=aliOSSUtil.upload(image, OssFileNameEnum.BOOK_IMAGE);
        return Result.success(url);
    }

    // 用户上传头像
    @PostMapping("/users/upload")
    public Result UserUpload(MultipartFile image) throws IOException {

        String url=aliOSSUtil.upload(image, OssFileNameEnum.USER_IMAGE);
        return Result.success(url);
    }
}
