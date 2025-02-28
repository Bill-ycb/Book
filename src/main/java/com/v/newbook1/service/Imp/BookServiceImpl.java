package com.v.newbook1.service.Imp;


import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSClientBuilder;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.v.newbook1.context.BaseContext;
import com.v.newbook1.dto.BookAddDTO;
import com.v.newbook1.dto.BookDeleteDTO;
import com.v.newbook1.dto.BookUpdateDTO;
import com.v.newbook1.dto.PageQueryDTO;
import com.v.newbook1.exception.AppException;
import com.v.newbook1.exception.AppExceptionCodeMsg;
import com.v.newbook1.mapper.BookMapper;
import com.v.newbook1.model.Book;
import com.v.newbook1.service.BookService;
import com.v.newbook1.vo.PageVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;


@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))

public class BookServiceImpl implements BookService {
    private final BookMapper bookMapper;

    @Override
    public Long addBook(BookAddDTO bookAddDTO) {
        Book book = new Book();
        BeanUtils.copyProperties(bookAddDTO, book);
        book.setUserId(BaseContext.getCurrentId());
        book.setStatus(1);
        book.setPrice(BigDecimal.valueOf(bookAddDTO.getPrice()));
        book.setCreateTime(LocalDateTime.now());
        book.setUpdateTime(LocalDateTime.now());
        bookMapper.addBook(book);
        return book.getBookId();
    }

    @Override
    public void deleteBook(BookDeleteDTO bookDeleteDTO) {
        List<Long> ids = bookDeleteDTO.getIds();
        if (ids != null && !ids.isEmpty()) {
            for (Long bookId : ids) {
                bookMapper.deleteBook(bookId);
            }
        }
    }

    @Override
    public Book updateBook(BookUpdateDTO bookUpdateDTO) throws AppException {
        Book book = new Book();
        BeanUtils.copyProperties(bookUpdateDTO, book);
        //if (!Objects.equals(book.getUserId(), BaseContext.getCurrentId())) {
        //    throw new AppException(AppExceptionCodeMsg.WRONG_USER);
        //}
        book.setUpdateTime(LocalDateTime.now());
        book.setPrice(BigDecimal.valueOf(bookUpdateDTO.getPrice()));
        bookMapper.update(book);
        return book;
    }

    @Override
    public PageVO getAllBook(PageQueryDTO pageQueryDTO) {
        if (Objects.isNull(pageQueryDTO.getOrderWay())) {
            throw new AppException(AppExceptionCodeMsg.WRONG_ORDERWAY);
        }
        if (!Objects.equals(pageQueryDTO.getOrderWay(), "create_time")) {
            if (!pageQueryDTO.getOrderWay().equals("update_time")) {
                throw new AppException(AppExceptionCodeMsg.WRONG_ORDERWAY);
            }
        }
        PageHelper.startPage(pageQueryDTO.getPage(), pageQueryDTO.getPageSize());
        Page<Book> page = bookMapper.getAllBook(pageQueryDTO);
        int total = (int) page.getTotal();
        List<Book> books = page.getResult();
        return new PageVO(total, books);
    }

    @Override
    public Book getOneBook(int bookId) {
        return bookMapper.getOneBook(bookId);
    }

    @Override
    public String uploadBook(MultipartFile file) throws IOException {
        String ALI_DOMAIN = "https://yuetaoshu.oss-cn-qingdao.aliyuncs.com/";
        if (file.isEmpty()) {
            return "file is Empty!";
        }
        //重命名
        String originalFilename = file.getOriginalFilename();
        String ext = "." + FilenameUtils.getExtension(originalFilename);
        String uuid = UUID.randomUUID().toString().replace("-", "");
        String fileName = uuid + ext;
        //地域节点
        String endpoint = "https://oss-cn-qingdao.aliyuncs.com";
        String accessKeyId = "LTAI5tRBsmEFweoHkVPqwRJo";
        String accessKeySecret = "rxRGObDCEO4da4igGz1jpjW9DWlbPi";
        //oss客户端对象
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        ossClient.putObject(
                "yuetaoshu",
                fileName,
                file.getInputStream()
        );
        ossClient.shutdown();
        return ALI_DOMAIN + fileName;
    }
}

