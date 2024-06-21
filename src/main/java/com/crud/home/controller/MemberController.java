package com.crud.home.controller;

import com.crud.home.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

//import javax.servlet.http.HttpSession;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

@RequiredArgsConstructor
@Controller
@Slf4j
public class MemberController {


    private final MemberService userService;

    // 회원가입
    @GetMapping("/join")
    public String join() {
        return "user/join";
    }

    @PostMapping("/join")
    public String join(@RequestParam Map<String, Object> param) throws NoSuchAlgorithmException, UnsupportedEncodingException {

        // 암호화
        String pw = (String) param.get("userPassword");
        String sha256 = "";

        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        messageDigest.update(pw.getBytes("UTF-8"));
        byte[] sha256Hash = messageDigest.digest();

        StringBuffer hexSHA256hash = new StringBuffer();

        for (byte b : sha256Hash) {
            String hexString = String.format("%02x", b);
            hexSHA256hash.append(hexString);
        }
        sha256 = hexSHA256hash.toString();

        // 비밀번호만 따로 담기
        param.put("userPassword",sha256);

        log.info("암호화된 비밀번호: " + sha256);

        userService.insertUser(param);
        return "redirect:/page/noticeList";
    }

    // 로그인
    @GetMapping("/login")
    public String login() {

        return "user/login";
    }


//    @PostMapping("/login")
//    @ResponseBody
//    public String login(@RequestParam Map<String, Object> param, Model model, HttpSession session) throws NoSuchAlgorithmException, UnsupportedEncodingException {
//
//        // 암호화
//        String pw = (String) param.get("userPassword");
//        String sha256 = "";
//
//        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
//        messageDigest.update(pw.getBytes("UTF-8"));
//        byte[] sha256Hash = messageDigest.digest();
//
//        StringBuffer hexSHA256hash = new StringBuffer();
//
//        for (byte b : sha256Hash) {
//            String hexString = String.format("%02x", b);
//            hexSHA256hash.append(hexString);
//        }
//        sha256 = hexSHA256hash.toString();
//
//        // 비밀번호만 따로 담기
////        String customerPassword = sha256;
//        param.put("userPassword",sha256);
//
//        // 아이디랑 비번 조회
//        List<Map<String, Object>> customerList = userService.findByUserId(param);
//
//
//        boolean isLogin = false;
//
//        for (Map<String, Object> customer : customerList) {
//            Object userId = customer.get("userid");
//            Object userPassword = customer.get("userPassword");
//
//            if (userId.equals(param.get("userID"))) {
//                if (userPassword.equals(param.get("userPassword"))) {
//                    isLogin = true;
//                    break;
//                } else {
//                    break;
//                }
//            } else {
//                break;
//            }
//        }
//
//        if (isLogin) {
//            System.out.println("로그인");
//        } else {
//            System.out.println("로그인 실패");
//        }
//
//        return "redirect:/page/noticeList";
//    }
}
