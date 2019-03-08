package kr.or.ddit.post.dao;

import java.util.List;
import java.util.Map;

import kr.or.ddit.post.model.PostVo;
import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.util.model.PageVo;

import org.apache.ibatis.session.SqlSession;

public class PostDaoImpl implements IPostDao {

	@Override
	public List<PostVo> getAllPost(SqlSession sqlSession, String board_num) {
		List<PostVo> postList = sqlSession.selectList("post.getAllPost", board_num);
		return postList;
	}

	@Override
	public PostVo selectPost(SqlSession sqlSession, String post_num) {
		PostVo postVo = sqlSession.selectOne("post.selectPost", post_num);
		return postVo;
	}
	
	@Override
	public List<PostVo> selectPostPagingList(SqlSession sqlSession,
			PageVo pageVo) {
		List<PostVo> postList = sqlSession.selectList("post.selectPostPagingList", pageVo);
		return postList;
	}

	@Override
	public int insertPost(SqlSession sqlSession, PostVo postVo) {
		int cnt = sqlSession.insert("post.insertPost", postVo);
		return cnt;
	}
	
	@Override
	public int insertReply(SqlSession sqlSession, PostVo postVo) {
		int cnt = sqlSession.insert("post.insertReply", postVo);
		return cnt;
	}

	@Override
	public int updatePost(SqlSession sqlSession, PostVo postVo) {
		int cnt = sqlSession.update("post.updatePost", postVo);
		return cnt;
	}

	@Override
	public int deletePost(SqlSession sqlSession, String post_num) {
		int cnt = sqlSession.delete("post.deletePost", post_num);
		return cnt;
	}

	@Override
	public int getPostCnt(SqlSession sqlSession, String board_num) {
		int postCnt = sqlSession.selectOne("post.getPostCnt", board_num);
		return postCnt;
	}

}
