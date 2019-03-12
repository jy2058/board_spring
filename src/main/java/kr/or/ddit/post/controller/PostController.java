package kr.or.ddit.post.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import kr.or.ddit.attachment.model.AttachmentVo;
import kr.or.ddit.attachment.service.IAttachmentService;
import kr.or.ddit.comments.model.CommentsVo;
import kr.or.ddit.comments.service.ICommentsService;
import kr.or.ddit.post.model.PostVo;
import kr.or.ddit.post.service.IPostService;
import kr.or.ddit.user.model.UserVo;

@Controller
public class PostController {
	
	@Resource(name="postService")
	private IPostService postService;
	@Resource(name="attachmentService")
	private IAttachmentService attachmentService;
	@Resource(name="commentsService")
	private ICommentsService commentsService;
	
	
	@RequestMapping(path="/post", method=RequestMethod.GET)
	public String board(@RequestParam("post_num")String post_num, HttpSession session, Model model ){
		UserVo userVo = (UserVo) session.getAttribute("userVo");
		PostVo postVo = postService.selectPost(post_num);
		
		System.out.println(postVo.getUserid());
		
		List<AttachmentVo> fileList = attachmentService.getAllFile(post_num);
		List<CommentsVo> commentList = commentsService.getAllComment(post_num);
		
		model.addAttribute("postVo", postVo);
		model.addAttribute("fileList", fileList);
		model.addAttribute("commentList", commentList);
		
		if(userVo == null){
			return "login/login";
		}else{
			return "post/post";
		}
		
	}
	
	@RequestMapping(path="/post", method=RequestMethod.POST)
	public String boardPost(HttpSession session, @RequestParam("post_num")String post_num, @RequestParam("info")String info, Model model, @RequestParam("comment")String comment, @RequestParam("comment_num")String comment_num){
		UserVo userVo = (UserVo) session.getAttribute("userVo");
		
		model.addAttribute("post_num", post_num);
		
		int cnt = 0;
		
		if(userVo != null){
			// 게시글 삭제
			if(info.equals("delete")){
				cnt = postService.deletePost(post_num);
				
				if(cnt > 0){
					PostVo postVo = postService.selectPost(post_num);
					model.addAttribute("boardnum", postVo.getBoard_num());
					return "redirect:/boardPagingList";
				}
			}else if(info.equals("comment")){
				CommentsVo commentsVo = new CommentsVo();
				commentsVo.setComment_con(comment);
				commentsVo.setUserid(userVo.getUserId());
				commentsVo.setPost_num(post_num);
				
				cnt = commentsService.insertComment(commentsVo);
				
				if(cnt > 0){
					return "redirect:/post";
				}
			}else if(info.equals("deleteComment")){
				cnt = commentsService.deleteComment(comment_num);
				
				if(cnt > 0){
					return "redirect:/post";
				}
			}
		}
		return "redirect:/post";
	}
	
	@RequestMapping(path="/postForm", method=RequestMethod.GET)
	public String postForm(HttpSession session, @RequestParam("board_num")String board_num, Model model){
		UserVo userVo = (UserVo) session.getAttribute("userVo");
		model.addAttribute("board_num", board_num);
		
		if(userVo == null){
			return "login/login";
		}else{
			return "post/postForm";
		}
	}
	
	@RequestMapping(path="/postForm", method=RequestMethod.POST)
	public String postFormPost(MultipartRequest Part, HttpSession session, PostVo postVo, Model model) throws IllegalStateException, IOException{
		UserVo userVo = (UserVo) session.getAttribute("userVo");
		List<MultipartFile> parts = Part.getFiles("uploadFiles");
		model.addAttribute("boardnum", postVo.getBoard_num());
		
		
/*		if (parts != null && parts.size() > 0) {
			for(MultipartFile file : parts){
				
				filename = file.getOriginalFilename();
				realFilename = "d:\\picture\\" + UUID.randomUUID().toString();

				System.out.println("filenameaaaaa:" + filename);
				
				file.transferTo(new File(realFilename));
			}
		}*/
		
		if (userVo != null) {
			PostVo tempPostVo = new PostVo();
			tempPostVo.setPost_title(postVo.getPost_title());
			tempPostVo.setPost_con(postVo.getPost_con());
			tempPostVo.setDel("0");
			tempPostVo.setUserid(userVo.getUserId());
			tempPostVo.setParent_num("");
			tempPostVo.setBoard_num(postVo.getBoard_num());
			
			int cnt = postService.insertPost(tempPostVo);
			
			// 게시글 등록 성공
			if(cnt > 0) {
				// post : insert 실행 후 적용된 post_num
				String post_num = tempPostVo.getPost_num();
				// attachment : 정상 실행된 행 개수
				int fileCnt = 0;
				
				String filename="";
				String realFilename="";
				
				for (MultipartFile file : parts) {
					filename = file.getOriginalFilename();
					realFilename = "d:\\picture\\" + UUID.randomUUID().toString();
					
					AttachmentVo attachmentVo = new AttachmentVo();
					
					attachmentVo.setFilename(filename);
					attachmentVo.setRealfilename(realFilename);
					attachmentVo.setPost_num(post_num);
					
					fileCnt += attachmentService.insertFile(attachmentVo);
				}
				
				if (fileCnt == parts.size()) {
					return "redirect:/post?post_num=" + post_num;
				} else {
					System.out.println("첨부파일 등록 오류");
				}
			}
			// 게시글 등록 실패
			else{
				return "redirect:/boardPagingList";
			}
		}else{
			return "login/login";
		}
		return "redirect:/boardPagingList";
	}
	
	@RequestMapping(path="/postModifyForm", method=RequestMethod.GET)
	public String postModifyForm(HttpSession session, Model model, @RequestParam("post_num")String post_num){
		
		UserVo userVo = (UserVo) session.getAttribute("userVo");
		
		PostVo postVo = postService.selectPost(post_num);
		List<AttachmentVo> fileList = attachmentService.getAllFile(post_num);
		
		model.addAttribute("postVo", postVo);
		model.addAttribute("fileList", fileList);
		
		if(userVo == null){
			return "login/login";
		}else{
			return "post/postModifyForm";
		}
	}
	
	@RequestMapping(path="/postModifyForm", method=RequestMethod.POST)
	public String postModifyFormPost(PostVo postVo, String[] delFile, MultipartRequest Part, Model model){
		
		model.addAttribute("post_num", postVo.getPost_num());
		
		PostVo tempPostVo = new PostVo();
		tempPostVo.setPost_num(postVo.getPost_num());
		tempPostVo.setPost_title(postVo.getPost_title());
		tempPostVo.setPost_con(postVo.getPost_con());
		
		int cnt = postService.updatePost(tempPostVo);
		
		if(cnt > 0){
			int insFileCnt = 0;
			int delFileCnt = 0;
			
			if(delFile != null){
				for(String dFile : delFile){
					delFileCnt += attachmentService.deleteFile(dFile);
				}
			}
			
			String filename="";
			String realFilename="";
			
			List<MultipartFile> parts = Part.getFiles("uploadFiles");
			
			for (MultipartFile file : parts) {
				filename = file.getOriginalFilename();
				realFilename = "d:\\picture\\" + UUID.randomUUID().toString();
				
				AttachmentVo attachmentVo = new AttachmentVo();
				
				attachmentVo.setFilename(filename);
				attachmentVo.setRealfilename(realFilename);
				attachmentVo.setPost_num(postVo.getPost_num());
				
				insFileCnt = attachmentService.insertFile(attachmentVo);
			}
			if(insFileCnt == parts.size() && delFileCnt == (delFile == null ? 0 : delFile.length)){
				return "redirect:/post";
			}else{
				System.out.println("첨부파일 등록 오류");
			}
		}else{
			return "redirect:/postModifyForm";
		}
		return "redirect:/postModifyForm";
	}
	
	@RequestMapping(path="/postReplyForm", method=RequestMethod.GET)
	public String postReplyForm(HttpSession session, @RequestParam("board_num")String board_num, @RequestParam("post_num")String post_num, Model model){
		UserVo userVo = (UserVo) session.getAttribute("userVo");
		PostVo postVo = postService.selectPost(post_num);
		
		model.addAttribute("board_num", board_num);
		model.addAttribute("postVo", postVo);
		
		if(userVo == null){
			return "login/login";
		}else{
			return "post/postReplyForm";
		}
	}
	
	@RequestMapping(path="/postReplyForm", method=RequestMethod.POST)
	public String postReplyFormPost(PostVo postVo, HttpSession session, MultipartRequest Part, Model model){
		UserVo userVo = (UserVo) session.getAttribute("userVo");
		model.addAttribute("boardnum", postVo.getBoard_num());
		
		String parent_num = postVo.getPost_num();
		
		PostVo parentVo = postService.selectPost(parent_num);
		String gn = parentVo.getGn();
		
		if (userVo != null) {
			PostVo tempPostVo = new PostVo();
			tempPostVo.setPost_title(postVo.getPost_title());
			tempPostVo.setPost_con(postVo.getPost_con());
			tempPostVo.setDel("0");
			tempPostVo.setGn(gn);
			tempPostVo.setUserid(userVo.getUserId());
			tempPostVo.setParent_num(parent_num);
			tempPostVo.setBoard_num(postVo.getBoard_num());
			
			int cnt = postService.insertPost(tempPostVo);
			
			// 게시글 등록 성공
			if(cnt > 0) {
				// post : insert 실행 후 적용된 post_num
				String post_num = tempPostVo.getPost_num();
				// attachment : 정상 실행된 행 개수
				int fileCnt = 0;
				
				String filename="";
				String realFilename="";
				
				List<MultipartFile> parts = Part.getFiles("uploadFiles");
				
				for (MultipartFile file : parts) {
					filename = file.getOriginalFilename();
					realFilename = "d:\\picture\\" + UUID.randomUUID().toString();
				
					AttachmentVo attachmentVo = new AttachmentVo();
					
					attachmentVo.setFilename(filename);
					attachmentVo.setRealfilename(realFilename);
					attachmentVo.setPost_num(post_num);
					
					fileCnt += attachmentService.insertFile(attachmentVo);
				}
				
				if (fileCnt == parts.size()) {
					return "redirect:/boardPagingList";
				} else {
					System.out.println("첨부파일 등록 오류");
				}
			}
			// 게시글 등록 실패
			else{
				return "redirect:/boardPagingList";
			}
		}else{
			return "redirect:/postReplyForm";
		}
		return "redirect:/boardPagingList";
	}
	
	@RequestMapping("/fileDownload")
	public String fileDownload(@RequestParam("file_num")String file_num, HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		AttachmentVo attachmentVo = attachmentService.selectFile(file_num);
	    String filename = attachmentVo.getFilename();
	    String realfilename = attachmentVo.getRealfilename();
	    
	    File outputFile = new File(realfilename);
	    
	    // 파일을 읽어와 저장할 버퍼를 임시로 만들고 버퍼의 용량은 업로드할 수 있는 파일 크기로 지정한다.
	    byte[] temp = new byte[1024 * 1024 * 5];

	    FileInputStream fis = new FileInputStream(outputFile);
	    
	    // 유형 확인 : 읽어올 경로의 파일 유형 -> 페이지 생성할 때 타입을 설정해야 한다.
	    String mimeType = request.getServletContext().getMimeType(realfilename);

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
		
		return "redirect:/post";
		
	}
	
	
}