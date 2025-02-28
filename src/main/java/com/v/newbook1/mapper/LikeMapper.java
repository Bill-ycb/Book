package com.v.newbook1.mapper;

import com.github.pagehelper.Page;
import com.v.newbook1.dto.PageQueryDTO;
import com.v.newbook1.model.Book;
import com.v.newbook1.model.BookAndUser;
import com.v.newbook1.model.Like;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface LikeMapper {
    @Select("select co_id from `like` where user_id=#{userId} and book_id=#{bookId}")
    Long selectOneLike(Like like);

    Long addLike(Like like);



    Page<BookAndUser> getLike(PageQueryDTO pageQueryDTO);

    @Delete("delete from `like` where co_id =#{coId}")
    void deleteLike(Long coId);
}
