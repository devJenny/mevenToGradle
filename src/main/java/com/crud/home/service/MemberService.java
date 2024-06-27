package com.crud.home.service;

import com.crud.home.Entity.Member;
import com.crud.home.Repository.MemberRepository;
import com.crud.home.domain.JoinReqDto;
import com.crud.home.mapper.MemberMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Comment;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;

    private final MemberMapper memberMapper = MemberMapper.INSTANCE;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Comment("회원가입")
    @Transactional
    public Member join(JoinReqDto dto) {
        Member member = memberMapper.toEntity(dto);
        String rawPassword = dto.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);
        member.setPassword(encPassword);
        Member save = memberRepository.save(member);

        return save;
    }

    // 조회
    public List<Map<String, Object>> findByUserId(Map<String, Object> param) {
//         return userMapper.findByUserId(param);
        return null;
    }

}
