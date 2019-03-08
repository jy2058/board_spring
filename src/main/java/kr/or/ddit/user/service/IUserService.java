package kr.or.ddit.user.service;

import kr.or.ddit.user.model.UserVo;

public interface IUserService {

	/**
	 * Method : selectUser
	 * 작성자 : PC07
	 * 변경이력 :
	 * @param userId
	 * @return
	 * Method 설명 : 사용자 조회
	 */
	UserVo selectUser(String userId);

}
