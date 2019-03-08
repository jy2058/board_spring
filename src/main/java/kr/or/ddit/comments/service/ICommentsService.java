package kr.or.ddit.comments.service;

import java.util.List;

import kr.or.ddit.comments.model.CommentsVo;

public interface ICommentsService {

	List<CommentsVo> getAllComment(String post_num);
	
	int insertComment(CommentsVo commentsVo);
	
	int deleteComment(String comment_num);
	
}
