package com.crud.home.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("회원 ID")
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "member_id")
    private Long memberId;

    @Comment("제목")
    private String title;

    @Comment("게시글")
    private String content;

    @Comment("조회수")
    private Long hits;

    @Comment("생성 날짜")
    private LocalDateTime createdDate;

    @Comment("수정 날짜")
    private LocalDateTime updatedDate;

    @PrePersist
    public void createdDate() {
        this.createdDate = LocalDateTime.now();
    }
}
