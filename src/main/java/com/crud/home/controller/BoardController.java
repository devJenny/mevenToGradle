package com.crud.home.controller;

import java.time.LocalDateTime;
import java.util.*;

import com.crud.home.Entity.Board;
import com.crud.home.Entity.Comments;
import com.crud.home.config.auth.PrincipalDetails;
import com.crud.home.domain.BoardReqDto;
import com.crud.home.service.CommentsService;
import com.crud.home.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Comment;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
@RequestMapping("/board")
@Slf4j
public class BoardController {

    private final BoardService boardService;

    private final CommentsService commentsService;

    @Comment("게시판 메인")
    @GetMapping("/")
    public String board(Model model, @RequestParam(name="page", defaultValue = "0") int page,
                        @RequestParam(name="size", defaultValue = "10") int size) {

        Page<Board> mainPagination = boardService.getMainPagination(page, size);

        model.addAttribute("board", mainPagination);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", mainPagination.getTotalPages());
        model.addAttribute("nowday", LocalDateTime.now());

        return "board/main";
    }

    @Comment("게시물 등록 페이지")
    @GetMapping("/page")
    public String boardCreate() {
        return "board/resister";
    }

    @Comment("게시물 등록")
    @PostMapping("/page")
    public String boardCreate(BoardReqDto dto, @AuthenticationPrincipal PrincipalDetails principalDetails, Model model) {

        Long memberId = principalDetails.getMember().getId();
        Board board = boardService.insertBoard(dto, memberId);
        model.addAttribute("memberId", memberId);

        log.info("게시물등록 : {}" + board);

        return "redirect:/board/";
    }

    @Comment("상세 페이지")
    @GetMapping("/page/{id}")
    public String boardDetail(@PathVariable("id") Long id, Model model, @AuthenticationPrincipal PrincipalDetails principalDetails)  {

        boardService.hits(id);
        Board result = boardService.findById(id);
        List<Comments> comments = commentsService.commentsByBoardId(id);

        model.addAttribute("board", result);
        model.addAttribute("username", principalDetails.getMember().getId());
        model.addAttribute("usernickname", principalDetails.getMember().getNickname());

        model.addAttribute("comments", comments);

        log.info("상세페이지 : {}" + result);

        return "board/detail";
    }

    @Comment("수정")
    @GetMapping("/page/update/{id}")
    public String boardUpdate(@PathVariable("id") Long id, Model model) {

        Board result = boardService.findById(id);
        log.info("상세페이지" + result);
        model.addAttribute("board", result);

        return "board/update";
    }

    @Comment("게시물 업데이트")
    @PostMapping("/page/update/{id}")
    public String boardUpdate(@PathVariable("id") Long id, BoardReqDto dto, Model model, @AuthenticationPrincipal PrincipalDetails principalDetails) {

        Long memberId = principalDetails.getMember().getId();
        Board result = boardService.updateBoard(id, dto, memberId);
        model.addAttribute("board", result);

//        return "board/detail";
        return "redirect:/board/page/" + id;
    }

    @Comment("게시물 삭제")
    @GetMapping("/page/delete/{id}")
    public String boardDelete(@PathVariable("id") Long id) {
        boardService.deleteBoard(id);
        return "redirect:/board/";
    }

    @Comment("Ajax 요청")
    @GetMapping(value = "/api/userStatus")
    public ResponseEntity<List<Map<String, Object>>> userStatus()  {

        Map<String, Object> test = new HashMap<>();

        List<Map<String, Object>> resultList = new ArrayList<>();
        test.put("use_yn", "Y");
        resultList.add(test);
        return new ResponseEntity<>(resultList, HttpStatus.OK);
    }
}
