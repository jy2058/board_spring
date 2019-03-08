package kr.or.ddit.login;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.board.model.BoardVo;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.encrypt.kisa.sha256.KISA_SHA256;
import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.service.IUserService;


@Controller
public class LoginController{
	
	@Resource(name="userService")
	private IUserService userService;
	
	@Resource(name="boardService")
	private IBoardService boardService;
	
	@RequestMapping(path="/login", method={RequestMethod.GET})
	public String loginView(){
		return "login/login";
	}
	
	@RequestMapping(path="/login", method={RequestMethod.POST})
	public String loginProcess(UserVo userVo, HttpSession session ){
		UserVo userVo_temp = userService.selectUser(userVo.getUserId());
		
		if(userVo_temp.getUserId().equals(userVo.getUserId()) && userVo_temp.getPass().equals(KISA_SHA256.encrypt(userVo.getPass()))){
			session.setAttribute("userVo", userVo_temp);
			
			return "module/main";
		}
		else{
			return "login/login";
		}
	}
	
	@RequestMapping("/main")
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
	public String mainForward(){
		return "module/main";
	}
}
	

