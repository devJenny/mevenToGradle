package com.crud.home.service;

//import com.github.pagehelper.Page;
import com.crud.home.Entity.Board;
import com.crud.home.Repository.BoardRepository;
import com.crud.home.Repository.MemberRepository;
import com.crud.home.config.auth.PrincipalDetails;
import com.crud.home.domain.BoardReqDto;
import com.crud.home.mapper.BoardMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Comment;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
public class BoardService {

    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;
    private final BoardMapper boardMapper = BoardMapper.INSTANCE;

    @Comment("게시글 등록")
    public Board insertBoard(BoardReqDto dto, @AuthenticationPrincipal PrincipalDetails principalDetails) {

        Board board = boardMapper.toEntity(dto);
        Long memberId = principalDetails.getMember().getId();
        log.info("memberId: {}", memberId);

        board.setMemberId(null);
        board.setHits(2L);

        Board save = boardRepository.save(board);

        return save;
    }

    @Comment("전체 게시글 조회")
    public List<Board> findByAll() {

        List<Board> all = boardRepository.findAll();
        return all;

    }

    @Comment("id 조회")
    public Board findById(Long id) {

        Optional<Board> byId = boardRepository.findById(id);
        Board board = byId.orElseThrow(() -> new EntityNotFoundException("게시물을 찾을 수 없습니다"));

        return board;

    }

    @Comment("게시물 수정")
    public Board updateBoard(BoardReqDto dto, PrincipalDetails principalDetails) {

        Board board = boardMapper.toEntity(dto);
        Long memberId = 1L;

//        board.setMemberId(memberId);
        board.setHits(2L);

        Board save = boardRepository.save(board);

        return save;

    }

    @Comment("게시물 사제")
    public void deleteBoard(Long id) {
        boardRepository.deleteById(id);
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
