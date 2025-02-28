package com.v.newbook1.mapper;

import com.github.pagehelper.Page;
import com.v.newbook1.dto.PageQueryDTO;
import com.v.newbook1.model.Book;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface BookMapper {
    Long addBook(Book book);

    @Delete("delete from book where book_id=#{bookId}")
    void deleteBook(Long bookId);

    @Update("update book set name=#{name},image=#{image},msg=#{msg},price=#{price}," +
            "category_id=#{categoryId},author=#{author},press=#{press},status=#{status},update_time=#{updateTime}" +
            " where book_id=#{bookId}")
    void update(Book book);

    Page<Book> getAllBook(PageQueryDTO pageQueryDTO);

    @Select("select author ,press, book_id, user_id, category_id, name, image, msg, price, status, create_time, update_time from book " +
            "where book_id=#{bookId}")
    Book getOneBook(int bookId);
}
