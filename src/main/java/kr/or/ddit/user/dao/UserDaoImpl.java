package kr.or.ddit.user.dao;

import kr.or.ddit.user.model.UserVo;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository("userDao")
public class UserDaoImpl implements IUserDao {
	
	/**
	 * Method : selectUser
	 * 작성자 : PC07
	 * 변경이력 :
	 * @param userId
	 * @return
	 * Method 설명 : 사용자 조회
	 */
	@Override
	public UserVo selectUser(SqlSession sqlSession, String userId) {
		UserVo userVo = sqlSession.selectOne("user.selectUser", userId);
		return userVo;
	}
	
}
