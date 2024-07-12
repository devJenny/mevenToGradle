package com.crud.home.controller;

import com.crud.home.Entity.Comments;
import com.crud.home.config.auth.PrincipalDetails;
import com.crud.home.domain.CommentReqDto;
import com.crud.home.service.CommentsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@Controller
@Slf4j
@RequestMapping("/comments")
public class CommentsController {

    private final CommentsService commentsService;
    private final SimpMessagingTemplate messagingTemplate;

    // 댓글 작성
    @PostMapping( "/add")
    public Comments commentCreate(@RequestParam(name="boardId") Long boardId, CommentReqDto dto
            , @AuthenticationPrincipal PrincipalDetails principalDetails) {

        Long memberId = principalDetails.getMember().getId();
        Comments comments = commentsService.insertComments(boardId, dto, memberId);
        log.info("commentReqDto = {} " + dto);
        log.info("memberId = {} " + memberId);

        // WebSocket
        String notificationMessage = comments.getBoard().getId() + "번 게시물에 "
                + principalDetails.getMember().getNickname() + " 작성자가 새로운 댓글을 추가했습니다.";
        messagingTemplate.convertAndSend("/topic/comments", notificationMessage);

        return comments;
    }

    // 댓글 조회
    @GetMapping(value = "/list/{boardNo}")
    public List<Comments> commentList(@PathVariable(name = "boardNo") Long boardNo, Model model) throws Exception {
        List<Comments> comments = commentsService.findByAll();
        log.info("출력" + comments);
        model.addAttribute("comment", comments);

        commentsService.countComments(boardNo);

        return comments;
    }

    // 댓글 수정

    // 댓글 삭제

}
