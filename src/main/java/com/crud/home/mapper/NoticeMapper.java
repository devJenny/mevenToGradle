package com.crud.home.mapper;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface NoticeMapper {

    // 게시물 조회
    Page<List<Map<String, Object>>> selectNoticeList(Map<String, Object> param);
//    List<Map<String, Object>> selectAll(Map<String, Object> param);

    // 게시글 조회
    List<Map<String, Object>> findById(Long id);

    // 게시글 작성
    int insertBoard(Map<String, Object> param);

    // 게시물 수정
    void updateBoard(Map<String, Object> param);

    // 게시물 삭제
    int delete(Long id);

    // 게시물 조회수
    void hits(Long id);

    // 게시물 댓글 조회
    void countComment(Long id);

}
