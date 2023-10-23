package com.controller.notice;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriUtils;

import com.common.FileStore;
import com.domain.Notice;
import com.dto.CommunityDTO;
import com.dto.UploadFileDTO;
import com.dto.UploadImageResponseDTO;
import com.service.NoticeService;

@Controller
public class NoticeDownloadController {

	@Autowired
	NoticeService noticeService;
	
	@Autowired
	FileStore fileStore;
	
	@GetMapping("/notices/{noticeNum}/files/{filename:.+}")
	public ResponseEntity<Resource> dowunloadFile(@PathVariable Long noticeNum, @PathVariable String filename) throws MalformedURLException, UnsupportedEncodingException {
		filename = UriUtils.decode(filename, StandardCharsets.UTF_8.toString());
		Notice notice = noticeService.getNoticeByNum(noticeNum);
		
		List<UploadFileDTO> files = notice.getFiles();
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

		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
				.body(urlResource);
	}

	@PostMapping("/notices/images")
	public ResponseEntity<UploadImageResponseDTO> uploadImage(@RequestParam("image") MultipartFile multipartFile, HttpServletRequest request) throws IOException {
		
		UploadFileDTO image = fileStore.storeFile(multipartFile);
		
				
		String imageUrl = request.getContextPath() + "/notices/images/" + image.getStoreFilename();
		UploadImageResponseDTO body = new UploadImageResponseDTO("ok", image.getOriginalFilename(), imageUrl);
		
		return ResponseEntity.ok(body);
	}
	
	@GetMapping("/notices/images/{filename:.+}")
	public ResponseEntity<Resource> image(@PathVariable String filename) throws MalformedURLException {
		
		String fullPath = fileStore.getFullPath(filename);

		UrlResource urlResource = new UrlResource("file:" + fullPath);
		
		String contentDisposition = "inline";
		
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
				.body(urlResource);
	}
}
