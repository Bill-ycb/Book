package com.v.newbook1.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class User implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long userId;

    private String openid;

    private String idNumber;

    private String head;

    private LocalDateTime createTime;

    private String connect;

    private String name;

}
