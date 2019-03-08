package kr.or.ddit.board.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.board.model.BoardVo;

public interface IBoardDao {
	/**
	 * Method : getAllBoard
	 * 작성자 : PC07
	 * 변경이력 :
	 * @param sqlSession
	 * @return
	 * Method 설명 : 전체 게시판 조회
	 */
	List<BoardVo> getAllBoard(SqlSession sqlSession);
	
	/**
	 * Method : selectBoard
	 * 작성자 : PC07
	 * 변경이력 :
	 * @param sqlSession
	 * @param board_num
	 * @return
	 * Method 설명 : 특정 게시판 조회
	 */
	BoardVo selectBoard(SqlSession sqlSession, String board_num);
	
	/**
	 * Method : insertBoard
	 * 작성자 : PC07
	 * 변경이력 :
	 * @param sqlSession
	 * @param boardVo
	 * @return
	 * Method 설명 : 게시판 생성
	 */
	int insertBoard(SqlSession sqlSession, BoardVo boardVo);
	
	/**
	 * Method : updateBoard
	 * 작성자 : PC07
	 * 변경이력 :
	 * @param sqlSession
	 * @param boardVo
	 * @return
	 * Method 설명 : 게시판 수정
	 */
	int updateBoard(SqlSession sqlSession, BoardVo boardVo);
}
