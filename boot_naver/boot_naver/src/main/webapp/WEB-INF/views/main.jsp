<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>맛집 WISH LIST</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
</head>
<body>
	<div class="container mt-3">
        <!-- search -->
        <div class="row">
            <div class="col-sm-6 col-md-8">
                <input id="searchBox" style="height: 46px" class="form-control form-control-lg" type="text" placeholder="맛집을 검색해주세요 ex.(판교 갈비집)">
            </div>
            <div class="col-sm-6 col-md-4">
                <button id="searchButton" type="button" class="btn btn-primary btn-lg" style="width: 100%">검색</button>
            </div>
        </div>

        <br/>
        <!-- search result -->
        <div class="row" id="search-result" style="visibility: hidden">
            <div class="col-sm-6 col-md-8">
                <img id="wish_image" alt="음식" class="img-thumbnail w-100">
            </div>
            <div class="col-sm-6 col-md-4">
                <ul class="list-group list-group-flush">
                    <li class="list-group-item" id="wish_title"></li>
                    <li class="list-group-item" id="wish_category"></li>
                    <li class="list-group-item" id="wish_address"></li>
                    <li class="list-group-item" id="wish_road_address"></li>
                    <li class="list-group-item"><a id="wish_link" target="_blank" class="">홈페이지</a></li>
                </ul>
                <button id="wishButton" type="button" class="btn btn-primary btn-lg d-block" >위시리스트 추가</button>
            </div>
        </div>   

        <div class="row mt-5">
            <div class="alert alert-info col-sm-12 col-md-12">
                나의 맛집 리스트
            </div>
        </div>        
        <div id="wish-list">            
           <hr class="mt-1" />
           <div class="row">
               <div class="col-sm-6 col-md-8">
                   <img alt="food" class="img-thumbnail w-100" src="https://placehold.co/400x200">
               </div>
                <div class='col-sm-6 col-md-4'>
                    <ul class='list-group list-group-flush'>
                        <li class='list-group-item'>장소 : </li>
                        <li class='list-group-item'>Category : </li>
                        <li class='list-group-item'>주소 : </li>
                        <li class='list-group-item'>도로명 : </li>
                        <li class='list-group-item'>방문여부 :</li>
                        <li class='list-group-item'>마지막 방문일자 : </li>
                        <li class='list-group-item'>방문횟수 : </li>
                        <li class='list-group-item'>
                            <a href=''>홈페이지</a>
                        </li>
                        <li class='list-group-item'>
                        	<div class='d-grid gap-2'>
							  <button class='btn btn-primary' type='button'>방문 추가</button>
							  <button class='btn btn-warning' type='button'>위시리스트 삭제</button>
							</div>
                        </li>                       
                    </ul>
                </div>                     
           </div>          
        </div>
    </div>  <!-- container end -->     
         
<script src="/js/main.js"></script>    
</body>
</html>