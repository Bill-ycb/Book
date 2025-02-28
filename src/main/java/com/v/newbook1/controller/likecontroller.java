package com.v.newbook1.controller;

import com.v.newbook1.dto.CollectionAddDTO;
import com.v.newbook1.dto.CollectionDeleteDTO;
import com.v.newbook1.dto.PageQueryDTO;

import com.v.newbook1.result.Result;
import com.v.newbook1.service.LikeService;
import com.v.newbook1.vo.PageLikeVO;
import com.v.newbook1.vo.PageVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@CrossOrigin(origins ="*")
@RequestMapping("/book/like")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class likecontroller {
    private final LikeService likeService;

    @PostMapping("/addLike")
    public Result<Long> addLike(@RequestBody CollectionAddDTO collectionAddDTO) {
        Long l = likeService.addLike(collectionAddDTO);
        return Result.success(l);
    }

    @PostMapping("/PageQuery")//查询本人的收藏
    public Result<PageLikeVO> getLike(@RequestBody PageQueryDTO pageQueryDTO) {
        PageLikeVO pagelikeVO= likeService.getLike(pageQueryDTO);
        return Result.success(pagelikeVO);
    }

    @DeleteMapping("/delete")
    public Result deleteLike(@RequestBody CollectionDeleteDTO collectionDeleteDTO){
        likeService.deleteLike(collectionDeleteDTO);
        return Result.success();
    }
}
