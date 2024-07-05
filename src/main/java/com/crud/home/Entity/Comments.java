package com.crud.home.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Comments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("회원 ID")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "member_id")
    private Member member; // join 예정

    @Comment("게시판 ID")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "board_id")
    private Board board; // join 예정

    @Comment("댓글")
    private String comments;

    @Comment("생성 날짜")
    private LocalDateTime createdDate;

    @Comment("수정 날짜")
    private LocalDateTime updatedDate;

    @PrePersist
    public void createdDate() {
        this.createdDate = LocalDateTime.now();
    }

}
