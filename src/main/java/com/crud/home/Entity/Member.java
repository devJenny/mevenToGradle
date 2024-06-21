package com.crud.home.Entity;

/**
 * fileName       : Member
 * date           : 2024.06.21
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-06-21       devjenny       최초 생성
 */

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
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("회원 ID")
    private String userId;

    @Comment("비밀번호")
    private String password;

    @Comment("회원 이름")
    private String userName;

    @Comment("전화번호")
    private String phoneNumber;

    @Comment("이메일")
    private String email;

    @Comment("성별")
    private String gender;

    @Comment("생성 날짜")
    private LocalDateTime createdDate;

    @PrePersist
    public void createdDate() {
        this.createdDate = LocalDateTime.now();
    }
}
