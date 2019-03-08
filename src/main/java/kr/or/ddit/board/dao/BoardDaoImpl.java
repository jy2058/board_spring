package kr.or.ddit.board.dao;

import java.util.List;

import kr.or.ddit.board.model.BoardVo;

import org.apache.ibatis.session.SqlSession;

public class BoardDaoImpl implements IBoardDao {

	@Override
	public List<BoardVo> getAllBoard(SqlSession sqlSession) {
		List<BoardVo> boardList = sqlSession.selectList("board.getAllBoard");
		return boardList;
	}

	@Override
	public BoardVo selectBoard(SqlSession sqlSession, String board_num) {
		BoardVo boardVo = sqlSession.selectOne("board.selectBoard", board_num);
		return boardVo;
	}

	@Override
	public int insertBoard(SqlSession sqlSession, BoardVo boardVo) {
		int cnt = sqlSession.insert("board.insertBoard", boardVo);
		return cnt;
	}

	@Override
	public int updateBoard(SqlSession sqlSession, BoardVo boardVo) {
		int cnt = sqlSession.update("board.updateBoard", boardVo);
		return cnt;
	}

}
