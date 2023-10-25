package com.controller.notice;

import static org.junit.Assert.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.queryParam;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.mock.web.MockMultipartFile;

import com.dao.MemberDAO;
import com.domain.Notice;
import com.dto.MemberDTO;
import com.service.MemberService;
import com.service.NoticeService;


@RunWith(SpringJUnit4ClassRunner.class)

@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml", "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
@WebAppConfiguration
public class NoticeControllerTest {

	@Autowired
	WebApplicationContext wac;
	
	@Autowired
	MemberService memberService;
	
	@Autowired
	NoticeService noticeService;
	
	MockMvc mockMvc;
	
	MockHttpSession httpSession;
	
	MemberDTO member;
	
	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
		
		HashMap<String, String> loginParam = new HashMap<>();
		loginParam.put("member_num", "1001");
		loginParam.put("password", "1001");
		member = memberService.login(loginParam);
		
		httpSession = new MockHttpSession();
		httpSession.setAttribute("login", member);
	}
	
	@Test
	public void postNewNotice() throws Exception {
		this.mockMvc.perform(post("/notices/new")
				.param("title", "test-title")
				.param("content", "test-content")
				.session(httpSession))
			.andDo(print())
			.andExpect(status().is3xxRedirection())
			.andExpect(redirectedUrlPattern("/notices/**"));
	}
	
	@Test
	public void postNewNoticeWithMultipartFile() throws Exception {
		MockMultipartFile attachFile1 = new MockMultipartFile("attachFiles", "test.txt", MediaType.TEXT_PLAIN_VALUE, "테스트 파일입니다.".getBytes());
		
		this.mockMvc.perform(fileUpload("/notices/new")
				.file(attachFile1)
				.param("title", "test-title")
				.param("content", "test-content")
				.session(httpSession))
			.andDo(print())
			.andExpect(status().is3xxRedirection())
			.andExpect(redirectedUrlPattern("/notices/**"));
		
	}
	
	@Test
	public void getNoticeDetails() throws Exception {
		Notice notice = new Notice(getMemberNum(),"test-111", "test-1111");
		
		noticeService.createNotice(getMemberNum(), notice);
		
		Long noticeNum = notice.getNoticeNum();
		
		this.mockMvc.perform(get("/notices/{noticeNum}", noticeNum).session(httpSession))
			.andDo(print())
			.andExpect(status().isOk());
	}
	
	private Long getMemberNum() {
		return Long.valueOf(member.getMember_num());
	}
} 
