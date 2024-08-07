package com.crud.home.service;

import com.crud.home.Entity.Board;
import com.crud.home.Entity.Member;
import com.crud.home.Repository.BoardRepository;
import com.crud.home.Repository.CommentsRepository;
import com.crud.home.Repository.MemberRepository;
import com.crud.home.domain.BoardReqDto;
import com.crud.home.mapper.BoardMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
public class BoardService {

    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;
    private final CommentsRepository commentsRepository;
    private final BoardMapper boardMapper = BoardMapper.INSTANCE;

    @Comment("게시글 등록")
    public Board insertBoard(BoardReqDto dto, Long memberId) {

        Board board = boardMapper.toEntity(dto);

        Member member = memberRepository.findById(memberId).orElseThrow(() -> new IllegalArgumentException("Not Found Member"));
        Long confirmMemberId = member.getId();

        System.out.println("@@@@@@@@@@@@@@@" + Member.builder().id(confirmMemberId).build());
        board.setMember(Member.builder().id(confirmMemberId).build());

        board.setHits(0L);

        Board save = boardRepository.save(board);

        return save;
    }

    @Comment("게시물 수정")
    @Transactional
    public Board updateBoard(Long id, BoardReqDto dto, Long memberId) {

        Board board = boardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid board ID: " + id));

        Member member = memberRepository.findById(memberId).orElseThrow(() -> new IllegalArgumentException("Not Found Member"));
        Long confirmMemberId = member.getId();

        board.setId(board.getId());
        board.setMember(Member.builder().id(confirmMemberId).build());
        board.setTitle(dto.getTitle());
        board.setContent(dto.getContent());
        board.setUpdatedDate(LocalDateTime.now());

        Board save = boardRepository.save(board);

        return save;
    }

    @Comment("게시물 삭제")
    public void deleteBoard(Long id) {
        Board byId = findById(id);
        Long id1 = byId.getId();

        commentsRepository.deleteByBoardId(id1);
        boardRepository.deleteById(id);

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

    @Transactional
    public void hits(Long id) {
        boardRepository.countBoard(id);
    }

    @Comment("pagenation")
    public Page<Board> getMainPagination(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return boardRepository.findAll(pageRequest);
    }

    public void countComment(Long id) {
//        noticeMapper.countComment(id);
    }

}
