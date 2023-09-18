package com.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.dto.MemberDTO;
import com.service.MemberService;

@Controller
public class MemberController {
	@Autowired
	MemberService service;
	
	// 마이페이지
	@RequestMapping("/loginCheck/myPage")
	public String myPage(HttpSession session) {
		MemberDTO dto = (MemberDTO) session.getAttribute("login");
		dto = service.myPage(dto.getMember_num());
		System.out.println("mypage dto >>>>" + dto);
		session.setAttribute("login", dto);
		return "redirect:../myPage"; // servlet-context.xml -> myPage
	}

    @RequestMapping(value = "/loginCheck/pwchange", method = {RequestMethod.POST,RequestMethod.GET})
    public String changePassword(HttpSession session, Model model, 
            @RequestParam("userPwd") String passwd,
            @RequestParam("updatePwd") String passwd2) {
        MemberDTO dto = (MemberDTO) session.getAttribute("login");
        System.out.println("멤버 dto"+dto);
        if (dto != null) {
            MemberDTO changepw = new MemberDTO();
            changepw.setPassword(passwd2);
            System.out.println(changepw);

            int dto2 = service.pwUpdate(changepw);
            System.out.println(dto2);
            if (dto2 != 0) {
                session.setAttribute("login", dto2);
            }

            return "myPage";
        } else {
            return "loginForm";
        }
    }
	

    
    
}//MemberController
