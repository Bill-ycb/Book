package com.v.newbook1.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@AllArgsConstructor
@Builder
@NoArgsConstructor
@Data
public class UserLoginDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String code;
    private String userName;
    private String head;
    private String idNumber;
    private String connect;
}
