package kr.or.ddit.board.model;

public class BoardVo {
	private String board_num;
	private String board_name;
	private String use;
	
	public BoardVo() {
		
	}
	public BoardVo(String board_name, String use) {
		super();
		this.board_name = board_name;
		this.use = use;
	}
	public BoardVo(String board_num, String board_name, String use) {
		super();
		this.board_num = board_num;
		this.board_name = board_name;
		this.use = use;
	}
	
	public String getBoard_num() {
		return board_num;
	}
	public void setBoard_num(String board_num) {
		this.board_num = board_num;
	}
	public String getBoard_name() {
		return board_name;
	}
	public void setBoard_name(String board_name) {
		this.board_name = board_name;
	}
	public String getUse() {
		return use;
	}
	public void setUse(String use) {
		this.use = use;
	}
	
	@Override
	public String toString() {
		return "BoardVo [board_num=" + board_num + ", board_name=" + board_name
				+ ", use=" + use + "]";
	}
}
