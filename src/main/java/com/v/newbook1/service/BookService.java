package com.v.newbook1.service;

import com.v.newbook1.dto.BookAddDTO;
import com.v.newbook1.dto.BookDeleteDTO;
import com.v.newbook1.dto.BookUpdateDTO;
import com.v.newbook1.dto.PageQueryDTO;
import com.v.newbook1.model.Book;
import com.v.newbook1.vo.PageVO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface BookService {
    Long addBook(BookAddDTO bookAddDTO);

    void deleteBook(BookDeleteDTO bookDeleteDTO);

    Book updateBook(BookUpdateDTO bookUpdateDTO);

    PageVO getAllBook(PageQueryDTO pageQueryDTO);

    Book getOneBook(int bookId);

    String uploadBook(MultipartFile file) throws IOException;
}
