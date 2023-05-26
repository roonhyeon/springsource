package com.spring.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.multipart.MultipartFile;

import com.spring.domain.AttachFileDTO;

import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;

@Slf4j
@Controller
public class UploadAjaxController {

	// uploadAjax 컨트롤러 생성
	@GetMapping("/uploadAjax")
	public void uploadAjaxGet() {
		log.info("Ajax form request");

	}

	// 파일 1개 업로드
//	@PostMapping("/uploadAjax")
//	public ResponseEntity<String> uploadAjaxPost(MultipartFile uploadFile) { // js에서 append에 걸어준 변수명과 동일해야 한다.
//		log.info("upload request");
//		
//		log.info("file Name "+uploadFile.getOriginalFilename());
//		log.info("file Size "+uploadFile.getSize());
//		
//		String uploadPath="c:\\upload";
//		UUID uuid=UUID.randomUUID();
//		String fileName=uuid.toString()+"_"+uploadFile.getOriginalFilename();
//		
//		try {
//			uploadFile.transferTo(new File(uploadPath, fileName));
//		} catch (IllegalStateException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		
//		// 상태코드 + 메세지
//		return new ResponseEntity<>(fileName, HttpStatus.OK); // js의 첫번째 then()안에 넘겨준다.
//	}

	// 파일 여러 개 업로드
//	@PostMapping("/uploadAjax")
//	public ResponseEntity<List<String>> uploadAjaxPost(MultipartFile[] uploadFile) { // js에서 append에 걸어준 변수명과 동일해야 한다.
//		log.info("upload request ");
//		
//		List<String> fileList=new ArrayList<String>();
//		
//		for (MultipartFile multipartFile : uploadFile) {
//			log.info("file Name "+multipartFile.getOriginalFilename());
//			log.info("file Size "+multipartFile.getSize());
//			
//			String uploadPath="c:\\upload";
//			UUID uuid=UUID.randomUUID();
//			String fileName=uuid.toString()+"_"+multipartFile.getOriginalFilename();
//			
//			fileList.add(multipartFile.getOriginalFilename());
//			
//			try {
//				multipartFile.transferTo(new File(uploadPath, fileName));
//			} catch (IllegalStateException e) {
//				e.printStackTrace();
//			} catch (IOException e) { 
//				e.printStackTrace();
//			}
//		}
//		// 상태코드 + 메세지
//		return new ResponseEntity<>(fileList, HttpStatus.OK);
//	}

	@PostMapping("/uploadAjax")
	public ResponseEntity<List<AttachFileDTO>> uploadAjaxPost(MultipartFile[] uploadFile) { // js에서 append에 걸어준 변수명과
																							// 동일해야 한다.
		log.info("upload request ");

		List<AttachFileDTO> fileList = new ArrayList<AttachFileDTO>();

		String uploadPath = "c:\\upload";
		// upload 폴더 안에 폴더 생성 => c:\\upload\\2023\\05\\26
		String uploadFolderPath = getFolder();
		log.info("uploadFolderPath ", uploadFolderPath);

		File uploadFullPath = new File(uploadPath, uploadFolderPath);

		if (!uploadFullPath.exists()) { // 폴더가 존재하지 않는다면 만들어주세요.
			uploadFullPath.mkdirs();
		}

		for (MultipartFile multipartFile : uploadFile) {
			log.info("file Name " + multipartFile.getOriginalFilename());
			log.info("file Size " + multipartFile.getSize());

			UUID uuid = UUID.randomUUID();
			String fileName = uuid.toString() + "_" + multipartFile.getOriginalFilename();

			File saveFile = new File(uploadFullPath, fileName);

			// 파일 한 개당 AttachFileDTO 생성
			AttachFileDTO attach = new AttachFileDTO();
			attach.setFileName(multipartFile.getOriginalFilename());
			attach.setUploadPath(uploadFolderPath);
			attach.setUuid(uuid.toString());

			try {
				// 원본 파일 저장하는 코드
				multipartFile.transferTo(saveFile); // fullpath로 바꿔주면 업로드했을 때 그 날짜 폴더 안에 저장된다.

				// 업로드 된 파일 타입 체크
				if (checkImageType(saveFile)) {
					attach.setFileType(true);

					// 이미지 파일이라면 썸네일 이미지로 저장
					// 원본 이미지 읽어오기
					BufferedImage origin = ImageIO.read(saveFile);

					// 썸네일 파일명
					File thumnail = new File(uploadFullPath, "s_" + fileName);

					double ratio = 20; // 축소비율
					int width = (int) (origin.getWidth() / ratio);
					int height = (int) (origin.getHeight() / ratio);

					Thumbnails.of(origin).size(width, height).toFile(thumnail);
				}
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			fileList.add(attach);
		}
		// 상태코드 + 메세지
		return new ResponseEntity<>(fileList, HttpStatus.OK);
	}

	// 파일 요청이 올 때 파일 보내주기
	@GetMapping("/display")
	public ResponseEntity<byte[]> getFile(String fileName) {
		log.info("파일 요청 " + fileName);

		File file = new File("c:\\upload\\" + fileName);

		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<byte[]> result = null;
		try {
			// 서버가 보내는 파일에 대한 타입 지정
			headers.add("content-type", Files.probeContentType(file.toPath()));
			result = new ResponseEntity<byte[]>(FileCopyUtils.copyToByteArray(file), HttpStatus.OK);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	// 파일 다운로드
	@GetMapping(value="/download", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity<Resource> downloadFile(String fileName, @RequestHeader("User-Agent") String userAgent){
		log.info("파일 다운로드 요청 "+fileName);
		
		Resource resource=new FileSystemResource("c:\\upload\\"+fileName);
		
		if(!resource.exists()) {
			return new ResponseEntity<Resource>(HttpStatus.NOT_FOUND);
		}
		
		HttpHeaders headers=new HttpHeaders();
		
		String downloadName=null;
		try {
				if(userAgent.contains("Trident") || userAgent.contains("Edge")) {
					downloadName=URLEncoder.encode(resource.getFilename(), "utf-8").replaceAll("\\+", " ");
				}else {
					downloadName=new String(resource.getFilename().getBytes("utf-8"),"ISO-8859-1");
				}
				headers.add("content-Disposition", "attachment;filename="+downloadName);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Resource>(resource, headers, HttpStatus.OK);		
	}

	// 일반 메소드(파일 타입 확인)
	private boolean checkImageType(File file) {
		String contentType;

		try {
			contentType = Files.probeContentType(file.toPath()); // image/gif, image/jpg
			return contentType.startsWith("image");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	// 일반 메소드(폴더 생성)
	private String getFolder() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		Date date = new Date(); // 날짜 정보가 길게
		String str = sdf.format(date); // 2023-05-26
		return str.replace("-", File.separator); // -로 되어있는 부분을 separator로 바꿔주세요.
	}
}
