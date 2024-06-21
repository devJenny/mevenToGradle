package com.crud.home.controller;

import com.crud.home.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@Controller
@Slf4j
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;

    // 댓글 작성
    @PostMapping(value = "/write")
    public String commentWrite(@RequestBody Map<String, Object> param) throws Exception {
        log.info("param = " + param);
        commentService.insertComment(param);

//        Long num = Long.parseLong((String)param.get("b_no"));
//        noticeService.countComment(num);

//        return "redirect:/page/"+param.get("b_no");
        return "InsertSuccess";
    }

    // 댓글 조회
    @GetMapping(value = "/commentList/{boardNo}")
    public List<Map<String, Object>> getList(@PathVariable(name = "boardNo")Long boardNo,Model model ) throws Exception {
        List<Map<String, Object>> comment = commentService.commentList(boardNo);
        log.info("출력" + comment);
        model.addAttribute("comment", comment);

        commentService.countComment(boardNo);

        return comment;
    }

    // 댓글 수정

    // 댓글 삭제

}
