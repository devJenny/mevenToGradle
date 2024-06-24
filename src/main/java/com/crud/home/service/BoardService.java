package com.crud.home.service;

//import com.github.pagehelper.Page;
import com.crud.home.Entity.Board;
import com.crud.home.Repository.BoardRepository;
import com.crud.home.domain.BoardReqDto;
import com.crud.home.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Comment;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
@Slf4j
public class BoardService {

    private final BoardRepository boardRepository;
    private final BoardMapper boardMapper = BoardMapper.INSTANCE;

    @Comment("게시글 저장")
    public Board insertBoard(BoardReqDto dto) {

        Long memberId = 1L;
        Board board = boardMapper.toEntity(dto);

        board.setId(1L);
        board.setMemberId(memberId);
        board.setHits(2L);

        Board save = boardRepository.save(board);
//        BoardResDto boardResDto = boardMapper.toDtoRes(board);
        return save;
    }

    public List<Board> findByAll() {
//        return noticeMapper.findById(id);


        List<Board> all = boardRepository.findAll();
        return all;

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
