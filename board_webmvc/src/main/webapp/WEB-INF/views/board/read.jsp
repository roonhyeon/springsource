<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp" %>
<!-- Page Heading -->
<div class="d-sm-flex align-items-center justify-content-between mb-4">
	<h1 class="h3 mb-0 text-gray-800">Board Read</h1>
</div>
<div class="row">
    <div class="col">
		<form action="" method="post">
			<div class="form-group">
				<label for="title">Title</label>
				<input type="text" class="form-control" id="title" name="title" readonly value="${dto.title}">
			</div>
			<div class="form-group">
				<label for="content">Content</label>
				<textarea class="form-control" id="content" rows="10" name="content" readonly>${dto.content}</textarea>
			</div>
			<div class="form-group">
				<label for="writer">Writer</label>
				<input type="text" class="form-control" id="writer" name="writer" readonly value="${dto.writer}">
			</div>
			<button type="button" class="btn btn-info">수정</button>
			<button type="button" class="btn btn-secondary">목록</button>
		</form>
	</div>
</div>
<form action="" id="operForm">
	<input type="hidden" name="bno" value="${dto.bno}" />
    <input type="hidden" name="page" value="${cri.page}" />
    <input type="hidden" name="amount" value="${cri.amount}" />
    <!-- 수정 버튼 눌렀을 때 타입과 키워드 주소창에 유지 -->
    <input type="hidden" name="type" value="${cri.type}" />
    <input type="hidden" name="keyword" value="${cri.keyword}" />
</form>
<script src="/resources/js/read.js"></script>
<%@ include file="../include/footer.jsp" %>