package com.example.restaurant.naver;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.restaurant.naver.dto.SearchImageReq;
import com.example.restaurant.naver.dto.SearchImageRes;
import com.example.restaurant.naver.dto.SearchLocalReq;
import com.example.restaurant.naver.dto.SearchLocalRes;

@SpringBootTest
public class NaverClientTest {

	@Autowired
	private NaverClient naverClient;
	
	@Test
	public void searchLocalTest() {
	
		SearchLocalReq req = new SearchLocalReq();
		req.setQuery("갈비집");
		
		SearchLocalRes res = naverClient.searchLocal(req);
		System.out.println(res);		
	}
	
	@Test
	public void searchImageTest() {
	
		SearchImageReq req = new SearchImageReq();
		req.setQuery("갈비집");
		
		SearchImageRes res = naverClient.searchImage(req);
		System.out.println("img 요청 "+res);		
	}
}










