package com.spring.domain;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString @AllArgsConstructor @NoArgsConstructor
public class FileDTO {
	private String name;
	// file 요소 처리를 위한 코드
	private MultipartFile[] file; // jsp의 name이랑 맞춰주기

}
