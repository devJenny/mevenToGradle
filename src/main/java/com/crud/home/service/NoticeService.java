package com.crud.home.service;

import com.crud.home.mapper.CommentMapper;
import com.crud.home.mapper.NoticeMapper;
import com.github.pagehelper.Page;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class NoticeService {

    @Resource
    NoticeMapper noticeMapper;

    @Resource
    CommentMapper commentMapper;

    public int insertBoard(Map<String, Object> param) {
        return noticeMapper.insertBoard(param);
    }

    public List<Map<String, Object>> findById(Long id) {
        return noticeMapper.findById(id);
    }

//    public List<Map<String, Object>> selectAll(Map<String, Object> param) {
//        return noticeMapper.selectAll(param);
//    }

    public void updateBoard(Map<String, Object> param) {
        noticeMapper.updateBoard(param);
    }


    public int delete(Long id) {
        return noticeMapper.delete(id);
    }

    public void hits(Long id) {
        noticeMapper.hits(id);
    }

    public Page<List<Map<String, Object>>> selectNoticeList(Map<String, Object> param) {
        return noticeMapper.selectNoticeList(param);
    }

    public void countComment(Long id) throws Exception{
        noticeMapper.countComment(id);
    }

}
