package com.ubom.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    private Integer id;
    private String bookName;//1-50
    private String author;//1-20
    private Float price;
    private String description;//1-200
    private String image;
    private Integer userId;
    private boolean isSolded;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
