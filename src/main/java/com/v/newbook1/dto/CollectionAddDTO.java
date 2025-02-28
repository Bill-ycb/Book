package com.v.newbook1.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CollectionAddDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private long bookId;//用户的根据线程拿
}
