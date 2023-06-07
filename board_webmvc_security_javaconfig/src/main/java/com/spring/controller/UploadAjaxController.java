package com.spring.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
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
import org.springframework.security.access.prepost.PreAuthorize;
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
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<List<AttachFileDTO>> uploadAjaxPost(MultipartFile[] uploadFile) { // js에서 append에 걸어준 변수명과 동일해야 한다.
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

	// HttpServletRequest 객체: 클라이언트 정보를 가져올 수 있다.
	// 파일 다운로드
	@GetMapping(value="/download", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	// f12 눌러 네트워크 부분에서 user-agent의 value값을 가져오기 위한 코드
	public ResponseEntity<Resource> downloadFile(String fileName, @RequestHeader("User-Agent") String userAgent){ 
		log.info("파일 다운로드 요청 "+fileName);
		
		// C:\\upload\2023\05\30\6acca6f4-1838-46f2-bb93-d5d8a8c99f12_h.jpg 형태로 파일이 저장된다.
		// 그렇다면 jpg 파일명 앞의 uuid을 생략하고 싶으면 어떻게 하면 될까??
		Resource resource=new FileSystemResource("c:\\upload\\"+fileName);
		
		String oriFileName=resource.getFilename(); // 경로를 포함한 파일명
		String splitUuid=oriFileName.substring(oriFileName.indexOf("_")+1); // indexof를 통해 파일명에서 uuid를 제거해주기
		
		if(!resource.exists()) {
			return new ResponseEntity<Resource>(HttpStatus.NOT_FOUND);
		}
		 
		HttpHeaders headers=new HttpHeaders();
		
		String downloadName=null;
		try {
			    // ms 계열: Trident(IE 11)
				if(userAgent.contains("Trident") || userAgent.contains("Edge")) {
					// uuid를 포함한 파일명
//					downloadName=URLEncoder.encode(resource.getFilename(), "utf-8").replaceAll("\\+", " ");
					// uuid를 제거한 파일명
					downloadName=URLEncoder.encode(splitUuid, "utf-8").replaceAll("\\+", " ");
				}else { // ms 계열 이외에는 ISO-8859-1
					// uuid를 포함한 파일명
//					downloadName=new String(resource.getFilename().getBytes("utf-8"),"ISO-8859-1");
					// uuid를 제거한 파일명
					downloadName=new String(splitUuid.getBytes("utf-8"),"ISO-8859-1");
				}
				// 파일을 헤더에 붙이기 위한 코드
				headers.add("content-Disposition", "attachment;filename="+downloadName);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Resource>(resource, headers, HttpStatus.OK);		
	}
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/deleteFile")
	public ResponseEntity<String> deleteFile(String fileName, String type){
		log.info("파일 제거 요청 "+fileName+", type "+type);
		
		try {
			// x 눌렀을 때 콘솔에서의 이미지 파일명: 2023%5C05%5C30%5Cs_2f8d8be9-8e42-4325-b68b-1360dc6f2741_h.PNG image
			// 경로에 있는 \가 %5C로 인코딩된다. 그래서 디코딩 작업 필요
			File file=new File("c:\\upload\\", URLDecoder.decode(fileName, "utf-8"));
			
			// upload 폴더 안의 파일 삭제(이미지: 원본, 썸네일 이미지 모두 제거 / txt: 파일 제거)
			file.delete(); // txt 파일, 썸네일 삭제
			// 원본 이미지 삭제
			if(type.equals("image")) {
				// 위에서 작성한 file 객체에서 fileName 추출 후, s_를 제거한 상태의 이름을 파일 객체로 생성
				String largeName=file.getAbsolutePath().replace("s_", "");
				file=new File(largeName);
				// 삭제 작업
				file.delete();
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 예외 발생 시
		}
		return new ResponseEntity<String>("success", HttpStatus.OK); // 성공 시
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
