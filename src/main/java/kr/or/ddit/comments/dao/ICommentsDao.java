package kr.or.ddit.comments.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.comments.model.CommentsVo;

public interface ICommentsDao {
	
	List<CommentsVo> getAllComment(SqlSession sqlSession, String post_num);
	
	int insertComment(SqlSession sqlSession, CommentsVo commentsVo);
	
	int deleteComment(SqlSession sqlSession, String comment_num);
	
}
