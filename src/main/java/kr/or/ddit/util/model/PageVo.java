package kr.or.ddit.util.model;

public class PageVo {
	private int page;			// 페이지 번호
	private int pageSize;		// 페이지 사이즈
	private String board_num;	// 게시판 번호
	
	public PageVo() {
		
	}
	public PageVo(String board_num, int page, int pageSize) {
		this.board_num = board_num;
		this.page = page;
		this.pageSize = pageSize;
	}

	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public String getBoard_num() {
		return board_num;
	}
	public void setBoard_num(String board_num) {
		this.board_num = board_num;
	}
	
	@Override
	public String toString() {
		return "PageVo [page=" + page + ", pageSize=" + pageSize
				+ ", board_num=" + board_num + "]";
	}
}
