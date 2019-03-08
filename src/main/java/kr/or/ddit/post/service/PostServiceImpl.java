package kr.or.ddit.post.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.db.mybatis.MybatisSqlSessionFactory;
import kr.or.ddit.post.dao.IPostDao;
import kr.or.ddit.post.dao.PostDaoImpl;
import kr.or.ddit.post.model.PostVo;
import kr.or.ddit.util.model.PageVo;

public class PostServiceImpl implements IPostService {
	
	private IPostDao postDao;
	
	public PostServiceImpl() {
		postDao = new PostDaoImpl();
	}

	@Override
	public List<PostVo> getAllPost(String board_num) {
		SqlSessionFactory sqlSessionFactory = MybatisSqlSessionFactory.getSqlSessionFactory();
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		List<PostVo> postList = postDao.getAllPost(sqlSession, board_num);
		
		sqlSession.close();
		return postList;
	}

	@Override
	public PostVo selectPost(String post_num) {
		SqlSessionFactory sqlSessionFactory = MybatisSqlSessionFactory.getSqlSessionFactory();
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		PostVo postVo = postDao.selectPost(sqlSession, post_num);
		
		sqlSession.close();
		return postVo;
	}

	@Override
	public int insertPost(PostVo postVo) {
		SqlSessionFactory sqlSessionFactory = MybatisSqlSessionFactory.getSqlSessionFactory();
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		int cnt = postDao.insertPost(sqlSession, postVo);

		sqlSession.commit();
		sqlSession.close();
		return cnt;
	}
	
	@Override
	public int insertReply(PostVo postVo) {
		SqlSessionFactory sqlSessionFactory = MybatisSqlSessionFactory.getSqlSessionFactory();
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		int cnt = postDao.insertReply(sqlSession, postVo);

		sqlSession.commit();
		sqlSession.close();
		return cnt;
	}

	@Override
	public int updatePost(PostVo postVo) {
		SqlSessionFactory sqlSessionFactory = MybatisSqlSessionFactory.getSqlSessionFactory();
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		int cnt = postDao.updatePost(sqlSession, postVo);

		sqlSession.commit();
		sqlSession.close();
		return cnt;
	}

	@Override
	public int deletePost(String post_num) {
		SqlSessionFactory sqlSessionFactory = MybatisSqlSessionFactory.getSqlSessionFactory();
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		int cnt = postDao.deletePost(sqlSession, post_num);

		sqlSession.commit();
		sqlSession.close();
		return cnt;
	}

	@Override
	public Map<String, Object> selectPostPagingList(PageVo pageVo) {
		SqlSessionFactory sqlSessionFactory = MybatisSqlSessionFactory.getSqlSessionFactory();
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		resultMap.put("postList", postDao.selectPostPagingList(sqlSession, pageVo));
		resultMap.put("postCnt", postDao.getPostCnt(sqlSession, pageVo.getBoard_num()));
		
		sqlSession.close();
		return resultMap;
	}

}
