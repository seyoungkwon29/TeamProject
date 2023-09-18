package com.controller.approval;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dto.DocFormDTO;
import com.dto.SaveDocFormDTO;
import com.service.DocFormService;

@Controller
public class DrafrListController {
	
	@Autowired
	DocFormService service;

	
	//DocFormName: 기안 문서 선택
	@RequestMapping("/DocFormName")
	public String DocFormName( @RequestParam("form_name") String form_name, HttpSession session) {
		DocFormDTO docForm = service.docFormName(form_name); //문서 이름 보내고, 데이터 가져오기	
		System.out.println("문서 종류 출력 : " + docForm);
				
		session.setAttribute("form", docForm);
		
		return "app_docForm";
	}
	
	
	@RequestMapping("/tempList")
	public String tempList() {
		
		return "app_tempList";
	}
	
	@RequestMapping("/appList")
	public String appList() {
		
		return "app_appList";
	}
}
