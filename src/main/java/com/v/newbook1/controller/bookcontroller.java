package com.v.newbook1.controller;

import com.v.newbook1.dto.BookAddDTO;
import com.v.newbook1.dto.BookDeleteDTO;
import com.v.newbook1.dto.BookUpdateDTO;
import com.v.newbook1.dto.PageQueryDTO;
import com.v.newbook1.model.Book;
import com.v.newbook1.result.Result;
import com.v.newbook1.service.BookService;
import com.v.newbook1.vo.PageVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@Slf4j
@CrossOrigin(origins ="*")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/book")
public class bookcontroller {
    private final BookService bookService;

    @PostMapping("/addBook")
    public Result<Long> addBook(@RequestBody BookAddDTO bookAddDTO) {
        Long bookId = bookService.addBook(bookAddDTO);
        return Result.success(bookId);
    }
    @DeleteMapping("/deleteBook")
    public Result deleteBook(@RequestBody BookDeleteDTO bookDeleteDTO) {
        bookService.deleteBook(bookDeleteDTO);
        return Result.success();
    }
    @PostMapping("/updateBook")
    public Result<Book> updateBook(@RequestBody BookUpdateDTO bookUpdateDTO) {
        Book book = bookService.updateBook(bookUpdateDTO);
        return Result.success(book);
    }
    @PostMapping("/getAllBook")
    public Result<PageVO> getAllBook(@RequestBody PageQueryDTO pageQueryDTO) {
        PageVO pageVO= bookService.getAllBook(pageQueryDTO);
        return Result.success(pageVO);
    }
    @GetMapping("/getOneBook/{bookId}")
    public Result<Book> getOneBook(@PathVariable int bookId) {
        Book oneBook = bookService.getOneBook(bookId);
        return Result.success(oneBook);
    }
    @PostMapping("/upload")
    public Result<String> uploadBook(@RequestParam("file") MultipartFile file) throws IOException {
        String img = bookService.uploadBook(file);
        return Result.success(img);
    }
}
