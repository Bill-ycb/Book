package com.v.newbook1.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Like implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long coId;

    private long userId;

    private long bookId;
}
