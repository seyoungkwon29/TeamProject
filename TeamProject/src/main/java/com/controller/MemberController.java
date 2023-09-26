package com.controller;

import java.io.File;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.dto.MemberDTO;
import com.dto.UploadDTO;
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

		// 프로필업데이트 (휴대폰, 이메일)
		@RequestMapping(value = "/loginCheck/update", method = RequestMethod.POST)
	    public String updatemember(HttpSession session,
	    							@RequestParam("member_num") int member_num, 
	    							@RequestParam("phone") String phone, 
	    							@RequestParam("mail") String mail) {
			MemberDTO dto = (MemberDTO) session.getAttribute("login");
		    System.out.println("멤버 dto"+dto);
		    
		    // 전화번호에서 하이픈 제거
		    System.out.println("폰번호 : "+phone+"메일주소 : "+mail);
		    dto.setPhone(phone);
		    dto.setMail(mail);
		    
	        int dto2 =service.updatemember(member_num, phone, mail);
	        System.out.println("사용자정보업데이트 dto2: "+dto2);
	        
	        if (dto2 != 0) {
	        	session.setAttribute("mesg", "회원정보 변경이 완료되었습니다.");
	        	System.out.println("if");
	        } else { // 정보 변경이 실패했을 때
	        	session.setAttribute("mesg", "회원정보 변경에 실패했습니다. 다시 시도해주세요.");
	        	System.out.println("else");
	        }
	        session.setAttribute("login", dto);
	        return "redirect:../myPage"; // 업데이트 후 이동할 페이지 설정
	    }	

		//비밀번호 변경
	    @RequestMapping(value = "/loginCheck/pwchange", method = {RequestMethod.POST,RequestMethod.GET})
	    public String changePassword(HttpSession session, Model model, 
	            @RequestParam("updatePwd") String passwd,
	            @RequestParam("checkPwd") String passwd2) {
	        MemberDTO dto = (MemberDTO) session.getAttribute("login");
	        System.out.println("멤버 dto"+dto);
	        System.out.println("비밀번호"+passwd+passwd2);
	        
	            dto.setPassword(passwd2);
	            int dto2 = service.pwUpdate(dto);
	            System.out.println("dtototoot"+dto2);
	            
	            if (dto2 != 0) {
	            	session.setAttribute("mesg", "비밀번호 변경이 완료되었습니다.");
	            	System.out.println("if");
	            } else { // 비밀번호 변경이 실패했을 때
	            	session.setAttribute("mesg", "비밀번호 변경에 실패했습니다. 다시 시도해주세요.");
	            	System.out.println("else");
	            }

	            session.setAttribute("login", dto);
	            
	        return "redirect:../myPage";	
	    }


	    //프로필사진 수정
	    @RequestMapping(value = "/loginCheck/profilepic", method = RequestMethod.POST)
	    public String updateImg(HttpSession session , UploadDTO udto)throws Exception {
	    	System.out.println(udto.getMember_num());
			CommonsMultipartFile theFile= udto.getTheFile();//
			System.out.println("업로드된 파일"+theFile);
			
			if (theFile != null) {//파일이  null이 아닐경우 
				try {
					String genId = UUID.randomUUID().toString();
					
					String originalFileName = theFile.getOriginalFilename();
					System.out.println("originalFileName :"+originalFileName);
					
					String onlyFile = originalFileName.substring(0, originalFileName.lastIndexOf("."));
					System.out.println("onlyFile :"+onlyFile);
					
					String extention = FilenameUtils.getExtension(originalFileName);
					System.out.println("extention : "+extention);
					
					String saveFileName = onlyFile + genId.substring(0,3) + "." + extention;
					System.out.println("saveFileName : "+saveFileName);
					
					String uploadDirectory = "c:/profilepic"; //업로드할 디렉토리 경로
				    File saveFile = new File(uploadDirectory, saveFileName);
				    //uploadDirectory는 파일을 저장할 디렉토리를 나타내며, saveFileName은 저장될 파일의 이름
	                theFile.transferTo(saveFile);

	                // 데이터베이스에 파일 이름를 저장
	                System.out.println(saveFileName);
	                String photo = saveFileName;//파일이름만 저장.
	                service.setphoto(udto.getMember_num(), photo);
	                session.setAttribute("mesg", "사진 변경이 완료되었습니다.");
				}catch (Exception e) {
					e.printStackTrace();
					session.setAttribute("mesg", "사진 변경에 실패했습니다. 다시 시도해주세요.");
				}
	    	}
			 return "redirect:myPage";
//			 return "forward:myPage";
	    }
    
}//MemberController
