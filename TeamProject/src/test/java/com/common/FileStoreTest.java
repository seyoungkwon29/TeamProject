package com.common;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.dto.UploadFileDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
	"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
	"file:src/main/webapp/WEB-INF/spring/root-context.xml"
})
@WebAppConfiguration
public class FileStoreTest {
	
	@Autowired
	private FileStore fileStore;

	@Test
	public void testStoreFile() throws IOException {
		MockMultipartFile mockMultipartFile = new MockMultipartFile("image", "file.txt", MediaType.TEXT_PLAIN_VALUE, "hello".getBytes());
		UploadFileDTO uploadFileDTO = fileStore.storeFile(mockMultipartFile);
		String storeFilename = uploadFileDTO.getStoreFilename();
		
		File file = new File(fileStore.getFullPath(storeFilename));
		assertThat(file.exists()).isTrue();
		assertThat(file.getName()).isEqualTo(storeFilename);
		
		assertThat(uploadFileDTO.getOriginalFilename()).isEqualTo("file.txt");
	}

}
