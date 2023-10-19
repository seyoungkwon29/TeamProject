package com.controller;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.constant.LoginConstant;
import com.dto.AppDocumentDTO;
import com.dto.ApprovalDTO;
import com.dto.MemberDTO;
import com.dto.NoticeDTO;
import com.service.MemberService;
import com.service.NoticeService;
import com.service.SaveDocFormService;

@Controller
public class LoginController {
	@Autowired
	MemberService service;
	@Autowired
	NoticeService aservice;//공지사항 서비스
	@Autowired
	SaveDocFormService appService;//전자결재 서비스
	
	@RequestMapping("/")
    public String redirect(HttpSession session) {
		System.out.println("호출됨");

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
<<<<<<< HEAD
			HttpSession session,
			AppDocumentDTO doc, ApprovalDTO app) {
		System.out.println(map);
=======
			HttpSession session) {
		System.out.println("login :"+map);
>>>>>>> branch 'main' of https://github.com/seyoungkwon29/TeamProject.git
		MemberDTO dto = service.login(map);
		
		if (dto != null) {
			dto.setT_key(UUID.randomUUID().toString());
			session.setAttribute("login", dto);
			LoginConstant.memberMap.put(dto.getT_key(), dto);
			//결재
			int member_num = (int)((MemberDTO)session.getAttribute("login")).getMember_num(); 			
			System.out.println("memnum>>"+member_num);
			doc.setDoc_status("대기");
			doc.setMember_num(member_num);
			System.out.println("========= doc 확인 : " + doc);
			List<AppDocumentDTO> appDocList = appService.selectHomeAppList(doc);
			System.out.println("============ 홈화면 결재 리스트 : " + appDocList);
			session.setAttribute("appDocList", appDocList); //기안한 문서
			
			//공지사항
			List<NoticeDTO> list =  aservice.getAllNotices(member_num);
			System.out.println("list>>"+list.toString());
			session.setAttribute("HomeNoticelists",list);
			
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
