package com.controller;

import java.io.File;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
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
	@Autowired // 암호화 기능 API 추가
	BCryptPasswordEncoder pwdEncoder;
	
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
    							@RequestParam(value = "member_num", required = true) int member_num, 
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
	
	//비밀번호 변경 - 변경할 때 비밀번호 암호화 (spring security)
    @RequestMapping(value = "/loginCheck/pwchange", method = {RequestMethod.POST,RequestMethod.GET})
    public String changePassword(HttpSession session, Model model, 
            @RequestParam("updatePwd") String passwd,
            @RequestParam("checkPwd") String passwd2) {
		MemberDTO dto = (MemberDTO) session.getAttribute("login");
		System.out.println("멤버 dto" + dto);
		System.out.println("비밀번호" + passwd + passwd2);
		
		// 비밀번호 암호화
		String crytPassword = pwdEncoder.encode(passwd2);
		dto.setPassword(crytPassword);
		
		int num = service.pwUpdate(dto);
		System.out.println("비밀번호 변경된 계정 수 : " + num);

		if (num != 0) {
			session.setAttribute("mesg", "비밀번호 변경이 완료되었습니다.");
		} else { // 비밀번호 변경이 실패했을 때
			session.setAttribute("mesg", "비밀번호 변경에 실패했습니다. 다시 시도해주세요.");
		}

		session.setAttribute("login", dto);

		return "redirect:../myPage";
	}
    
    //프로필사진 수정
    @RequestMapping(value = "/loginCheck/profilepic", method = RequestMethod.POST)
    public String updateImg(HttpSession session, HttpServletRequest request, UploadDTO udto)throws Exception {
    	System.out.println(udto.getMember_num());
		CommonsMultipartFile theFile = udto.getTheFile();//
		System.out.println("업로드된 파일"+theFile);
		
		if (theFile != null) {//파일이  null이 아닐경우 
			try {
				String genId = UUID.randomUUID().toString();
				
				String originalFileName = theFile.getOriginalFilename();
				System.out.println("originalFileName : " + originalFileName);
				
				String onlyFile = originalFileName.substring(0, originalFileName.lastIndexOf("."));
				System.out.println("onlyFile : " + onlyFile);
				
				String extention = FilenameUtils.getExtension(originalFileName);
				System.out.println("extention : " + extention);
				
				String saveFileName = onlyFile + genId.substring(0,3); 
				System.out.println("saveFileName : " + saveFileName);
					
				String uploadDirectory = "C:/upload/member"; //업로드할 디렉토리 경로
				
				File directory = new File(uploadDirectory);
				if (!directory.exists()) {
					boolean created = directory.mkdirs();
					if (created) {
						System.out.println("업로드 디렉토리가 생성되었습니다.");
					} else {
						System.out.println("업로드 디렉토리 생성에 실패했습니다.");
					}
				}

			    File saveFile = new File(uploadDirectory, saveFileName + "." + extention);
                theFile.transferTo(saveFile);

                // 데이터베이스에 파일 이름를 저장
                System.out.println("saveFileName : " + saveFileName);
                service.setphoto(udto.getMember_num(), saveFileName);
                session.setAttribute("mesg", "사진 변경이 완료되었습니다.");
			}catch (Exception e) {
				e.printStackTrace();
				session.setAttribute("mesg", "사진 변경에 실패했습니다. 다시 시도해주세요.");
			}
    	}
		 return "redirect:myPage";
	}
    
    /* 비밀번호 찾기 */
    @RequestMapping(value = "findpw", method = RequestMethod.GET)
    public void findPwGET() throws Exception{
    }

    @RequestMapping(value = "findpw", method = RequestMethod.POST)
    public void findPwPOST(@ModelAttribute MemberDTO member, HttpServletResponse response) throws Exception{
    	service.findPw(response, member);
    	
    }
    
    
    
    
    
}//MemberController
