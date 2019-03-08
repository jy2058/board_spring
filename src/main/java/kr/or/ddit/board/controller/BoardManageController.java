package kr.or.ddit.board.controller;

import java.io.IOException;
import java.util.Iterator;
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
import kr.or.ddit.user.model.UserVo;

@WebServlet("/boardManage")
public class BoardManageController extends HttpServlet {

	private IBoardService boardService;
	
	@Override
	public void init() throws ServletException {
		boardService = new BoardServiceImpl();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HttpSession session = req.getSession();
		UserVo userVo = (UserVo) session.getAttribute("userVo");
		
		List<BoardVo> boardList = boardService.getAllBoard();
		
		req.setAttribute("boardList", boardList);
		
		if (userVo == null) {
			req.getRequestDispatcher("/login/login.jsp").forward(req, resp);
		} else {
			req.getRequestDispatcher("/board/boardManage.jsp").forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		
		Map<String, String[]> map = req.getParameterMap();
		Iterator<String> itr = map.keySet().iterator();
		while(itr.hasNext()){
			String key = itr.next();
			String[] values = map.get(key);
			for(String value : values){
				System.out.println(key + " : " + value);
			}
		}
		String info = req.getParameter("info");
		BoardVo boardVo = null;
		
		System.out.println(info);
		
		// 게시판 생성
		if (info.equals("insert")) {
			String board_name = req.getParameter("board_name");
			String use = req.getParameter("sbUse");
			
			boardVo = new BoardVo(board_name, use);
			
			int cnt = boardService.insertBoard(boardVo);
			
			// 게시판 생성 성공
			if (cnt > 0) {
				resp.sendRedirect(req.getContextPath() + "/boardManage");
			}
			// 게시판 생성 실패
			else {
				doGet(req, resp);
			}
		} 
		// 게시판 수정
		else {
			String board_num  = req.getParameter("updBoardNum");
			String board_name = req.getParameter("updBoardName");
			String use 		  = req.getParameter("updBoardUse");
			
			boardVo = new BoardVo(board_num, board_name, use);
			
			int cnt = boardService.updateBoard(boardVo);
			
			// 게시판 수정 성공
			if (cnt > 0) {
				resp.sendRedirect(req.getContextPath() + "/boardManage");
			}
			// 게시판 수정 실패
			else {
				doGet(req, resp);
			}
		}
	}
	
}
