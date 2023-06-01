package com.spring.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.domain.AttachFileDTO;
import com.spring.domain.BoardDTO;
import com.spring.domain.Criteria;
import com.spring.domain.PageDTO;
import com.spring.service.BoardService;

import lombok.extern.slf4j.Slf4j;

@RequestMapping("/board")
@Slf4j
@Controller
public class BoardController {
	
	@Autowired
	private BoardService service;
	
	// 전체 리스트 보여주기 컨트롤러 작성: list.jsp 보여주기
	@GetMapping("/list")
	public void getList(Model model, @ModelAttribute("cri") Criteria cri) {
		log.info("list request");
		log.info("type "+Arrays.toString(cri.getTypeArr()));
		
		List<BoardDTO> list=service.getList(cri); // 사용자가 요청한 번호에 맞는 게시물 목록 요청
		int total=service.getTotalCnt(cri); // 전체 게시물 개수
		
		model.addAttribute("list", list); // 목록 정보 넘기기
		model.addAttribute("pageDTO", new PageDTO(cri, total)); // 페이지 관련 정보 넘기기
	}
	
	// register.jsp 보여주기
	@GetMapping("/register")
	public void registerForm() {
		log.info("register form request");
	}
	
	@PostMapping("/register")
	public String register(BoardDTO boardDTO, RedirectAttributes rttr, Criteria cri) {
		log.info("register request "+boardDTO);
		
		// 첨부파일 확인
//		if(boardDTO.getAttachList() != null) {
//			boardDTO.getAttachList().forEach(attach -> log.info(attach.toString()));
//		}
		
		boolean insertFlag=service.register(boardDTO);
		if(insertFlag) {
			log.info("글 번호: "+boardDTO.getBno()); // 게시글을 작성할 때마다 로그에 글 번호가 출력된다.
			rttr.addFlashAttribute("result", boardDTO.getBno()); // addFlashAttribute: 주소줄에 넘기지 않고 session에 잠시 담아두는 방식
			
			// 페이지 나누기 정보 주소줄에 같이 보내기
			rttr.addAttribute("page", cri.getPage());
			rttr.addAttribute("amount", cri.getAmount());
			
			return "redirect:/board/list";
		}
		return "/board/register";
	}
	
	// http://localhost:8080/board/read??page=1&amount=10&bno=917
	// http://localhost:8080/board/modify??page=1&amount=10&bno=917
	@GetMapping({"/read", "/modify"})
	public void readGet(int bno, Model model, @ModelAttribute("cri") Criteria cri) {
		log.info("read request "+bno);
		
		// bno에 해당하는 내용 가져오기
		BoardDTO dto=service.read(bno);
		model.addAttribute("dto", dto);
	}
	
	@PostMapping("/modify")
	public String modifyPost(BoardDTO boardDTO, RedirectAttributes rttr, Criteria cri) {
		log.info("modify request"+cri); 
		// 수정 성공 시 리스트로 가기
		service.modify(boardDTO);
		rttr.addFlashAttribute("result", "success");
		
		// 페이지 나누기 정보 주소줄에 같이 보내기
		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("amount", cri.getAmount());
		
		// 수정 버튼 눌렀을 때 검색 정보 주소줄에 보내기
		rttr.addAttribute("type", cri.getType());
		rttr.addAttribute("keyword", cri.getKeyword());
		
		return "redirect:/board/list";
	}
	
	@GetMapping("/remove")
	public String removeGet(int bno, RedirectAttributes rttr, Criteria cri) {

		// 폴더에서 첨부파일 제거
		List<AttachFileDTO> attachList = service.getAttachList(bno);
		deleteAttachFiles(attachList);
		
		// 삭제 성공 시 리스트로 가기
		service.remove(bno);
		
		rttr.addFlashAttribute("result", "success");
		
		// 페이지 나누기 정보 주소줄에 같이 보내기
		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("amount", cri.getAmount());
		
		// 삭제 버튼 눌렀을 때 검색 정보 주소줄에 보내기
		rttr.addAttribute("type", cri.getType());
		rttr.addAttribute("keyword", cri.getKeyword());
		
		return "redirect:/board/list";
	}
	
	// 첨부 파일 가져오기(/getAttachList) + GET + bno
	@GetMapping("/getAttachList")
	public ResponseEntity<List<AttachFileDTO>> attachList(int bno){
		List<AttachFileDTO> attachList=service.getAttachList(bno);
		
		return attachList != null ? new ResponseEntity<List<AttachFileDTO>>(attachList, HttpStatus.OK):
			new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	private void deleteAttachFiles(List<AttachFileDTO> attachList) {
		log.info("첨부 파일 폴더에서 제거");
		
		if(attachList == null || attachList.size() <= 0) return;
		
		for(AttachFileDTO dto : attachList) {
			// 파일 경로
			Path path=Paths.get("c:\\upload\\"+dto.getUploadPath()+"\\"+dto.getUuid()+"_"+dto.getFileName());
			
			try {
				Files.deleteIfExists(path);
				
				// 이미지 파일인 경우 썸네일 제거
				if(Files.probeContentType(path).startsWith("image")) {
					Path thumb=Paths.get("c:\\upload\\"+dto.getUploadPath()+"\\s_"+dto.getUuid()+"_"+dto.getFileName());
					Files.deleteIfExists(thumb);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
}
