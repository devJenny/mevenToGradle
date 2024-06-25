package com.crud.home.controller;

import java.text.SimpleDateFormat;
import java.util.*;

import com.crud.home.Entity.Board;
import com.crud.home.domain.BoardReqDto;
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
    public String board(Model model) throws Exception {

        List<Board> result = boardService.findByAll();
        model.addAttribute("board", result);

//        log.info("상세페이지1" + param);
        int pageNum = 0;

//        if (param.get("pageNum") != null) {
//            pageNum = Integer.parseInt((String) param.get("pageNum"));
//        }

        int pageSize = 10;
        String orderBy = "b_no DESC";
//        Page<List<Map<String, Object>>> notice = new Page<>();
//        PageHelper.startPage(pageNum, pageSize, orderBy);
//
//        notice = noticeService.selectNoticeList(param);
//        log.info("상세페이지2" + notice);

//        model.addAttribute("pageNum", notice.getPageNum());
//        model.addAttribute("total", notice.getTotal());
//        model.addAttribute("pages", notice.getPages());

//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//        Calendar cal = Calendar.getInstance();
//        cal.add(Calendar.DAY_OF_MONTH, -1); //7일간 보이도록 하기위해서.
//        String nowday = format.format(cal.getTime());
//
//        model.addAttribute("nowday", nowday);

        return "board/main";
    }

    @Comment("게시물 등록 페이지")
    @GetMapping("/page")
    public String boardCreate() {
        return "board/resister";
    }

    @Comment("게시물 등록")
    @PostMapping("/page")
    public String boardCreate(BoardReqDto dto, Model model) {

        Board board = boardService.insertBoard(dto);
        log.info("게시물등록 : {}" + board);
        model.addAttribute("글쓰기", board);

        return "redirect:/board/";
    }

    @Comment("상세 페이지")
    @GetMapping("/page/{id}")
    public String boardDetail(@PathVariable("id") Long id, Model model)  {
//        boardService.hits(id);
        Board result = boardService.findById(id);
        log.info("상세페이지" + result);
        model.addAttribute("board", result);

//        // 댓글 조회
//        List<Map<String, Object>> comment = commentService.commentList(id);
//        model.addAttribute("comment", comment);

//        boardService.countComment(id);

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
    @PostMapping("/page/update")
    public String boardUpdate(BoardReqDto dto, Model model) {

        Board result = boardService.updateBoard(dto);
        model.addAttribute("board", result);

        return "board/detail";
    }

    @Comment("게시물 삭제")
    @GetMapping("/page/delete/{id}")
    public String boardDelete(@PathVariable("id") Long id) {
        boardService.deleteBoard(id);
        return "redirect:/board/";
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
