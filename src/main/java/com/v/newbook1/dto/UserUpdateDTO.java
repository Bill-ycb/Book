package com.v.newbook1.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserUpdateDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String idNumber;

    private String head;

    private String connect;

    private String name;

}
