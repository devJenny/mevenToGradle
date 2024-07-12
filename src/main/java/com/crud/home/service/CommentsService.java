package com.crud.home.service;

import com.crud.home.Entity.Board;
import com.crud.home.Entity.Comments;
import com.crud.home.Entity.Member;
import com.crud.home.Repository.BoardRepository;
import com.crud.home.Repository.CommentsRepository;
import com.crud.home.Repository.MemberRepository;
import com.crud.home.domain.CommentReqDto;
import com.crud.home.mapper.CommentsMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Comment;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
@Slf4j
public class CommentsService {

    private final CommentsRepository commentsRepository;
    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;


    private final CommentsMapper commentsMapper = CommentsMapper.INSTANCE;

    @Comment("댓글 작성")
    public Comments insertComments(Long boardId, CommentReqDto dto, Long memberId) {
        Comments comments = commentsMapper.toEntity(dto);

        Member member = memberRepository.findById(memberId).orElseThrow(() -> new IllegalArgumentException("Not Found Member"));
        Long confirmMemberId = member.getId();

        Board boardIdOriginal = boardRepository.findById(boardId).orElseThrow(() -> new EntityNotFoundException("게시물을 찾을 수 없습니다"));
        Long boardIds = boardIdOriginal.getId();

        comments.setBoard(Board.builder().id(boardIds).build());
        comments.setComments(dto.getComments());
        comments.setMember(Member.builder().id(confirmMemberId).build());

        Comments save = commentsRepository.save(comments);


        return save;
    }

    @Comment("댓글 수정")
    public void updateComments(Map<String, Object> param) {
//        commentMapper.updateComment(param);
    }

    @Comment("댓글 전체 조회")
    public List<Comments> findByAll() {
        List<Comments> comments = commentsRepository.findAll();
        return comments;
    }

    @Comment("댓글 내 게시물 조회")
    public List<Comments> commentsByBoardId(Long boardId) {
        return commentsRepository.findByBoardId(boardId);
    }

    @Comment("댓글 삭제")
    public int deleteComments(Long id) {
//        return commentMapper.deleteComment(id);
        return 1;
    }

    @Comment("댓글 조회수 없데이트")
    public void countComments(Long id) {
//        commentMapper.countComment(id);
    }

}
