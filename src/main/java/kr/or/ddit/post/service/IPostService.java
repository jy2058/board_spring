package kr.or.ddit.post.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.post.model.PostVo;
import kr.or.ddit.util.model.PageVo;

public interface IPostService {
	
	/**
	 * Method : getAllPost
	 * 작성자 : PC07
	 * 변경이력 :
	 * @param board_num
	 * @return
	 * Method 설명 : 전체 게시글 조회
	 */
	List<PostVo> getAllPost(String board_num);
	
	/**
	 * Method : selectPost
	 * 작성자 : PC07
	 * 변경이력 :
	 * @param post_num
	 * @return
	 * Method 설명 : 특정 게시글 조회
	 */
	PostVo selectPost(String post_num);

	/**
	 * Method : selectPostPagingList
	 * 작성자 : PC07
	 * 변경이력 :
	 * @param pageVo
	 * @return
	 * Method 설명 : 게시글 페이징 리스트 조회
	 */
	Map<String, Object> selectPostPagingList(PageVo pageVo);
	
	/**
	 * Method : insertPost
	 * 작성자 : PC07
	 * 변경이력 :
	 * @param postVo
	 * @return
	 * Method 설명 : 게시글 등록
	 */
	int insertPost(PostVo postVo);
	
	/**
	 * Method : insertReply
	 * 작성자 : PC07
	 * 변경이력 :
	 * @param postVo
	 * @return
	 * Method 설명 : 답글 등록
	 */
	int insertReply(PostVo postVo);
	
	/**
	 * Method : updatePost
	 * 작성자 : PC07
	 * 변경이력 :
	 * @param postVo
	 * @return
	 * Method 설명 : 게시글 수정
	 */
	int updatePost(PostVo postVo);

	/**
	 * Method : deletePost
	 * 작성자 : PC07
	 * 변경이력 :
	 * @param post_num
	 * @return
	 * Method 설명 : 게시글 삭제
	 */
	int deletePost(String post_num);

}
