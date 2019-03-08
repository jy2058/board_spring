package kr.or.ddit.main;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.board.model.BoardVo;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.user.model.UserVo;

//@Controller
public class MainController{
	
	@Resource(name="boardService")
	private IBoardService boardService;

	//@RequestMapping("/main")
	public String mainView(HttpSession session, Model model){
		UserVo userVo = (UserVo) session.getAttribute("userVo");
		List<BoardVo> boardList = boardService.getAllBoard();
		
		model.addAttribute("boardList", boardList);
		
		if(userVo == null){
			return "login/login";
		}else{
			return "module/main";
		}
	}
	
	@RequestMapping(path="/main", method=RequestMethod.POST)
		public String mainPost(){
			return "module/main";
		}
	}

/*	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}*/
