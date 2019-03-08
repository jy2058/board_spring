package kr.or.ddit.comments.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.comments.model.CommentsVo;

public class CommentsDaoImpl implements ICommentsDao {

	@Override
	public List<CommentsVo> getAllComment(SqlSession sqlSession, String post_num) {
		List<CommentsVo> commentList = sqlSession.selectList("comments.getAllComment", post_num);
		return commentList;
	}

	@Override
	public int insertComment(SqlSession sqlSession, CommentsVo commentsVo) {
		int cnt = sqlSession.insert("comments.insertComment", commentsVo);
		return cnt;
	}

	@Override
	public int deleteComment(SqlSession sqlSession, String comment_num) {
		int cnt = sqlSession.update("comments.deleteComment", comment_num);
		return cnt;
	}
	
}
