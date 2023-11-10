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
	
	//페이징처리를 위한 변수 저장
	private void setPaging(PageDTO pageDTO, List<MailDTO> list, int allListSize) {
		pageDTO.setMailDTOList(list);
		pageDTO.setListCnt(allListSize);
	}
	
	//DB에 보낸 메일정보를 저장
	public int sendMail(MailDTO mailDto) {;
		//저장결과 확인
		int res = session.insert("sendMail",mailDto);
		return res;
	}
	
	
	//자신을 제외한 모든 멤버리스트
	public List<MemberDTO> selectAllMemberListExceptMe(int user_num) {
		List<MemberDTO> list = session.selectList("selectAllMemberListExceptMe",user_num);
		return list;
	}

	//메일주소 리스트로 멤버번호 리스트 찾기
	public List<Integer> findMemberNumByMailAddress(String[] addressList) {
		List<Integer> rec_numList = new ArrayList<>();
		for(int i=0; i<addressList.length; i++) {
			String email = addressList[i];
			int rec_num = session.selectOne("findMemberNumByMailAddress",email);
			rec_numList.add(rec_num);
		}
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
		List<MailDTO> allList = session.selectList("receiveMailList", member_num);
		List<MailDTO> list = session.selectList("receiveMailList", member_num, new RowBounds(offset, pageDTO.getListSize()));
		setPaging(pageDTO, list, allList.size());
		
		return pageDTO;
	}

	public PageDTO sentMailList(int member_num, String page) {
		PageDTO pageDTO = new PageDTO();
		pageDTO.setPage(Integer.parseInt(page));
		int offset = (pageDTO.getPage()-1)*pageDTO.getListSize();
		//전체 메일개수 확인용 
		List<MailDTO> allList = session.selectList("sentMailList", member_num);
		List<MailDTO> list = session.selectList("sentMailList", member_num, new RowBounds(offset,pageDTO.getListSize()));
		setPaging(pageDTO, list, allList.size());
		
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
		List<MailDTO> allList = session.selectList("selfMailList", member_num);
		List<MailDTO> list = session.selectList("selfMailList", member_num, new RowBounds(offset, pageDTO.getListSize()));
		setPaging(pageDTO, list, allList.size());
		
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

	public List<MailDTO> homeReceiveMailList(int member_num) {
		List<MailDTO> receiveList = session.selectList("homeReceiveMailList", member_num);
		return receiveList;
	}

	public List<MailDTO> countMailNotReading(int member_num) {
		List<MailDTO> list = session.selectList("countMailNotReading",member_num);
		System.out.println("조회결과 : " + list.toString());
		return list;
	}

	
	
}
