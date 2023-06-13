package com.example.restaurant.naver;

import java.net.URI;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.restaurant.naver.dto.SearchImageReq;
import com.example.restaurant.naver.dto.SearchImageRes;
import com.example.restaurant.naver.dto.SearchLocalReq;
import com.example.restaurant.naver.dto.SearchLocalRes;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class NaverClient {
	
	//application.properties 에 있는 정보 가져오기
	@Value("${naver.client.id}")
	private String naverClientId;
	
	@Value("${naver.client.secret}")
	private String naverClientSecret;
	
	@Value("${naver.url.search.local}")
	private String naverLocalSearchUrl;
	
	@Value("${naver.url.search.image}")
	private String naverImageSearchUrl;
	
	
	public SearchLocalRes searchLocal(SearchLocalReq searchLocalReq) {
		
		URI uri = UriComponentsBuilder.fromUriString(naverLocalSearchUrl)									  
									  .queryParams(searchLocalReq.toMultiValueMap())									  
									  .encode()
									  .build()
									  .toUri();
		//헤더 추가
		HttpHeaders headers = new HttpHeaders();
		
		headers.set("X-Naver-Client-Id", naverClientId);
		headers.set("X-Naver-Client-Secret", naverClientSecret);
		headers.setContentType(MediaType.APPLICATION_JSON);		
		HttpEntity<HttpHeaders> httpEntity = new HttpEntity<>(headers);
		
		log.info("clientID,{}",naverClientId);
		log.info("naverClientSecret,{}",naverClientSecret);	
		
		
		ParameterizedTypeReference<SearchLocalRes> resReference = new ParameterizedTypeReference<SearchLocalRes>() {};	
		
		ResponseEntity<SearchLocalRes> resEntity = 
				new RestTemplate().exchange(uri, HttpMethod.GET, httpEntity, resReference);
		return resEntity.getBody();		
	}
	
	
	public SearchImageRes searchImage(SearchImageReq searchImageReq) {
		
		URI uri = UriComponentsBuilder.fromUriString(naverImageSearchUrl)									  
									  .queryParams(searchImageReq.toMultiValueMap())									  
									  .encode()
									  .build()
									  .toUri();
		//헤더 추가
		HttpHeaders headers = new HttpHeaders();		
		headers.set("X-Naver-Client-Id", naverClientId);
		headers.set("X-Naver-Client-Secret", naverClientSecret);
		headers.setContentType(MediaType.APPLICATION_JSON);		
		HttpEntity<HttpHeaders> httpEntity = new HttpEntity<>(headers);
	
		ParameterizedTypeReference<SearchImageRes> resReference 
						= new ParameterizedTypeReference<SearchImageRes>() {};	
		
		ResponseEntity<SearchImageRes> resEntity = 
				new RestTemplate().exchange(uri, HttpMethod.GET, httpEntity, resReference);
		return resEntity.getBody();		
	}
}
















