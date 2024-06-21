package com.crud.home.controller;

import com.crud.home.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

// 컨트롤러 역할을 하는 클래스를 지정하는 데 사용됨
// 컨트롤러는 클라이언트의 요청을 처리하고, 비즈니스 로직을 수행하여 응답을 생성하는 역할을 담당
// HTTP 요청을 처리할 수 있는 역할을 수행함
//@Controller
@RestController
@Controller
@Slf4j
@RequestMapping("/comment")
public class CommentController {

    @Resource
    CommentService commentService;


    // 댓글 조회
    @GetMapping(value = "/commentList/{boardNo}")
    public List<Map<String, Object>> getList(@PathVariable(name = "boardNo")Long boardNo,Model model ) throws Exception {
        List<Map<String, Object>> comment = commentService.commentList(boardNo);
        log.info("출력" + comment);
        model.addAttribute("comment", comment);

        commentService.countComment(boardNo);

        return comment;
    }

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

    // 댓글 수정

    // 댓글 삭제

}
