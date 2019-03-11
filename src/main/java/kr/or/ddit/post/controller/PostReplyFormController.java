package kr.or.ddit.post.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import kr.or.ddit.attachment.model.AttachmentVo;
import kr.or.ddit.attachment.service.AttachmentServiceImpl;
import kr.or.ddit.attachment.service.IAttachmentService;
import kr.or.ddit.board.model.BoardVo;
import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.post.model.PostVo;
import kr.or.ddit.post.service.IPostService;
import kr.or.ddit.post.service.PostServiceImpl;
import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.util.PartUtil;

//@WebServlet("/postReplyForm")
@MultipartConfig(maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class PostReplyFormController extends HttpServlet {
	private IPostService postService;
	private IBoardService boardService;
	private IAttachmentService attachmentService;
	
	@Override
	public void init() throws ServletException {
		postService = new PostServiceImpl();
		boardService = new BoardServiceImpl();
		attachmentService = new AttachmentServiceImpl();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		UserVo userVo = (UserVo) session.getAttribute("userVo");
		
		List<BoardVo> boardList = boardService.getAllBoard();
		String board_num = request.getParameter("board_num");
		
		String post_num = request.getParameter("post_num");
		PostVo postVo = postService.selectPost(post_num);
		
		request.setAttribute("boardList", boardList);
		request.setAttribute("board_num", board_num);
		request.setAttribute("postVo", postVo);
		
		if (userVo == null) {
			request.getRequestDispatcher("/login/login.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("/post/postReplyForm.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		UserVo userVo = (UserVo) request.getSession().getAttribute("userVo");
		String post_title = request.getParameter("post_title");
		String post_con = request.getParameter("post_con");
		String parent_num = request.getParameter("post_num");
		String board_num = request.getParameter("board_num");
		
		PostVo parentVo = postService.selectPost(parent_num);
		String gn = parentVo.getGn();
		
		// 첨부파일 정보 저장
		List<String> filenames = new ArrayList<>();
		List<String> realFilenames = new ArrayList<>();

		for (int i = 1; i <= 5; i++) {
			Part profilePart = request.getPart("file" + i);
			if (profilePart != null && profilePart.getSize() > 0) {
				System.out.println("file" + i);
				
				// 사용자 테이블에 파일명 저장
				// (실제 업로드한 파일명-filename, 파일 충동을 방지하기 위해 사용한 uuid-realFilename)
				String contentDisposition = profilePart.getHeader("Content-Disposition");
				String filename = PartUtil.getFileNameFromPart(contentDisposition);
				String realFilename = "d:\\attachment\\" + UUID.randomUUID().toString();

				filenames.add(filename);
				realFilenames.add(realFilename);
				
				// 디스크에 기록 (d:\picture\realFilename)
				profilePart.write(realFilename);
				profilePart.delete();
				
			}
		}
		
		if (userVo != null) {
			PostVo postVo = new PostVo();
			postVo.setPost_title(post_title);
			postVo.setPost_con(post_con);
			postVo.setDel("0");
			postVo.setGn(gn);
			postVo.setUserid(userVo.getUserId());
			postVo.setParent_num(parent_num);
			postVo.setBoard_num(board_num);
			
			int cnt = postService.insertReply(postVo);
			
			// 게시글 등록 성공
			if(cnt > 0) {
				// post : insert 실행 후 적용된 post_num
				String post_num = postVo.getPost_num();
				// attachment : 정상 실행된 행 개수
				int fileCnt = 0;
				
				for (int i = 0; i < filenames.size(); i++) {
					AttachmentVo attachmentVo = new AttachmentVo();
					attachmentVo.setFilename(filenames.get(i));
					attachmentVo.setRealfilename(realFilenames.get(i));
					attachmentVo.setPost_num(post_num);
					
					fileCnt += attachmentService.insertFile(attachmentVo);
				}
				
				if (fileCnt == filenames.size()) { 
					response.sendRedirect(request.getContextPath() + "/boardPagingList?boardnum=" + board_num);
				} else {
					System.out.println("첨부파일 등록 오류");
				}
			}
			// 게시글 등록 실패
			else {
				response.sendRedirect(request.getContextPath() + "/boardPagingList?boardnum=" + board_num);
			}
		} else {
			doGet(request, response);
		}
	}

}
