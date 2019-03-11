package kr.or.ddit.board.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.board.model.BoardVo;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.post.model.PostVo;
import kr.or.ddit.post.service.IPostService;
import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.util.model.PageVo;

@Controller
public class BoardController {
	
	@Resource(name = "boardService")
	private IBoardService boardService;
	
	@Resource(name = "postService")
	private IPostService postService;

	@RequestMapping(path="/board", method=RequestMethod.GET)
	public String board(HttpSession session, @RequestParam("boardnum")String board_num, Model model){
		UserVo userVo = (UserVo) session.getAttribute("userVo");
		
		BoardVo boardVo = boardService.selectBoard(board_num);
		//List<BoardVo> boardList = boardService.getAllBoard();
		List<PostVo> postList = postService.getAllPost(board_num);
		
		model.addAttribute("boardVo", boardVo);
		//model.addAttribute("boardList", boardList);
		model.addAttribute("postList", postList);
		
		if(userVo == null){
			return "login/login";
		}else{
			return "board/board";
		}
	}
	
	@RequestMapping(path="/boardManage", method=RequestMethod.GET)
	public String boardManage(HttpSession session, Model model){
		UserVo userVo = (UserVo) session.getAttribute("userVo");
		
		if(userVo == null){
			return "login/login";
		}else{
			return "board/boardManage";
		}
	}
	
	@RequestMapping(path="/boardManage", method=RequestMethod.POST)
	public String boardManagePost(HttpSession session, Model model, /*@RequestParam Map<String, String[]> map,*/ @RequestParam("info")String info, @RequestParam("board_name")String board_name, @RequestParam("sbUse")String use,
			@RequestParam("updBoardNum")String updBoard_num, @RequestParam("updBoardName")String updBoard_name, @RequestParam("updBoardUse")String updUse){
		
//		List<BoardVo> boardList = boardService.getAllBoard();
//		model.addAttribute("boardList", boardList);
		
		/*Iterator<String> itr = map.keySet().iterator();
		while(itr.hasNext()){
			String key = itr.next();
			String[] values = map.get(key);
			for(String value : values){
				System.out.println(key + " : " + value);
			}
		}*/
		
		BoardVo boardVo = null;
		System.out.println(info);
		
		// 게시판 생성
		if(info.equals("insert")){
			boardVo = new BoardVo(board_name, use);
			boardService.insertBoard(boardVo);
			
			return "redirect:/boardManage";
		}
		// 게시판 수정
		else{
			boardVo = new BoardVo(updBoard_num, updBoard_name, updUse);
			boardService.updateBoard(boardVo);
			
			return "redirect:/boardManage";
		}
	}
	
	@RequestMapping("/boardPagingList")
	public String boardPagingList(HttpSession session, Model model, PageVo pageVo, @RequestParam("boardnum")String board_num){
		UserVo userVo = (UserVo) session.getAttribute("userVo");
//		BoardVo boardVo = boardService.selectBoard(pageVo.getBoard_num());
		BoardVo boardVo = boardService.selectBoard(board_num);
		
		// 페이징 리스트 구현

		int page = pageVo.getPage();
		int pageSize = pageVo.getPageSize();
		
		int page2 = page == 0 ? 1 : page;
		int pageSize2 = pageSize == 0 ? 10 : pageSize;
		
		PageVo newPageVo = new PageVo(board_num, page2, pageSize2);
		
		// userService 객체를 이용 userPagingList 조회
		Map<String, Object> resultMap = postService.selectPostPagingList(newPageVo);
		List<PostVo> postList = (List<PostVo>) resultMap.get("postList");
		int postCnt = (Integer) resultMap.get("postCnt");
		
		model.addAttribute("boardVo", boardVo);
		model.addAttribute("postList", postList);
		model.addAttribute("postCnt", postCnt);
		model.addAttribute("pageSize", pageSize2);
		model.addAttribute("page", page2);
		
		// page랑 pageSize를 newPageVo로 넘기는 거 해보자
		
		if(userVo == null){
			return "login/login";
		}else{
			return "board/boardPagingList";
		}
	}
}
