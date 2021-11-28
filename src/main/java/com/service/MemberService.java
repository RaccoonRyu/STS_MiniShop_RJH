package com.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.MemberDAO;
import com.dto.MemberDTO;

@Service // component-scan으로 빈 생성
public class MemberService {
	@Autowired
	MemberDAO dao; // @Repository 사용하여 component-scan으로 빈 생성
	
	public void memberAdd(MemberDTO m) {
		dao.memberAdd(m);
	}

	public MemberDTO login(Map<String, String> map) {
		MemberDTO dto = dao.login(map); 
		return dto;
	}

	public MemberDTO myPage(String userid) {
		MemberDTO dto= dao.myPage(userid);
		return dto;
	}

	public void memberUpdate(MemberDTO m) {
		dao.memberUpdate(m);		
	}
}
