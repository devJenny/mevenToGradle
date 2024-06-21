package com.crud.home.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {

    // 회원조회
    List<Map<String, Object>> findByUserId(Map<String, Object> param);

    // 회원가입
    void insertUser(Map<String, Object> param);
}
