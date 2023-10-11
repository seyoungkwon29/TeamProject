package com.common;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.dto.UploadFileDTO;

@Component
public class FileStore {

	private final static Logger log = LoggerFactory.getLogger(FileStore.class); 
	
	@Value("${file.basedir}")
	private String basedir;
	
	public String getFullPath(String filename) {
		return basedir + filename;
	}
	
	public List<UploadFileDTO> storeFiles(List<MultipartFile> multipartFiles) throws IOException {
		List<UploadFileDTO> result = new ArrayList<>();
		
		for (MultipartFile multipartFile : multipartFiles) {
			if (!multipartFile.isEmpty()) {
				UploadFileDTO storeFile = storeFile(multipartFile);
				result.add(storeFile);
			}
		}
		return result;
	}
	
	public UploadFileDTO storeFile(MultipartFile multipartFile) throws IOException {
		if (multipartFile.isEmpty()) {
			return null;
		}
		
		String originalFilename = multipartFile.getOriginalFilename();
		String storeFilename = createStoreFilename(originalFilename);
		File file = new File(getFullPath(storeFilename));
		
		multipartFile.transferTo(file);
		
		UploadFileDTO uploadFileDTO = new UploadFileDTO(originalFilename, storeFilename);
		log.debug("Save [{}] On [{}]", originalFilename, getFullPath(storeFilename));
		return uploadFileDTO;		
	}

	private String createStoreFilename(String originalFilename) {
		String uuid = UUID.randomUUID().toString();
		String ext = extractExtension(originalFilename);
		return uuid + "." + ext;
	}

	private String extractExtension(String originalFilename) {
		int idx = originalFilename.lastIndexOf(".");
		String ext = originalFilename.substring(idx+1);
		return ext;
	}
	
}
