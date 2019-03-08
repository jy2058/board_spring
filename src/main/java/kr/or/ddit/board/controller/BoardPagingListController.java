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

@WebServlet("/boardPagingList")
public class BoardPagingListController extends HttpServlet {
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
		
		// 페이징 리스트 구현
		String pageStr = request.getParameter("page");
		String pageSizeStr = request.getParameter("pageSize");
		
		int page = pageStr == null ? 1 : Integer.parseInt(pageStr);
		int pageSize = pageSizeStr == null ? 10 : Integer.parseInt(pageSizeStr);
		
		PageVo pageVo = new PageVo(board_num, page, pageSize);
		
		// userService 객체를 이용 userPagingList 조회
		Map<String, Object> resultMap = postService.selectPostPagingList(pageVo);
		List<PostVo> postList = (List<PostVo>) resultMap.get("postList");
		int postCnt = (Integer) resultMap.get("postCnt");
		
		request.setAttribute("boardVo", boardVo);
		request.setAttribute("boardList", boardList);
		
		request.setAttribute("postList", postList);
		request.setAttribute("postCnt", postCnt);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("page", page);
		
		if (userVo == null) {
			request.getRequestDispatcher("/login/login.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("/board/boardPagingList.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
	}
}
