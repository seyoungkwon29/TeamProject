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
	
}
