package com.ubom.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Integer id;
    private String username;//1-20
    private boolean userType;//0-普通用户，1-管理员
    private String password;//6-20
    private String nickname="未命名";
    private String email;
    private String image;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
