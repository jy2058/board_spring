package kr.or.ddit.attachment.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.attachment.model.AttachmentVo;
import kr.or.ddit.attachment.service.AttachmentServiceImpl;
import kr.or.ddit.attachment.service.IAttachmentService;

@WebServlet("/fileDownload")
public class FileDownloadController extends HttpServlet {

	private IAttachmentService attachmentService;

    @Override
	public void init() throws ServletException {
    	attachmentService = new AttachmentServiceImpl();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String file_num = request.getParameter("file_num");
	    
	    AttachmentVo attachmentVo = attachmentService.selectFile(file_num);
	    String filename = attachmentVo.getFilename();
	    String realfilename = attachmentVo.getRealfilename();

	    File outputFile = new File(realfilename);
	    
	    // 파일을 읽어와 저장할 버퍼를 임시로 만들고 버퍼의 용량은 업로드할 수 있는 파일 크기로 지정한다.
	    byte[] temp = new byte[1024 * 1024 * 5];

	    FileInputStream fis = new FileInputStream(outputFile);
	    
	    // 유형 확인 : 읽어올 경로의 파일 유형 -> 페이지 생성할 때 타입을 설정해야 한다.
	    String mimeType = getServletContext().getMimeType(realfilename);

	    // 지정되지 않은 유형 예외처리
	    if (mimeType == null){
	    	mimeType = "application.octec-stream"; // 일련된 8bit 스트림 형식
	    }
	    
	    // 파일 다운로드 시작
	    response.setContentType(mimeType); // 유형 지정
	    
	    String encoding = new String(filename.getBytes("euc-kr"),"8859_1");
	  
	    String AA = "Content-Disposition";
	    String BB = "attachment;filename="+ encoding;
	    response.setHeader(AA,BB);
	     
	    // 브라우저에 쓰기
	    ServletOutputStream sos = response.getOutputStream();
	     
	    int numRead = 0;
	    while((numRead = fis.read(temp,0,temp.length)) != -1){
	    	// 브라우저에 출력
	    	sos.write(temp,0,numRead);
	    }
	    
	    // 자원 해제
	    sos.flush();
	    sos.close();
	    fis.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
