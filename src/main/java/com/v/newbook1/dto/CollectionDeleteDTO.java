package com.v.newbook1.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CollectionDeleteDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private List<Long> ids;
}
