package kr.or.ddit.attachment.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.attachment.dao.AttachmentDaoImpl;
import kr.or.ddit.attachment.dao.IAttachmentDao;
import kr.or.ddit.attachment.model.AttachmentVo;
import kr.or.ddit.db.mybatis.MybatisSqlSessionFactory;

public class AttachmentServiceImpl implements IAttachmentService {

	private IAttachmentDao attachmentDao;
	
	public AttachmentServiceImpl() {
		attachmentDao = new AttachmentDaoImpl();
	}

	@Override
	public List<AttachmentVo> getAllFile(String post_num) {
		SqlSessionFactory sqlSessionFactory = MybatisSqlSessionFactory.getSqlSessionFactory();
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		List<AttachmentVo> fileList = attachmentDao.getAllFile(sqlSession, post_num);
		
		sqlSession.close();
		return fileList;
	}
	
	@Override
	public AttachmentVo selectFile(String file_num) {
		SqlSessionFactory sqlSessionFactory = MybatisSqlSessionFactory.getSqlSessionFactory();
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		AttachmentVo attachmentVo = attachmentDao.selectFile(sqlSession, file_num);
		
		sqlSession.close();
		return attachmentVo;
	}

	@Override
	public int insertFile(AttachmentVo attachmentVo) {
		SqlSessionFactory sqlSessionFactory = MybatisSqlSessionFactory.getSqlSessionFactory();
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		int cnt = attachmentDao.insertFile(sqlSession, attachmentVo);
		
		sqlSession.commit();
		sqlSession.close();
		return cnt;
	}

	@Override
	public int deleteFile(String file_num) {
		SqlSessionFactory sqlSessionFactory = MybatisSqlSessionFactory.getSqlSessionFactory();
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		int cnt = attachmentDao.deleteFile(sqlSession, file_num);
		
		sqlSession.commit();
		sqlSession.close();
		return cnt;
	}

}
