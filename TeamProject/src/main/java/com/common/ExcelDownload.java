package com.common;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.dto.ExcelDTO;
import com.dto.ProjectDTO;

public class ExcelDownload {
	
	public static void createXlsx(List<ExcelDTO> pojoObjectList, String filename, HttpServletResponse response) {
//		Workbook wb = new HSSFWorkbook();  //xls
		XSSFWorkbook wb = new XSSFWorkbook(); // xlsx
		XSSFSheet sheet = wb.createSheet("fileboard Sheet"); // 시트
		int rowNum = 0;
		Cell cell = null;
		try {
			if (pojoObjectList.size() > 0) {
				Row row = sheet.createRow(0);
				// Header
				row = sheet.createRow(rowNum++);
				cell = row.createCell(0);
				cell.setCellValue("no");
				cell = row.createCell(1);
				cell.setCellValue("사원번호");
				cell = row.createCell(2);
				cell.setCellValue("게시글 제목");

				// Body
				for (int i = 0; i < pojoObjectList.size(); i++) {
					ExcelDTO dto = pojoObjectList.get(i);
					row = sheet.createRow(rowNum++);
					cell = row.createCell(0);
					cell.setCellValue(i + 1);
					cell = row.createCell(1);
					cell.setCellValue(dto.getExcelId());
					cell = row.createCell(2);
					cell.setCellValue(dto.getExcelTitle());
				}

			} else {
				Row row = sheet.createRow(0);
				row.createCell(0).setCellValue("데이터가 존재 하지 않습니다.");
			}

			// 컨텐츠 타입과 파일명 지정
			response.setContentType("ms-vnd/excel");
//	        response.setHeader("Content-Disposition", "attachment;filename=example.xls");
			response.setHeader("Content-Disposition", "attachment;filename=" + filename);
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

	// todo
	public static void excelProjectTodo(List<ProjectDTO> projectList ,List<ExcelDTO> pojoObjectList, String filename, HttpServletResponse response) { // todo
		XSSFWorkbook wb = new XSSFWorkbook(); // xlsx
		XSSFSheet sheet = wb.createSheet("todo Sheet"); // 시트
		 ProjectDTO rtnDTO = new ProjectDTO();
		int rowNum = 0;
		Cell cell = null;
		Row row=null;
		try {// project
			if(projectList.size() >0) {
				// Header
				row = sheet.createRow(rowNum++);
				List<String> list = excelHeaderCellMaker(1);
				for (int i = 0; i < list.size(); i++) {					
					cell = row.createCell(i);
					cell.setCellValue(list.get(i));
				}
				
				// body
				for (int i = 0; i < projectList.size(); i++) {
					ProjectDTO dto = projectList.get(i);
					row = sheet.createRow(rowNum++);
					cell = row.createCell(0);
					cell.setCellValue(i + 1);
					cell = row.createCell(1);
					cell.setCellValue(dto.getProject_num());
					cell = row.createCell(2);  
					cell.setCellValue(dto.getProject_title());
					cell = row.createCell(3);
					cell.setCellValue(dto.getStatus());
					cell = row.createCell(4);
					cell.setCellValue(dto.getStart_date());
					cell = row.createCell(5);
					cell.setCellValue(dto.getDue_date());
					cell = row.createCell(6);
					cell.setCellValue(dto.getProject_manager()+"("+dto.getMember_num()+")");
					
				}

			} else {
				row.createCell(0).setCellValue("Project 데이터가 존재 하지 않습니다.");
			}
			
			// todo
			if (pojoObjectList.size() > 0) {
				// Header
				row = sheet.createRow(rowNum++);
				List<String> list = excelHeaderCellMaker(2);
				for (int i = 0; i < list.size(); i++) {
					
					cell = row.createCell(i);
					cell.setCellValue(list.get(i));
				}
				// body
				for (int i = 0; i < pojoObjectList.size(); i++) {
					ExcelDTO dto = pojoObjectList.get(i);
					row = sheet.createRow(rowNum++);
					cell = row.createCell(0);
					cell.setCellValue(i + 1);
					cell = row.createCell(1);
					cell.setCellValue(dto.getExcelTitle());
					cell = row.createCell(2);
					cell.setCellValue(dto.getMemeberName());
					cell = row.createCell(3);
					cell.setCellValue(dto.getExcelsStartDate());
					cell = row.createCell(4);
					cell.setCellValue(dto.getExcelDueDate());
					cell = row.createCell(5);
					cell.setCellValue(dto.getExcelContent());
				}
			} else {
				row.createCell(0).setCellValue("Todo 데이터가 존재 하지 않습니다.");
			}

			// 컨텐츠 타입과 파일명 지정
			response.setContentType("ms-vnd/excel");
//	        response.setHeader("Content-Disposition", "attachment;filename=example.xls");
			response.setHeader("Content-Disposition", "attachment;filename=" + filename);
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

	public static List<String> excelHeaderCellMaker(int type) { // 몸통
		List<String> header = new ArrayList<String>();
		if (type == 0) {
			//fileboard header
		}
		if (type == 1) {	//proejct header
			header.add("no");
			header.add("project no");
			header.add("project title");
			header.add("project 상태");
			header.add("시작일");
			header.add("종료일");
			header.add("프로젝트 생성자");
		}
		if (type == 2) {	//todo header
			header.add("no");
			header.add("title");
			header.add("member");
			header.add("start time");
			header.add("end time");
			header.add("content");
		}
		System.out.println(header.toString());
		return header;
	}
}
