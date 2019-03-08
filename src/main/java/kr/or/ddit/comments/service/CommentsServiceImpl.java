package kr.or.ddit.comments.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.comments.dao.CommentsDaoImpl;
import kr.or.ddit.comments.dao.ICommentsDao;
import kr.or.ddit.comments.model.CommentsVo;
import kr.or.ddit.db.mybatis.MybatisSqlSessionFactory;

public class CommentsServiceImpl implements ICommentsService {

	private ICommentsDao commentsDao;
	
	public CommentsServiceImpl() {
		commentsDao = new CommentsDaoImpl();
	}

	@Override
	public List<CommentsVo> getAllComment(String post_num) {
		SqlSessionFactory sqlSessionFactory = MybatisSqlSessionFactory.getSqlSessionFactory();
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		List<CommentsVo> commentList = commentsDao.getAllComment(sqlSession, post_num);
		
		sqlSession.close();
		return commentList;
	}

	@Override
	public int insertComment(CommentsVo commentsVo) {
		SqlSessionFactory sqlSessionFactory = MybatisSqlSessionFactory.getSqlSessionFactory();
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		int cnt = commentsDao.insertComment(sqlSession, commentsVo);
		
		sqlSession.commit();
		sqlSession.close();
		return cnt;
	}

	@Override
	public int deleteComment(String comment_num) {
		SqlSessionFactory sqlSessionFactory = MybatisSqlSessionFactory.getSqlSessionFactory();
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		int cnt = commentsDao.deleteComment(sqlSession, comment_num);
		
		sqlSession.commit();
		sqlSession.close();
		return cnt;
	}

}
