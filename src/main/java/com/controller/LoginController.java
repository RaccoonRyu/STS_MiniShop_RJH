package com.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dto.MemberDTO;
import com.service.MemberService;

@Controller
public class LoginController {
@Autowired
MemberService service;

@RequestMapping(value = "/loginCheck/logout")
public String logout(HttpSession session) {
	session.invalidate();
	return "redirect:../"; // servlet-context.xml에 설정된 / 주소를 요청해 main.jsp로 이동.
	// ../을 이용하여 /loginCheck의 상위 주소로 이동 후 찾고자 하는 주소를 사용한다.
}

@RequestMapping(value = "/login")
public String login(@RequestParam Map<String, String> map, Model model, HttpSession session) {
	MemberDTO dto = service.login(map);
	System.out.println(map);
	if(dto!= null ) { // 로그인 된 경우
		session.setAttribute("login", dto);
		return "redirect:/goodsList?gCategory=top"; // 로그인시 top 카테고리를 보이도록 작성
	}else {
		model.addAttribute("mesg", "아이디 또는 비번이 잘못되었습니다.");
		return "loginForm";
	}
	
}
}
