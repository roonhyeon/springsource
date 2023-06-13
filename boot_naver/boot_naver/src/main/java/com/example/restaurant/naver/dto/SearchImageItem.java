package com.example.restaurant.naver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchImageItem {
	private String title;   		// 이미지가 검색된 문서의 제목
	private String link;			// 이미지의 URL
	private String thumbnail;		// 섬네일 이미지의 URL
	private String sizeheight;		// 이미지의 세로 크기(단위: 픽셀)
	private String sizewidth;		// 이미지의 가로 크기(단위: 픽셀)
}
