package com.crud.home.controller;

import java.text.SimpleDateFormat;
import java.util.*;

import com.crud.home.service.CommentService;
import com.crud.home.service.BoardService;
//import com.github.pagehelper.Page;
//import com.github.pagehelper.PageHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@Controller
@Slf4j
public class BoardController {

    private final BoardService noticeService;

    private final CommentService commentService;


    @RequestMapping(value = {"/page/noticeList","/page"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String noticeList(@RequestParam Map<String, Object> param, Model model) throws Exception {

        log.info("상세페이지1" + param);
        int pageNum = 0;

        if (param.get("pageNum") != null ) {
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

        model.addAttribute("nowday",nowday);

        return "notice/noticeList";
    }

    /**
     * 게시물 등록 페이지
     *
     * @return
     */
    @GetMapping("/page/noticeResister")
    public String noticeResister() {
        return "notice/resister";
    }

    /**
     * 게시물 등록
     *
     * @param param
     * @return
     */
    @PostMapping("/page/noticeResister")
    public String noticeResister(@RequestParam Map<String, Object> param, Model model) throws Exception {

        int result = noticeService.insertBoard(param);
        log.info("게시물등록" + result);
        model.addAttribute("글쓰기", result);

        return "redirect:/page/noticeList";
    }


    /**
     * 상세 페이지
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/page/{id}")
    public String noticeDetail(@PathVariable Long id,@RequestParam Map<String, Object> param, Model model) throws Exception {
        noticeService.hits(id);
        List<Map<String, Object>> result = noticeService.findById(id);
        model.addAttribute("board", result);
        log.info("상세페이지" + result);


//        // 댓글 조회
//        List<Map<String, Object>> comment = commentService.commentList(id);
//        model.addAttribute("comment", comment);

        noticeService.countComment(id);


        return "/notice/detail";
    }


    @GetMapping("/page/update/{id}")
    public String updateForm(@PathVariable Long id, Model model) {
        List<Map<String, Object>> result = noticeService.findById(id);
        model.addAttribute("updateBoard", result);
        log.info("result" + result);



        return "/notice/update";
    }

    @PostMapping("/page/update")
    public String updateForm(@RequestParam Map<String, Object> param, Model model) {

        noticeService.updateBoard(param);
        log.info("업데이트" + param);
        Long num = Long.parseLong((String) param.get("b_no"));
        List<Map<String, Object>> result = noticeService.findById(num);

        model.addAttribute("board", result);

//        return "redirect:/page/"+param.get("b_no");
        return "notice/detail";
    }


    /**
     * 게시물 삭제
     *
     * @param id
     * @return
     */
    @GetMapping("/page/delete/{id}")
    public String delete(@PathVariable Long id) {
        noticeService.delete(id);
        log.info("게시물 삭제"+id);
        return "redirect:/page/noticeList";
    }


    /**
     * AJAX 요청
     *
     * @return
     * @throws Exception
     */
    @GetMapping(value = "/api/userStatus")
    public ResponseEntity<List<Map<String, Object>>> userStatus() throws Exception {

        Map<String, Object> test = new HashMap<>();

        List<Map<String, Object>> resultList = new ArrayList<>();
        test.put("use_yn", "Y");
        resultList.add(test);
        return new ResponseEntity<>(resultList, HttpStatus.OK);
    }
}
