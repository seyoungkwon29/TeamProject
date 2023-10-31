package com.dto;

import org.apache.ibatis.type.Alias;

@Alias("ExcelDTO")
public class ExcelDTO {
	private int excelNo; //글번호
	private int excelId; //사원번호
	private String excelMemeberName;	//사원명
	private String excelTitle; //게시글 제목
	private String startTime;
	private String endTime;
	
	public ExcelDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ExcelDTO(int excelNo, int excelId, String excelMemeberName, String excelTitle) {
		super();
		this.excelNo = excelNo;
		this.excelId = excelId;
		this.excelMemeberName = excelMemeberName;
		this.excelTitle = excelTitle;
	}




	public int getExcelNo() {
		return excelNo;
	}

	public void setExcelNo(int excelNo) {
		this.excelNo = excelNo;
	}

	public int getExcelId() {
		return excelId;
	}

	public void setExcelId(int excelId) {
		this.excelId = excelId;
	}

	public String getMemeberName() {
		return excelMemeberName;
	}

	public void setMemeberName(String excelMemeberName) {
		this.excelMemeberName = excelMemeberName;
	}

	public String getExcelTitle() {
		return excelTitle;
	}

	public void setExcelTitle(String excelTitle) {
		this.excelTitle = excelTitle;
	}
	
	
}
