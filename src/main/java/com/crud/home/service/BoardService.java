package com.crud.home.service;

//import com.crud.home.mapper.NoticeMapper;
//import com.github.pagehelper.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
@Slf4j
public class BoardService {

//    private final NoticeMapper noticeMapper;
//    private final CommentMapper commentMapper;

    public int insertBoard(Map<String, Object> param) {
//        return noticeMapper.insertBoard(param);
        return 1;
    }

    public List<Map<String, Object>> findById(Long id) {
//        return noticeMapper.findById(id);
        return null;
    }

//    public List<Map<String, Object>> selectAll(Map<String, Object> param) {
//        return noticeMapper.selectAll(param);
//    }

    public void updateBoard(Map<String, Object> param) {
//        noticeMapper.updateBoard(param);
    }


    public int delete(Long id) {
//        return noticeMapper.delete(id);
        return 1;
    }

    public void hits(Long id) {
//        noticeMapper.hits(id);
    }

//    public Page<List<Map<String, Object>>> selectNoticeList(Map<String, Object> param) {
//        return noticeMapper.selectNoticeList(param);
//    }

    public void countComment(Long id) throws Exception{
//        noticeMapper.countComment(id);
    }

}
