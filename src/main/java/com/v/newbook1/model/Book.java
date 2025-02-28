package com.v.newbook1.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Book implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    //书籍id
    private Long bookId;

    //卖书人用户id
    private Long userId;

    //商品分类
    private Long categoryId;

    //书籍名称
    private String name;

    //书籍图片
    private String image;

    //书籍简介
    private String msg;

    //书籍价格
    private BigDecimal price;

    private String author;
    private String press;

    //书籍出售状态 1代表出售中，0代表已出售
    private Integer status;

    //发布时间
    private LocalDateTime createTime;

    //更新时间
    private LocalDateTime updateTime;

}
