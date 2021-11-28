package com.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dto.MemberDTO;
import com.service.MemberService;

@Controller
public class MemberController {
	@Autowired
	MemberService service; // Service클래스. @Service 사용하여 component-scan으로 자동 생성된 빈 받아옴
	
	@RequestMapping(value = "/idDuplicateCheck", produces="text/plain;charset=UTF-8") // 한글 처리
	@ResponseBody
	public String idDuplicatedCheck(@RequestParam("userid") String userid) {
		MemberDTO dto= service.myPage(userid);
		System.out.println("idDuplicatedCheck====   "+ userid);
		String mesg="아이디 사용가능"; // db에 같은 id가 존재하지 않을 때
		if(dto != null) {
			mesg="아이디 중복";
		}
		return mesg; // 비동기 방식 요청에 대하므로 view 페이지가 아닌 mesg를 문자열로 전송
	}

	@RequestMapping(value = "/loginCheck/memberUpdate")
	public String memberUpdate(MemberDTO m) {
		System.out.println("memberUpdate====="+ m);
		service.memberUpdate(m); // db에 사용자 정보 업데이트
		return "redirect:../loignCheck/myPage"; // db에서 사용자 데이터 가져와 mypage에 출력
	}
	@RequestMapping(value = "/loignCheck/myPage")
	public String myPage(HttpSession session) {
		MemberDTO dto = (MemberDTO)session.getAttribute("login");
		String userid= dto.getUserid();
		dto= service.myPage(userid);
		session.setAttribute("login", dto);
		System.out.println("mypage함수 호출 =======");
		return "redirect:../myPage"; // 주소에 해당하는 페이지를 servlet-context에 등록 후 사용
	}
	@RequestMapping(value = "/memberAdd")
	public String memberAdd(MemberDTO m,Model model) {
		service.memberAdd(m);
		model.addAttribute("success", "회원가입성공"); // main.jsp에서 success라는 키로 회원가입성공 검사
		return "main";
	}
}
