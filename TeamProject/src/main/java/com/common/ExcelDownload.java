package com.common;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.dto.ExcelDTO;

public class ExcelDownload {
	
	public static void createXlsx(List<ExcelDTO> pojoObjectList,  String filename, HttpServletResponse response) {
//		Workbook wb = new HSSFWorkbook();  //xls
		XSSFWorkbook wb = new XSSFWorkbook(); // xlsx
		XSSFSheet sheet = wb.createSheet("첫번째 시트");	//시트
		int rowNum = 0;
		Cell cell =null;
		try {
			if(pojoObjectList.size()>0){
        		Row row = sheet.createRow(0);
        		 // Header
        	      row = sheet.createRow(rowNum++);
        	      cell = row.createCell(0);
        	      cell.setCellValue("no");
        	      cell = row.createCell(1);
        	      cell.setCellValue("글번호");
        	      cell = row.createCell(2);
        	      cell.setCellValue("사원번호");
        	      cell = row.createCell(3);
        	      cell.setCellValue("게시글 제목");

        	      // Body
        	      for (int i=0; i<pojoObjectList.size(); i++) {
        	    	  ExcelDTO dto = pojoObjectList.get(i);
        	          row = sheet.createRow(rowNum++);
        	          cell = row.createCell(0);
        	          cell.setCellValue(i+1);
        	          cell = row.createCell(1);
        	          cell.setCellValue(dto.getExcelNo());
        	          cell = row.createCell(2);
        	          cell.setCellValue(dto.getExcelId());
        	          cell = row.createCell(3);
        	          cell.setCellValue(dto.getExcelTitle());
        	      }
            }else{
            	Row row = sheet.createRow(0);
            	row.createCell(0).setCellValue("데이터가 존재 하지 않습니다.");
            }

			// 컨텐츠 타입과 파일명 지정
			response.setContentType("ms-vnd/excel");
//	        response.setHeader("Content-Disposition", "attachment;filename=example.xls");
			response.setHeader("Content-Disposition", "attachment;filename="+filename);
			response.setCharacterEncoding("UTF-8");
			// Excel File Output
			wb.write(response.getOutputStream());
			wb.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				wb.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
		
}
