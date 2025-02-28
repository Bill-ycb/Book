package com.v.newbook1.service.Imp;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.v.newbook1.context.BaseContext;
import com.v.newbook1.dto.CollectionAddDTO;
import com.v.newbook1.dto.CollectionDeleteDTO;
import com.v.newbook1.dto.PageQueryDTO;
import com.v.newbook1.exception.AppException;
import com.v.newbook1.exception.AppExceptionCodeMsg;
import com.v.newbook1.mapper.LikeMapper;
import com.v.newbook1.model.Book;
import com.v.newbook1.model.BookAndUser;
import com.v.newbook1.model.Like;
import com.v.newbook1.service.LikeService;
import com.v.newbook1.vo.PageLikeVO;
import com.v.newbook1.vo.PageVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class LikeServiceImp implements LikeService {
    private final LikeMapper likeMapper;
    @Override
    public Long addLike(CollectionAddDTO collectionAddDTO) {
        Like like = new Like();
        like.setBookId(collectionAddDTO.getBookId());
        like.setUserId(BaseContext.getCurrentId());
        if(likeMapper.selectOneLike(like)==null){
            return likeMapper.addLike(like);
        }
        return 0L;
        //throw new AppException(AppExceptionCodeMsg.HAVEBEEN);
    }

    @Override
    public PageLikeVO getLike(PageQueryDTO pageQueryDTO) {
        pageQueryDTO.setUserId(BaseContext.getCurrentId());
        PageHelper.startPage(pageQueryDTO.getPage(), pageQueryDTO.getPageSize());
        Page<BookAndUser> page = likeMapper.getLike(pageQueryDTO);
        int total = (int) page.getTotal();
        List<BookAndUser> bookAndUsers = page.getResult();
        return new PageLikeVO(total, bookAndUsers);
    }

    @Override
    public void deleteLike(CollectionDeleteDTO collectionDeleteDTO) {
        List<Long> ids = collectionDeleteDTO.getIds();
        if (ids!=null && !ids.isEmpty()) {
            for (Long coId : ids) {
                likeMapper.deleteLike(coId);
            }
        }
    }
}
