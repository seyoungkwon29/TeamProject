package com.dto;

public class AttachVO{
	private int atch_no; /* 첨부파일 번호(PK) */
	private int atch_parent_no; /* 부모글의 PK */
	private String atch_category; /* 상위글 분류(BOARD, FREE, QNA, PDS 등) */
	private String atch_file_name; /* 실제 저장된 파일명 */
	private String atch_original_name; /* 사용자가 올린 원래 파일명 */
	private long atch_file_size; /* 파일 사이즈   1024,  1024*1024 */
	private String atch_fancy_size; /* 팬시 사이즈  : 1KB , 1MB   */
	private String atch_content_type; /* 컨텐츠 타입 */
	private String atch_path; /* 저장 경로(board/2020) */
	private int atch_down_hit; /* 다운로드 횟수 */
	private String atch_del_yn; /* 삭제여부(스케쥴에 의해서 파일삭제처리) */
	private String atch_reg_date; /* 등록일 */

	public AttachVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AttachVO(int atch_no, int atch_parent_no, String atch_category, String atch_file_name,
			String atch_original_name, long atch_file_size, String atch_fancy_size, String atch_content_type,
			String atch_path, int atch_down_hit, String atch_del_yn, String atch_reg_date) {
		super();
		this.atch_no = atch_no;
		this.atch_parent_no = atch_parent_no;
		this.atch_category = atch_category;
		this.atch_file_name = atch_file_name;
		this.atch_original_name = atch_original_name;
		this.atch_file_size = atch_file_size;
		this.atch_fancy_size = atch_fancy_size;
		this.atch_content_type = atch_content_type;
		this.atch_path = atch_path;
		this.atch_down_hit = atch_down_hit;
		this.atch_del_yn = atch_del_yn;
		this.atch_reg_date = atch_reg_date;
	}
	
	public int getAtch_no() {
		return atch_no;
	}

	public void setAtch_no(int atch_no) {
		this.atch_no = atch_no;
	}

	public int getAtch_parent_no() {
		return atch_parent_no;
	}

	public void setAtch_parent_no(int atch_parent_no) {
		this.atch_parent_no = atch_parent_no;
	}

	public String getAtch_category() {
		return atch_category;
	}

	public void setAtch_category(String atch_category) {
		this.atch_category = atch_category;
	}

	public String getAtch_file_name() {
		return atch_file_name;
	}

	public void setAtch_file_name(String atch_file_name) {
		this.atch_file_name = atch_file_name;
	}

	public String getAtch_original_name() {
		return atch_original_name;
	}

	public void setAtch_original_name(String atch_original_name) {
		this.atch_original_name = atch_original_name;
	}

	public long getAtch_file_size() {
		return atch_file_size;
	}

	public void setAtch_file_size(long atch_file_size) {
		this.atch_file_size = atch_file_size;
	}

	public String getAtch_fancy_size() {
		return atch_fancy_size;
	}

	public void setAtch_fancy_size(String atch_fancy_size) {
		this.atch_fancy_size = atch_fancy_size;
	}

	public String getAtch_content_type() {
		return atch_content_type;
	}

	public void setAtch_content_type(String atch_content_type) {
		this.atch_content_type = atch_content_type;
	}

	public String getAtch_path() {
		return atch_path;
	}

	public void setAtch_path(String atch_path) {
		this.atch_path = atch_path;
	}

	public int getAtch_down_hit() {
		return atch_down_hit;
	}

	public void setAtch_down_hit(int atch_down_hit) {
		this.atch_down_hit = atch_down_hit;
	}

	public String getAtch_del_yn() {
		return atch_del_yn;
	}

	public void setAtch_del_yn(String atch_del_yn) {
		this.atch_del_yn = atch_del_yn;
	}

	public String getAtch_reg_date() {
		return atch_reg_date;
	}

	public void setAtch_reg_date(String atch_reg_date) {
		this.atch_reg_date = atch_reg_date;
	}

	@Override
	public String toString() {
		return "AttachVO [atch_no=" + atch_no + ", atch_parent_no=" + atch_parent_no + ", atch_category="
				+ atch_category + ", atch_file_name=" + atch_file_name + ", atch_original_name=" + atch_original_name
				+ ", atch_file_size=" + atch_file_size + ", atch_fancy_size=" + atch_fancy_size + ", atch_content_type="
				+ atch_content_type + ", atch_path=" + atch_path + ", atch_down_hit=" + atch_down_hit + ", atch_del_yn="
				+ atch_del_yn + ", atch_reg_date=" + atch_reg_date + "]\t";
	}
	
    	
    
}