package kr.or.ddit.attachment.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.attachment.model.AttachmentVo;

public interface IAttachmentDao {
	List<AttachmentVo> getAllFile(SqlSession sqlSession, String post_num);
	
	AttachmentVo selectFile(SqlSession sqlSession, String file_num);
	
	int insertFile(SqlSession sqlSession, AttachmentVo attachmentVo);
	
	int deleteFile(SqlSession sqlSession, String file_num);
}
