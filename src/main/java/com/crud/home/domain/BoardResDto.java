package com.crud.home.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardResDto {
    private Long id;
    private Long memberId;
    private String title;
    private String content;
    private Long hits;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
