package com.crud.home.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface CommentMapper {

    //    댓글 조회
//    List<Map<String, Object>> commentList(Map<String, Object> param);
    List<Map<String,Object>> commentList(Long id);

    // 댓글 작성
    int insertComment(Map<String, Object> param);

    // 댓글 수정
    void updateComment(Map<String, Object> param);

    // 댓글 삭제
    int deleteComment(Long id);

    // 댓글 업데이트
    void countComment(Long id);
}
