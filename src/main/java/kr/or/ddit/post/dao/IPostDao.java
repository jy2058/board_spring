package kr.or.ddit.post.dao;

import java.util.List;

import kr.or.ddit.post.model.PostVo;
import kr.or.ddit.util.model.PageVo;

import org.apache.ibatis.session.SqlSession;

public interface IPostDao {
	
	/**
	 * Method : getAllPost
	 * 작성자 : PC07
	 * 변경이력 :
	 * @param sqlSession
	 * @param board_num
	 * @return
	 * Method 설명 : 전체 게시글 조회
	 */
	List<PostVo> getAllPost(SqlSession sqlSession, String board_num);
	
	/**
	 * Method : selectPost
	 * 작성자 : PC07
	 * 변경이력 :
	 * @param sqlSession
	 * @param post_num
	 * @return
	 * Method 설명 : 특정 게시글 조회
	 */
	PostVo selectPost(SqlSession sqlSession, String post_num);

	/**
	 * Method : selectPostPagingList
	 * 작성자 : PC07
	 * 변경이력 :
	 * @param sqlSession
	 * @param pageVo
	 * @return
	 * Method 설명 : 게시글 페이징 리스트 조회
	 */
	List<PostVo> selectPostPagingList(SqlSession sqlSession, PageVo pageVo);
	
	/**
	 * Method : insertPost
	 * 작성자 : PC07
	 * 변경이력 :
	 * @param sqlSession
	 * @param postVo
	 * @return
	 * Method 설명 : 게시글 등록
	 */
	int insertPost(SqlSession sqlSession, PostVo postVo);
	
	/**
	 * Method : insertReply
	 * 작성자 : PC07
	 * 변경이력 :
	 * @param sqlSession
	 * @param postVo
	 * @return
	 * Method 설명 : 답글 등록
	 */
	int insertReply(SqlSession sqlSession, PostVo postVo);
	
	/**
	 * Method : updatePost
	 * 작성자 : PC07
	 * 변경이력 :
	 * @param sqlSession
	 * @param postVo
	 * @return
	 * Method 설명 : 게시글 수정
	 */
	int updatePost(SqlSession sqlSession, PostVo postVo);

	/**
	 * Method : deletePost
	 * 작성자 : PC07
	 * 변경이력 :
	 * @param sqlSession
	 * @param post_num
	 * @return
	 * Method 설명 : 게시글 삭제
	 */
	int deletePost(SqlSession sqlSession, String post_num);

	/**
	 * Method : getPostCnt
	 * 작성자 : PC07
	 * 변경이력 :
	 * @param sqlSession
	 * @param board_num
	 * @return
	 * Method 설명 : 특정 게시판 전체 게시글 수 조회
	 */
	int getPostCnt(SqlSession sqlSession, String board_num);

}
