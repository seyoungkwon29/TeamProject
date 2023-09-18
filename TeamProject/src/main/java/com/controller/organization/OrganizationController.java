package com.controller.organization;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dto.OrganizationDTO;
import com.service.OrganizationService;

@Controller
public class OrganizationController {

	@Autowired
	OrganizationService service;
	
	// 전체 조직도
	@RequestMapping(value="/organization", method=RequestMethod.GET)
	public String organizationView(Model model){
		List<OrganizationDTO> list = service.viewOrganization();
		
		if(!list.isEmpty()) {
			System.out.println("organizationList >>>" + list);
			model.addAttribute("organizationList", list);
			return "/viewOrganization";
		} else 
			return "#";
	}
	
	
//	//조직도 json 데이터
//	@ResponseBody
//	@RequestMapping(value="/member/organizationData.sw", method=RequestMethod.GET)
//	public String organizationJsonData(){
//		List<Division> oList = mService.printOrganization();
//		if(!oList.isEmpty()) {
//			Gson gson = new Gson();
//			return gson.toJson(oList); // [ {}, {}, .. ]
//		}else {
//			return null;
//		}
//	}
//	//조직도 사원정보
//	@ResponseBody
//	@RequestMapping(value="/member/organizationInfo.sw", method=RequestMethod.GET)
//	public String organizationInfo(
//			Model model
//			, @RequestParam("memNum") String memNum) {
//		Member mOne = mService.printOneById(memNum);
//		if(mOne != null) {
//			//model.addAttribute("mOne", mOne);
//			//return "/member/organizationView";
//			Gson gson = new Gson();
//			return gson.toJson(mOne);	// {}
//		}else {
//			//model.addAttribute("msg", "사원정보 조회 실패");
//			//return "common/errorPage";
//			return null;
//		}
//	}
}
