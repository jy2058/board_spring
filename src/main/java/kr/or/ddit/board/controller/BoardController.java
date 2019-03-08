package kr.or.ddit.board.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.board.model.BoardVo;
import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.post.model.PostVo;
import kr.or.ddit.post.service.IPostService;
import kr.or.ddit.post.service.PostServiceImpl;
import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.util.model.PageVo;

@WebServlet("/board")
public class BoardController extends HttpServlet {
	private IBoardService boardService;
	private IPostService postService;
	
	@Override
	public void init() throws ServletException {
		boardService = new BoardServiceImpl();
		postService = new PostServiceImpl();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		UserVo userVo = (UserVo) session.getAttribute("userVo");
		
		String board_num = request.getParameter("boardnum");
		
		BoardVo boardVo = boardService.selectBoard(board_num);
		List<BoardVo> boardList = boardService.getAllBoard();
		List<PostVo> postList = postService.getAllPost(board_num);
		
		request.setAttribute("boardVo", boardVo);
		request.setAttribute("boardList", boardList);
		request.setAttribute("postList", postList);
		
		if (userVo == null) {
			request.getRequestDispatcher("/login/login.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("/board/board.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
	}
}
