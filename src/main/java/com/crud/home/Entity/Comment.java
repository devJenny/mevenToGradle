package com.crud.home.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @org.hibernate.annotations.Comment("회원 ID")
    private Long memberId; // join 예정

    @org.hibernate.annotations.Comment("게시판 ID")
    private Long boardId; // join 예정

    @org.hibernate.annotations.Comment("댓글")
    private String comment;

    @org.hibernate.annotations.Comment("생성 날짜")
    private LocalDateTime createdDate;

    @org.hibernate.annotations.Comment("수정 날짜")
    private LocalDateTime updatedDate;

    @PrePersist
    public void createdDate() {
        this.createdDate = LocalDateTime.now();
    }

}
