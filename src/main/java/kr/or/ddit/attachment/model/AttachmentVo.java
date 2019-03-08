package kr.or.ddit.attachment.model;

public class AttachmentVo {
	private String file_num;
	private String filename;
	private String realfilename;
	private String post_num;
	
	public String getFile_num() {
		return file_num;
	}
	public void setFile_num(String file_num) {
		this.file_num = file_num;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getRealfilename() {
		return realfilename;
	}
	public void setRealfilename(String realfilename) {
		this.realfilename = realfilename;
	}
	public String getPost_num() {
		return post_num;
	}
	public void setPost_num(String post_num) {
		this.post_num = post_num;
	}
	
	@Override
	public String toString() {
		return "AttachmentVo [file_num=" + file_num + ", filename=" + filename
				+ ", realfilename=" + realfilename + ", post_num=" + post_num
				+ "]";
	}
}
