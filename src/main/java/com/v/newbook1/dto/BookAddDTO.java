package com.v.newbook1.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class BookAddDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    //书籍名称
    private String name;

    //书籍图片
    private String image;

    //书籍简介
    private String msg;

    //书籍价格
    private Integer price;

    private String author;
    private String press;

    //商品分类
    private long categoryId;
}
