package com.crud.home.controller;

import com.crud.home.Entity.Member;
import com.crud.home.domain.JoinReqDto;
import com.crud.home.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Comment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;


@RequiredArgsConstructor
@Controller
@Slf4j
public class MemberController {

    private final MemberService memberService;

    @Comment("로그인")
    @GetMapping("/auth/login")
    public String login() {

        return "member/login";
    }

    @Comment("회원가입 페이지")
    @GetMapping("/auth/join")
    public String join() {
        return "member/join";
    }

    @Comment("회원가입")
    @PostMapping("/auth/join")
    public String join(JoinReqDto dto) {

        Member result = memberService.join(dto);
        log.info("회원가입: {}", result);

        return "redirect:/";
    }
}
