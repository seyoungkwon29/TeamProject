package com.controller;

import java.util.HashMap;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.constant.LoginConstant;
import com.dto.MemberDTO;
import com.service.MemberService;

@Controller
public class LoginController {
	
	@Autowired
	MemberService memberService;
	
	@Autowired
	BCryptPasswordEncoder pwdEncoder;
	
	@GetMapping("/login")
	public String loginForm() {
		return "loginForm";
	}
	
	// 로그인
	@PostMapping("/login") // jsp에서 login으로 데이터 전달
	public String login(
			@RequestParam("member_num") int member_num,
			@RequestParam("password") String password,
			Model model,
			HttpSession session) {
		String dbPassword = memberService.getPassword(member_num); // db 비밀번호

		String crytPassword; // 암호화된 비밀번호
		
		// 입력한 비밀번호가 암호화 되어있지 않을 경우 암호화 실행
		if (password.equalsIgnoreCase(dbPassword)) {
			crytPassword = pwdEncoder.encode(dbPassword);
			MemberDTO temp = new MemberDTO();
			temp.setMember_num(member_num);
			temp.setPassword(crytPassword);
			int num = memberService.setCrytPassword(temp);
			if (num != 0) {
				System.out.println("비밀번호 암호화 성공");
			}
		}
		
		boolean pwdEqual = pwdEncoder.matches(password, dbPassword);
		System.out.println("pwdEqual >>>>>>>>>>>" + pwdEqual);
		if (pwdEqual) {
			MemberDTO member = memberService.getMemberById(member_num);
			member.setT_key(UUID.randomUUID().toString());
			session.setAttribute("login", member);
			LoginConstant.memberMap.put(member.getT_key(), member);
			
			return "redirect:/";
		} else {
			session.setAttribute("mesg", "로그인 실패");
		}
		return "redirect:/";
	}
	
	// 로그아웃
	@RequestMapping("/loginCheck/logout")
	public String logout(HttpSession session) {
		LoginConstant.memberMap.remove(((MemberDTO)session.getAttribute("login")).getT_key());
		session.invalidate(); // session 삭제
		return "redirect:../";
	}
	
	// 비밀번호 찾기
	@RequestMapping("/passwordSearch")
	public String passwordSearch() {
		return "passwordSearch";
	}
}
