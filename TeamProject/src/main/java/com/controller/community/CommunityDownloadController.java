package com.controller.community;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.util.UriUtils;

import com.common.FileStore;
import com.dto.CommunityDTO;
import com.dto.UploadFileDTO;
import com.dto.UploadImageResponseDTO;
import com.service.CommunityService;

@Controller
public class CommunityDownloadController {

	private final static Logger log = LoggerFactory.getLogger(CommunityDownloadController.class);
	
	private final CommunityService communityService;
	private final FileStore fileStore;

	
	public CommunityDownloadController(CommunityService communityService, FileStore fileStore) {
		this.communityService = communityService;
		this.fileStore = fileStore;
	}
	
	
	
	@GetMapping("/communities/{comNum}/files/{filename:.+}")
	public ResponseEntity<Resource> dowunloadFile(@PathVariable Long comNum, @PathVariable String filename) throws MalformedURLException, UnsupportedEncodingException {
		filename = UriUtils.decode(filename, StandardCharsets.UTF_8.toString());
		CommunityDTO community = communityService.getCommunityByNum(comNum);
		
		List<UploadFileDTO> files = community.getFiles();
		UploadFileDTO file = null;
		for (UploadFileDTO uploadFile : files) {
			if(uploadFile.getOriginalFilename().equals(filename)) {
				file = uploadFile;
			}
		}
		
		if (file == null) {
			return ResponseEntity.notFound().build();
		}
		
		//경로
		UrlResource urlResource = new UrlResource("file:"+ fileStore.getFullPath(file.getStoreFilename()));
		
		String encodedOriginalFileName = UriUtils.encode(file.getOriginalFilename(), StandardCharsets.UTF_8.toString());
		String contentDisposition =  "attachment; filename=\"" + encodedOriginalFileName + "\"";

		log.debug("DownloadFile=[{}][{}]", filename, fileStore.getFullPath(file.getStoreFilename()));
		log.debug("EncodedDownloadFile=[{}][{}]", encodedOriginalFileName, fileStore.getFullPath(file.getStoreFilename()));
		
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
				.body(urlResource);
	}

	@PostMapping("/communities/images")
	public ResponseEntity<UploadImageResponseDTO> uploadImage(@RequestParam("image") MultipartFile multipartFile, HttpServletRequest request) throws IOException {
		
		UploadFileDTO image = fileStore.storeFile(multipartFile);
		
		log.debug("POST`/communities/images` [{}][{}]", image.getOriginalFilename(), fileStore.getFullPath(image.getStoreFilename()));
				
		String imageUrl = request.getContextPath() + "/communities/images/" + image.getStoreFilename();
		UploadImageResponseDTO body = new UploadImageResponseDTO("ok", image.getOriginalFilename(), imageUrl);
		
		return ResponseEntity.ok(body);
	}
	
	@GetMapping("/communities/images/{filename:.+}")
	public ResponseEntity<Resource> image(@PathVariable String filename) throws MalformedURLException {
		
		String fullPath = fileStore.getFullPath(filename);

		UrlResource urlResource = new UrlResource("file:" + fullPath);
		log.debug("GET`/communities/images` [{}][{}]", filename, fullPath);
		
		String contentDisposition = "inline";
		
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
				.body(urlResource);
	}
}
