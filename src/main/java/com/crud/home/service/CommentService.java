package com.crud.home.service;

//import com.crud.home.mapper.CommentMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
@Slf4j
public class CommentService {

//    private final CommentMapper commentMapper;

    // 댓글 조회
    public List<Map<String,Object>> commentList(Long id) {
//        return commentMapper.commentList(id);
        return null;

    }

    // 댓글 작성
    public int insertComment(Map<String, Object> param) {
//        return commentMapper.insertComment(param);
        return 1;
    }

    // 댓글 수정
    public void updateComment(Map<String, Object> param) {
//        commentMapper.updateComment(param);
    }

    // 댓글 삭제
    public int deleteComment(Long id) {
//        return commentMapper.deleteComment(id);
        return 1;
    }

    // 댓글수 업데이트
    public void countComment(Long id) {
//        commentMapper.countComment(id);
    }
}
