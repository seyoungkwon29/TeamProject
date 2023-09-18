package com.controller.approval;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dto.SaveDocFormDTO;
import com.service.SaveDocFormService;

@Controller
public class SavaDocFormController {
	
	@Autowired
	SaveDocFormService service;
	
	//전자결재 메뉴를 클릭했을 때: 기안한 문서가 뜨도록 설정
	@RequestMapping("/draftList")
	public String draftList( SaveDocFormDTO formDto, HttpSession session ) {
		
		List<SaveDocFormDTO> docList = service.saveDocList();
		System.out.println("기안한 문서 목록 : " + docList);
		
		session.setAttribute("docList", docList); //저장한 문서 list
		
		return "app_draftList";
	}

	
	//SaveDocForm
	@RequestMapping("/SaveDocForm")
	public String SaveDocForm( SaveDocFormDTO formDto, HttpSession session ) {

		System.out.println("문서 저장 : " + formDto);
        int num = service.saveDocForm(formDto);
        
        System.out.println("문서 저장 성공 개수 ! " + num);
			
        session.setAttribute("form", formDto);
		
		return "redirect: draftList"; // /draftList 주소로 다시 가서, 기안 문서가 뜨도록 해줌
	}
	
	

}
