package com.spring.controller;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.spring.domain.FileDTO;

@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	@GetMapping("/uploadForm")
	public void uploadForm() {
		logger.info("uploadForm 요청");
	}
	
	// 첨부파일이 1개인 경우
//	@PostMapping("/uploadForm")
//	public void uploadPost(FileDTO dto) {
//		logger.info("upload 요청 "+dto);
//	
//		logger.info("file Name "+dto.getFile().getOriginalFilename());
//		logger.info("content type "+dto.getFile().getContentType());
//		logger.info("file Size "+dto.getFile().getSize());
//		
//		String uploadPath="C:\\upload";
//		File saveFile=new File(uploadPath, dto.getFile().getOriginalFilename());
//		
//		try {
//			// 폴더에 실제로 저장하는 코드
//			dto.getFile().transferTo(saveFile);
//		} catch (IllegalStateException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
	
	// 첨부파일이 여러 개인 경우
	@PostMapping("/uploadForm")
	public void uploadPost(FileDTO dto) {
		logger.info("upload 요청 "+dto);
		
		String uploadPath="C:\\upload";
		for (MultipartFile multipartFile : dto.getFile()) {
			logger.info("file Name "+multipartFile.getOriginalFilename());
			logger.info("content type "+multipartFile.getContentType());
			logger.info("file Size "+multipartFile.getSize());
			
			UUID uuid=UUID.randomUUID();
			String fileName=uuid.toString()+"_"+multipartFile.getOriginalFilename();
			
			File saveFile=new File(uploadPath, fileName);
			
			try {
				// 폴더에 실제로 저장하는 코드
				multipartFile.transferTo(saveFile);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	
	}
	
}
