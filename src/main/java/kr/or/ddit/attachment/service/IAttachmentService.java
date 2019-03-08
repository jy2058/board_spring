package kr.or.ddit.attachment.service;

import java.util.List;

import kr.or.ddit.attachment.model.AttachmentVo;

public interface IAttachmentService {
	List<AttachmentVo> getAllFile(String post_num);

	AttachmentVo selectFile(String file_num);
	
	int insertFile(AttachmentVo attachmentVo);
	
	int deleteFile(String file_num);
}
