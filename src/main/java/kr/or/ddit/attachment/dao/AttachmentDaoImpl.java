package kr.or.ddit.attachment.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import kr.or.ddit.attachment.model.AttachmentVo;

@Repository("attachmentDao")
public class AttachmentDaoImpl implements IAttachmentDao {

	@Override
	public List<AttachmentVo> getAllFile(SqlSession sqlSession, String post_num) {
		List<AttachmentVo> fileList = sqlSession.selectList("attachment.getAllFile", post_num);
		return fileList;
	}
	
	@Override
	public AttachmentVo selectFile(SqlSession sqlSession, String file_num) {
		AttachmentVo attachmentVo = sqlSession.selectOne("attachment.selectFile", file_num);
		return attachmentVo;
	}

	@Override
	public int insertFile(SqlSession sqlSession, AttachmentVo attachmentVo) {
		int cnt = sqlSession.insert("attachment.insertFile", attachmentVo);
		return cnt;
	}

	@Override
	public int deleteFile(SqlSession sqlSession, String file_num) {
		int cnt = sqlSession.delete("attachment.deleteFile", file_num);
		return cnt;
	}

}
