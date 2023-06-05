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

<!-- 파일 첨부 폼 -->
<div class="row mt-3">
	<div class="col">
		<div class="card">
			<div class="card-header">
				<i class="fa fa-file"></i> 
				파일첨부
			</div>
			<div class="card-body">
				<div class="uploadResult">
					<ul></ul>
				</div>
			</div>
		</div>
	</div>
</div>

<!-- 댓글 작성 폼 -->
<form action="" class="mt-3" id="replyForm">
	<div class="form-row">
		<div class="col-11">
			<textarea name="reply" id="reply" rows="5" class="form-control"></textarea>
		</div>
		<div class="col my-2">
			<div class="form-row my-2">
				<input type="text" name="replyer" id="replyer" class="form-control" />
			</div>
			<div class="form-row my-2">
				<button class="btn btn-success btn-block">댓글 작성</button>
			</div>
		</div>
	</div>
</form>

<!-- 댓글 목록 -->
<div class="card mt-4">
	<div class="card-header">
		<i class="fa fa-comments fa-fw"></i>
		Reply
	</div>
	<div class="card-body">
		<ul class="chat list-group list-group-flush">
			<li class='list-group-item border-bottom' data-rno='1'>
				<div class="d-flex justify-content-between">
					<strong class='primary-font'>user00</strong>
					<small class='text-muted text-right'>2023-05-24 00:00</small>
				</div>
				<p>Good Job!!</p>
				<div class="btn-group btn-group-sm">
					<button class="btn btn-warning" type="button">수정</button>
					<button class="btn btn-danger" type="button">삭제</button>
				</div>
			</li>
		</ul>
	</div>
	<div class="card-footer">
		<!-- 댓글 페이지 나누기 -->
	</div>
</div>

<!-- 댓글 수정 폼 -->
<div class="modal" tabindex="-1" id="replyModal">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">댓글 수정</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
      	<input type="hidden" name="rno" id="rno"/>
      	<div class="form-group">
      		<textarea name="reply" id="reply" rows="4" class="form-control"></textarea>
      	</div>
      	<div class="form-group">
      		<input type="text" name="replyer" id="replyer" class="form-control" readonly/>
      	</div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">수정</button>
      </div>
    </div>
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
<script>
	// 게시물 글번호 가져오기(1번째 실행)
	const bno=${dto.bno};
</script>
<script src="/resources/js/reply.js"></script> <!-- 2번째 실행 -->
<script src="/resources/js/read.js"></script> <!-- 3번째 실행 -->
<%@ include file="../include/footer.jsp" %>