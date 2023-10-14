package com.controller;

import java.util.HashMap;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.constant.LoginConstant;
import com.dto.MemberDTO;
import com.service.MemberService;

@Controller
public class LoginController {
	@Autowired
	MemberService service;
	
	@RequestMapping("/")
    public String redirect(HttpSession session) {
        MemberDTO dto = (MemberDTO) session.getAttribute("login");
        if (dto != null) {
            return "homePage";
        } else {
            return "loginForm";
        }
    }
	
	// 로그인
	@RequestMapping("login") // jsp에서 login으로 데이터 전달
	// session 사용할 경우 -> HttpSession import
	public String login(
			@RequestParam HashMap<String, String> map,
			Model model,
			HttpSession session) {
		System.out.println(map);
		MemberDTO dto = service.login(map);
		
		if (dto != null) {
			dto.setT_key(UUID.randomUUID().toString());
			session.setAttribute("login", dto);
			LoginConstant.memberMap.put(dto.getT_key(), dto);
			return "homePage";
		} else {
			model.addAttribute("mesg", "아이디 또는 비밀번호가 잘못입력되었습니다.");
			return "loginForm";
		}
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
