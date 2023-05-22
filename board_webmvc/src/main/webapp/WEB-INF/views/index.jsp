<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="include/header.jsp" %>
<!-- Page Heading -->
<div class="d-sm-flex align-items-center justify-content-between mb-4">
	<h1 class="h3 mb-0 text-gray-800">Board</h1>
</div>
<div id="content">
	<%-- get 방식으로 주소줄에 총 4개의 변수를 보낸다. page=1, amount=10, type=, keyword= --%>
    <a href="/board/list?page=1&amount=10&type=&keyword=">전체 리스트 보기</a>
</div>
<%@ include file="include/footer.jsp" %>