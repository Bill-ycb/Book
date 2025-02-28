package com.v.newbook1.vo;

import com.v.newbook1.model.Book;
import com.v.newbook1.model.BookAndUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PageLikeVO {
    private int total;
    private List<BookAndUser> records;
}
