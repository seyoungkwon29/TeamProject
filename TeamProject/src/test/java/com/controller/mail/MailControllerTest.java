package com.controller.mail;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.dto.MailDTO;
import com.dto.MemberDTO;
import com.service.MailService;
import com.service.MemberService;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml", 
	"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml", 
	"file:src/main/webapp/WEB-INF/spring/spring-security.xml"})
public class MailControllerTest {
	
	@Autowired
	private WebApplicationContext wac;
	
	@Autowired
	MemberService memberService;
	@Autowired
	MailService mailService;
	
	MemberDTO member;
	
	MockMvc mockMvc;
	
	MockHttpSession httpSession;
	
	MailDTO mail;
	
	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
		
		HashMap<String,String> loginParam = new HashMap<>();
		loginParam.put("member_num", "1001");
		loginParam.put("password", "$2a$10$bQvRUGsll2ZIV5txqMRyquFQby4V/eb7N2uQP2fEXF.W8Lw5uAAUe");
		
		member = memberService.login(loginParam);
		
		httpSession = new MockHttpSession();
		httpSession.setAttribute("login", member);
	}
	
	
	@Test
	public void testSendMail() throws Exception {
		MockMultipartFile attachFile = new MockMultipartFile("mail_file", "테스트파일.txt", MediaType.TEXT_PLAIN_VALUE,"테스트 파일의 내용".getBytes());
		
		this.mockMvc.perform(fileUpload("/sendMail")
				.file(attachFile)
				.param("mail_title", "testMail1")
				.param("mail_content", "aaaaaaaa")
				.param("mail_receiver", "leals8l@naver.com")
				.session(httpSession)
				)
		.andDo(print())
		.andExpect(status().is3xxRedirection())
		.andExpect(redirectedUrl("writeMail"));
	}


}
