package com.v.newbook1.vo;

import com.v.newbook1.model.Book;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PageVO {
    private int total;
    private List<Book> records;
}
