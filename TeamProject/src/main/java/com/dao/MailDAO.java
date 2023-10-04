package com.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dto.MailDTO;
import com.dto.MailRecDTO;
import com.dto.MemberDTO;
import com.dto.PageDTO;

@Repository("MailDAO")
public class MailDAO {
	@Autowired
	SqlSessionTemplate session;
	
	//DB에 보낸 메일정보를 저장
	public int mailSendProcess1(MailDTO mailDto) {;
		
		session.insert("mailSendProcess",mailDto);
		
		//가장 최근에 저장된 이메일 고유번호
		int recentEmailNum = session.selectOne("recentEmailNum");
		return recentEmailNum;
	}
	
	//이메일주소를 사용한 사용자 정보 찾기
	public MemberDTO selectByEmail(String email) {
		MemberDTO receiver = session.selectOne("selectByEmail",email);
		return receiver;
	}
	
	//자신을 제외한 모든 멤버리스트
	public List<MemberDTO> selectAllMemberListExceptMe(int user_num) {
		List<MemberDTO> list = session.selectList("selectAllMemberListExceptMe",user_num);
		return list;
	}

	//이메일들을 사용해서 멤버번호 리스트 찾기
	public List<Integer> findMemberNumByEmail(String[] addressList) {
		List<Integer> rec_numList = new ArrayList<>();
		for(int i=0; i<addressList.length; i++) {
			String email = addressList[i];
			int rec_num = session.selectOne("findMemberNumByEmail",email);
			rec_numList.add(rec_num);
		}
		System.out.println(rec_numList.toString());
		return rec_numList;
	}

	public int insertReceiveTable(MailRecDTO mailRecDTO) {
		int res = session.insert("insertReceiveTable",mailRecDTO);
		return res;
	}

	public PageDTO receiveMailList(int member_num, String page) {
		PageDTO pageDTO = new PageDTO();
		pageDTO.setPage(Integer.parseInt(page));
		int offset = (pageDTO.getPage()-1)*pageDTO.getListSize();
		//전체 메일개수 확인용 
		List<MailDTO> tempList = session.selectList("receiveMailList", member_num);
		List<MailDTO> list = session.selectList("receiveMailList", member_num, new RowBounds(offset, pageDTO.getListSize()));
		pageDTO.setMailDTOList(list);
		pageDTO.setListCnt(tempList.size());
		
		return pageDTO;
	}

	public PageDTO sentMailList(int member_num, String page) {
		PageDTO pageDTO = new PageDTO();
		pageDTO.setPage(Integer.parseInt(page));
		int offset = (pageDTO.getPage()-1)*pageDTO.getListSize();
		
		//전체 메일개수 확인용 
		List<MailDTO> tempList = session.selectList("sentMailList", member_num);
		List<MailDTO> list = session.selectList("sentMailList", member_num, new RowBounds(offset,pageDTO.getListSize()));
		pageDTO.setMailDTOList(list);
		pageDTO.setListCnt(tempList.size());
		
		return pageDTO;
	}

	public List<MailRecDTO> selectMailRecDTOByMailNum(int mail_num) {
		List<MailRecDTO> list = session.selectList("selectMailRecDTOByMailNum",mail_num);
		return list;
	}

	public MailDTO selectMailDTOByMailNum(int mail_num) {
		MailDTO mailDTO = session.selectOne("selectMailDTOByMailNum",mail_num);
		return mailDTO;
	}
	
	public PageDTO selfMailList(int member_num, String page) {
		PageDTO pageDTO = new PageDTO();
		pageDTO.setPage(Integer.parseInt(page));
		int offset = (pageDTO.getPage()-1)*pageDTO.getListSize();
		
		//전체 메일개수 확인용 
		List<MailDTO> tempList = session.selectList("selfMailList", member_num);
		List<MailDTO> list = session.selectList("selfMailList", member_num, new RowBounds(offset, pageDTO.getListSize()));
		pageDTO.setMailDTOList(list);
		pageDTO.setListCnt(tempList.size());
		
		return pageDTO;
	}

	public void checkMail(MailRecDTO mailRecDTO) {
		int res = session.update("checkMail_rec_status",mailRecDTO);
	}

	public MailRecDTO selectMailRecDTOByMailNumAndMemberNum(MailRecDTO mailRecDto) {
		MailRecDTO mailRecDTO = session.selectOne("selectMailRecDTOByMailNumAndMemberNum",mailRecDto);
		return mailRecDTO;
	}

	public void deleteRecMail(MailRecDTO mailRecDTO) {
		int res = session.delete("deleteRecMail",mailRecDTO);
	}

	
	
}
