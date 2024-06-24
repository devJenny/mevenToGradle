package com.crud.home.domain;

import com.crud.home.Entity.Board;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardCreateReqDto {

    private String title;
    private String content;

}
