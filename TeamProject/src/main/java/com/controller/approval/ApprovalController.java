package com.controller.approval;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dto.SearchMemberDTO;
import com.service.SearchAppMemService;

@Controller
public class ApprovalController {

	@Autowired
	SearchAppMemService service;
	
	//전체 멤버 정보 출력(결재자, 참조자 사원 정보)
	@RequestMapping(value="/approverSelect", method = RequestMethod.GET)
	@ResponseBody
	public List<SearchMemberDTO> approverSelect(HttpSession session) {
		
		List<SearchMemberDTO> list = service.selectAllMemberInfo();
		System.out.println("결재자 or 참조자 정보 출력 : " + list);
		
		return list; 
	}
	
	//조건 검색 하여 재자, 참조자 사원 정보 출력
	@RequestMapping(value="/searchMember", method = RequestMethod.GET)
	@ResponseBody
	public List<SearchMemberDTO> searchMember(HttpSession session, String searchCondition, String searchValue) {
		
		List<SearchMemberDTO> list = null;
		if(searchCondition.equals("div_name")) {// 조건이 부서인 경우	            
            list = service.searchByDivName(searchValue); //부서명     
		} else {//조건이 이름일 경우     
            list = service.searchByMemName(searchValue); //이름
		}
		System.out.println(list);
		
		return list;
	}
	

}
