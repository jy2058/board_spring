package kr.or.ddit.board.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Service;

import kr.or.ddit.board.dao.BoardDaoImpl;
import kr.or.ddit.board.dao.IBoardDao;
import kr.or.ddit.board.model.BoardVo;
import kr.or.ddit.db.mybatis.MybatisSqlSessionFactory;

@Service("boardService")
public class BoardServiceImpl implements IBoardService {
	
	@Resource(name="boardDao")
	private IBoardDao boardDao;
	
	@Override
	public List<BoardVo> getAllBoard() {
		SqlSessionFactory sqlSessionFactory = MybatisSqlSessionFactory.getSqlSessionFactory();
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		List<BoardVo> boardList = boardDao.getAllBoard(sqlSession);
		
		sqlSession.close();
		return boardList;
	}

	@Override
	public BoardVo selectBoard(String board_num) {
		SqlSessionFactory sqlSessionFactory = MybatisSqlSessionFactory.getSqlSessionFactory();
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		BoardVo boardVo = boardDao.selectBoard(sqlSession, board_num);
		
		sqlSession.close();
		return boardVo;
	}

	@Override
	public int insertBoard(BoardVo boardVo) {
		SqlSessionFactory sqlSessionFactory = MybatisSqlSessionFactory.getSqlSessionFactory();
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		int cnt = boardDao.insertBoard(sqlSession, boardVo);
		
		sqlSession.commit();
		sqlSession.close();
		return cnt;
	}

	@Override
	public int updateBoard(BoardVo boardVo) {
		SqlSessionFactory sqlSessionFactory = MybatisSqlSessionFactory.getSqlSessionFactory();
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		int cnt = boardDao.updateBoard(sqlSession, boardVo);
		
		sqlSession.commit();
		sqlSession.close();
		return cnt;
	}

}
