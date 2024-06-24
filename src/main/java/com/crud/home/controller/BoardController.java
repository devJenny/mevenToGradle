package com.crud.home.controller;

import java.text.SimpleDateFormat;
import java.util.*;

import com.crud.home.Entity.Board;
import com.crud.home.domain.BoardCreateReqDto;
import com.crud.home.service.CommentService;
import com.crud.home.service.BoardService;
//import com.github.pagehelper.Page;
//import com.github.pagehelper.PageHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Comment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@Controller
@RequestMapping("/board")
@Slf4j
public class BoardController {

    private final BoardService boardService;

    private final CommentService commentService;

    @Comment("게시판 메인")
//    @RequestMapping(value = {"/board","/"}, method = {RequestMethod.GET, RequestMethod.POST})
    @GetMapping("/")
    public String board(@RequestParam Map<String, Object> param, Model model) throws Exception {

//        log.info("상세페이지1" + param);
        int pageNum = 0;

        if (param.get("pageNum") != null) {
            pageNum = Integer.parseInt((String) param.get("pageNum"));
        }

        int pageSize = 10;
        String orderBy = "b_no DESC";
//        Page<List<Map<String, Object>>> notice = new Page<>();
//        PageHelper.startPage(pageNum, pageSize, orderBy);
//
//        notice = noticeService.selectNoticeList(param);
//        log.info("상세페이지2" + notice);


//        model.addAttribute("board", notice);
//        model.addAttribute("pageNum", notice.getPageNum());
//        model.addAttribute("total", notice.getTotal());
//        model.addAttribute("pages", notice.getPages());

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, -1); //7일간 보이도록 하기위해서.
        String nowday = format.format(cal.getTime());

        model.addAttribute("nowday", nowday);

        return "board/main";
    }

    @Comment("게시물 등록 페이지")
    @GetMapping("/page")
    public String boardCreate() {
        return "board/resister";
    }

    @Comment("게시물 등록")
    @PostMapping("/page")
    public String boardCreate(BoardCreateReqDto dto, Model model) throws Exception {

        Board result = boardService.insertBoard(dto);
        log.info("게시물등록 : {}" + result);
        model.addAttribute("글쓰기", result);

        return "redirect:/board/";
    }

    @Comment("상세 페이지")
    @GetMapping("/page/{id}")
    public String boardDetail(@PathVariable Long id, @RequestParam Map<String, Object> param, Model model) throws Exception {
        boardService.hits(id);
        List<Map<String, Object>> result = boardService.findById(id);
        model.addAttribute("board", result);
//        log.info("상세페이지" + result);


//        // 댓글 조회
//        List<Map<String, Object>> comment = commentService.commentList(id);
//        model.addAttribute("comment", comment);

        boardService.countComment(id);

        return "board/detail";
    }


    @Comment("수정")
    @GetMapping("/page/update/{id}")
    public String boardUpdate(@PathVariable Long id, Model model) {
        List<Map<String, Object>> result = boardService.findById(id);
        model.addAttribute("updateBoard", result);
//        log.info("result" + result);

        return "board/update";
    }

    @PostMapping("/page/update")
    public String boardUpdate(@RequestParam Map<String, Object> param, Model model) {

        boardService.updateBoard(param);
//        log.info("업데이트" + param);
        Long num = Long.parseLong((String) param.get("b_no"));
        List<Map<String, Object>> result = boardService.findById(num);

        model.addAttribute("board", result);

//        return "redirect:/page/"+param.get("b_no");
        return "board/detail";
    }


    @Comment("게시물 삭제")
    @DeleteMapping("/page/{id}")
    public String boardDelete(@PathVariable Long id) {
        boardService.delete(id);
//        log.info("게시물 삭제" + id);
        return "redirect:/";
    }

    @Comment("Ajax 요청")
    @GetMapping(value = "/api/userStatus")
    public ResponseEntity<List<Map<String, Object>>> userStatus() throws Exception {

        Map<String, Object> test = new HashMap<>();

        List<Map<String, Object>> resultList = new ArrayList<>();
        test.put("use_yn", "Y");
        resultList.add(test);
        return new ResponseEntity<>(resultList, HttpStatus.OK);
    }
}
