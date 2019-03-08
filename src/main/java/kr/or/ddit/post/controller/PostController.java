package kr.or.ddit.post.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.attachment.model.AttachmentVo;
import kr.or.ddit.attachment.service.AttachmentServiceImpl;
import kr.or.ddit.attachment.service.IAttachmentService;
import kr.or.ddit.board.model.BoardVo;
import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.comments.model.CommentsVo;
import kr.or.ddit.comments.service.CommentsServiceImpl;
import kr.or.ddit.comments.service.ICommentsService;
import kr.or.ddit.post.model.PostVo;
import kr.or.ddit.post.service.IPostService;
import kr.or.ddit.post.service.PostServiceImpl;
import kr.or.ddit.user.model.UserVo;

@WebServlet("/post")
public class PostController extends HttpServlet {
       
	private IPostService postService;
	private IBoardService boardService;
	private IAttachmentService attachmentService;
	private ICommentsService commentsService;
	
    @Override
	public void init() throws ServletException {
    	postService = new PostServiceImpl();
    	boardService = new BoardServiceImpl();
    	attachmentService = new AttachmentServiceImpl();
    	commentsService = new CommentsServiceImpl();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String post_num = request.getParameter("post_num");

		HttpSession session = request.getSession();
		UserVo userVo = (UserVo) session.getAttribute("userVo");
		
    	PostVo postVo = postService.selectPost(post_num);
		List<BoardVo> boardList = boardService.getAllBoard();
		List<AttachmentVo> fileList = attachmentService.getAllFile(post_num);
		List<CommentsVo> commentList = commentsService.getAllComment(post_num);
    	
    	request.setAttribute("postVo", postVo);
    	request.setAttribute("boardList", boardList);
    	request.setAttribute("fileList", fileList);
    	request.setAttribute("commentList", commentList);
    	
    	if (userVo == null) {
			request.getRequestDispatcher("/login/login.jsp").forward(request, response);
		} else {
	    	request.getRequestDispatcher("/post/post.jsp").forward(request, response);
		}
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		UserVo userVo = (UserVo) request.getSession().getAttribute("userVo");
		String post_num = request.getParameter("post_num");
		String info = request.getParameter("info");
		int cnt = 0;
		
		if (userVo != null) {
			// 게시글 삭제
			if (info.equals("delete")) {
				cnt = postService.deletePost(post_num);
				
				if (cnt > 0) {
					PostVo postVo = postService.selectPost(post_num);
					response.sendRedirect(request.getContextPath() + "/boardPagingList?boardnum=" + postVo.getBoard_num());
				}
			} else if(info.equals("comment")) {
				String comment = request.getParameter("comment");
				System.out.println("댓글 내용 : " + comment);
				
				CommentsVo commentsVo = new CommentsVo();
				commentsVo.setComment_con(comment);
				commentsVo.setUserid(userVo.getUserId());
				commentsVo.setPost_num(post_num);
				
				cnt = commentsService.insertComment(commentsVo);
				
				if (cnt > 0) {
					response.sendRedirect(request.getContextPath() + "/post?post_num=" + post_num);
				}
			} else if(info.equals("deleteComment")) {
				String comment_num = request.getParameter("comment_num");
				
				cnt = commentsService.deleteComment(comment_num);
				
				if (cnt > 0) {
					response.sendRedirect(request.getContextPath() + "/post?post_num=" + post_num);
				}
			}
		}
	}

}
