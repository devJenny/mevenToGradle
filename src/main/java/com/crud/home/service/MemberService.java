package com.crud.home.service;

import com.crud.home.Repository.MemberRepository;
//import com.crud.home.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
@Slf4j
public class MemberService {

//    private final UserMapper userMapper;

    private final MemberRepository memberRepository;

    // 조회
    public List<Map<String, Object>> findByUserId(Map<String, Object> param) {
//         return userMapper.findByUserId(param);
        return null;
    }

    // 회원가입
    public void insertUser(Map<String, Object> param) {
//        userMapper.insertUser(param);
    }
}
