package com.crud.home.service;

import com.crud.home.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class UserService {

    @Resource
    UserMapper userMapper;

    // 조회
    public List<Map<String, Object>> findByUserId(Map<String, Object> param) {
         return userMapper.findByUserId(param);
    }

    // 회원가입
    public void insertUser(Map<String, Object> param) {

        userMapper.insertUser(param);
    }
}
