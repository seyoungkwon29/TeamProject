package com.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.dto.CommunityDTO;
import com.dto.UploadFileDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
	"file:src/main/webapp/WEB-INF/spring/root-context.xml",
	"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"
})
@WebAppConfiguration
public class CommunityDAOTest {

	@Autowired
	CommunityDAO communityDao;
	
	@Test
	public void testGetFilesByComNum() {
		
		CommunityDTO communityDto = new CommunityDTO(1L, "test-files", "test-files");
		List<UploadFileDTO> uploadFiles = Arrays.asList(
				new UploadFileDTO("cat.png", UUID.randomUUID().toString() + "png"),
				new UploadFileDTO("dog.png", UUID.randomUUID().toString() + "png")
		);		
		communityDto.setFiles(uploadFiles);
		
		communityDao.insert(communityDto);
		for (UploadFileDTO uploadFile : uploadFiles) {
			communityDao.insertFile(communityDto.getComNum(), uploadFile);
		}
		
		List<UploadFileDTO> savedFiles = communityDao.getFilesByComNum(communityDto.getComNum());
		
		assertThat(savedFiles).hasSize(2);
		UploadFileDTO file = savedFiles.get(0);
		assertThat(file.getOriginalFilename()).isNotNull();
		assertThat(file.getStoreFilename()).isNotNull();
	}

}
