package com.dto;

import org.apache.ibatis.type.Alias;

@Alias("ExcelDTO")
public class ExcelDTO {
	private int excelNo; //글번호
	private int excelId; //사원번호
	private String memeberName;	//사원명 member_name
	private String excelTitle; //게시글 제목, todo_title
	private String excelContent; //content
	private String excelStatus;	 //상태
	private String excelsStartDate;	//todo 시작일
	private String excelDueDate;	//todo 끝일

	public ExcelDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ExcelDTO(int excelNo, int excelId, String memeberName, String excelTitle, String excelContent,
			String excelStatus, String excelsStartDate, String excelDueDate) {
		super();
		this.excelNo = excelNo;
		this.excelId = excelId;
		this.memeberName = memeberName;
		this.excelTitle = excelTitle;
		this.excelContent = excelContent;
		this.excelStatus = excelStatus;
		this.excelsStartDate = excelsStartDate;
		this.excelDueDate = excelDueDate;
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
		return memeberName;
	}
	public void setMemeberName(String memeberName) {
		this.memeberName = memeberName;
	}
	public String getExcelTitle() {
		return excelTitle;
	}
	public void setExcelTitle(String excelTitle) {
		this.excelTitle = excelTitle;
	}
	public String getExcelContent() {
		return excelContent;
	}
	public void setExcelContent(String excelContent) {
		this.excelContent = excelContent;
	}
	public String getExcelStatus() {
		return excelStatus;
	}
	public void setExcelStatus(String excelStatus) {
		this.excelStatus = excelStatus;
	}
	public String getExcelsStartDate() {
		return excelsStartDate;
	}
	public void setExcelsStartDate(String excelsStartDate) {
		this.excelsStartDate = excelsStartDate;
	}
	public String getExcelDueDate() {
		return excelDueDate;
	}
	public void setExcelDueDate(String excelDueDate) {
		this.excelDueDate = excelDueDate;
	}
	
}
