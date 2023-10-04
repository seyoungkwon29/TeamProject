package com.controller.mail;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;


@Component("fileDownView")
public class FileDownView extends AbstractView{
	
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, 
    			HttpServletRequest request,
				HttpServletResponse response) throws Exception {
		
		String fileName = (String)model.get("fileName");
		String fileReName = (String)model.get("fileReName");
		String filePath = (String)model.get("filePath");
		
		File mailFile = new File(filePath+"/"+fileReName);
		
        // 파일명으로 한글 사용 시 설정
		fileName = URLEncoder.encode(fileName, "utf-8");
        
        // Http Response Header 설정
		response.setContentType(this.getContentType());
		response.setContentLength((int)mailFile.length());
		
		response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
		response.setHeader("Content-Transfer-Encoding", "binary;");	// 이진수로 저장
		response.setHeader("Content-Length", "" + mailFile.length());
		response.setHeader("Pragma", "no-cache;"); 	// 캐시로 저장 안함
		response.setHeader("Expires", "-1;");		// 기한 없음
		
		OutputStream os = response.getOutputStream();
		FileInputStream fis = new FileInputStream(mailFile);
		
		// 실제 데이터 기입 (os에 복사)
		FileCopyUtils.copy(fis, os);
		
		if(fis != null) {
			fis.close();
		}
	}
}