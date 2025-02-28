package com.v.newbook1.service;

import com.v.newbook1.dto.CollectionAddDTO;
import com.v.newbook1.dto.CollectionDeleteDTO;
import com.v.newbook1.dto.PageQueryDTO;
import com.v.newbook1.vo.PageLikeVO;
import com.v.newbook1.vo.PageVO;

public interface LikeService {
    Long addLike(CollectionAddDTO collectionAddDTO);

    PageLikeVO getLike(PageQueryDTO pageQueryDTO);

    void deleteLike(CollectionDeleteDTO collectionDeleteDTO);
}
