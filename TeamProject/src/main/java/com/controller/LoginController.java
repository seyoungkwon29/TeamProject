package com.controller;

import java.util.HashMap;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
	
	private final static Logger log = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	MemberService memberService;
	
	@GetMapping("/login")
	public String loginForm() {
		return "loginForm";
	}
	
	// 로그인
	@PostMapping("/login") // jsp에서 login으로 데이터 전달
	// session 사용할 경우 -> HttpSession import
	public String login(
			@RequestParam HashMap<String, String> loginParam,
			Model model,
			HttpSession session) {
		log.debug("loginParam={}", loginParam);
		MemberDTO member = memberService.login(loginParam);
		
		if (member == null) {
			model.addAttribute("mesg", "아이디 또는 비밀번호가 잘못입력되었습니다.");
			return "loginForm";
		}
		member.setT_key(UUID.randomUUID().toString());
		session.setAttribute("login", member);
		LoginConstant.memberMap.put(member.getT_key(), member);
		
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
