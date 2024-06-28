package com.crud.home.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;
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
    @NotNull
    private String username;

    @Comment("비밀번호")
    @NotNull
    private String password;

    @Comment("회원 이름")
    @NotNull
    private String name;

    @Comment("닉네임")
    @NotNull
    private String nickname;

    @Comment("전화번호")
    @NotNull
    private String phoneNumber;

    @Comment("이메일")
    @NotNull
    private String email;

    @Comment("성별")
    @NotNull
    private String gender;

    @Comment("생성 날짜")
    @NotNull
    private LocalDateTime createdDate;

    @PrePersist
    public void createdDate() {
        this.createdDate = LocalDateTime.now();
    }

}
