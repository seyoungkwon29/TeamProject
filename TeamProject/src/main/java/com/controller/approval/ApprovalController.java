package com.controller.approval;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dto.ApprovalDTO;
import com.service.ApprovalService;

@Controller
public class ApprovalController {

	@Autowired
	ApprovalService service;
	
	//ApproverSelect: 결재자, 참조자 사원 정보 출력
	@RequestMapping(value="/approverSelect", method = RequestMethod.GET)
	@ResponseBody
	public List<ApprovalDTO> approverSelect(HttpSession session) {
		
		List<ApprovalDTO> list = service.selectAllMemberInfo();
		System.out.println("결재자 or 참조자 정보 출력 : " + list);
		
		return list; 
	}
	
	//searchMember: 결재자, 참조자 사원 정보 출력
	@RequestMapping(value="/searchMember", method = RequestMethod.GET)
	@ResponseBody
	public List<ApprovalDTO> searchMember(HttpSession session, String searchCondition, String searchValue) {
		
		System.out.println("사원 검색 내용 : " + searchCondition + ", " + searchValue);
		
		List<ApprovalDTO> list = null;
		if(searchCondition.equals("div_name")) {// 조건이 부서인 경우	            
            list = service.searchByDivName(searchValue); //부서명     
		} else {//조건이 이름일 경우     
            list = service.searchByMemName(searchValue); //이름
		}
		System.out.println(list);
		
		return list;
	}
	

}
