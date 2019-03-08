package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.board.model.BoardVo;

public interface IBoardService {
	/**
	 * Method : getAllBoard
	 * 작성자 : PC07
	 * 변경이력 :
	 * @return
	 * Method 설명 : 전체 게시판 조회
	 */
	List<BoardVo> getAllBoard();
	
	/**
	 * Method : selectBoard
	 * 작성자 : PC07
	 * 변경이력 :
	 * @param board_num
	 * @return
	 * Method 설명 : 특정 게시판 조회
	 */
	BoardVo selectBoard(String board_num);
	
	/**
	 * Method : insertBoard
	 * 작성자 : PC07
	 * 변경이력 :
	 * @param boardVo
	 * @return
	 * Method 설명 : 게시판 생성
	 */
	int insertBoard(BoardVo boardVo);
	
	/**
	 * Method : updateBoard
	 * 작성자 : PC07
	 * 변경이력 :
	 * @param boardVo
	 * @return
	 * Method 설명 : 게시판 수정
	 */
	int updateBoard(BoardVo boardVo);
}
