<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp" %>
<!-- Page Heading -->
<div class="d-sm-flex align-items-center justify-content-between mb-4">
	<h1 class="h3 mb-0 text-gray-800">Board List</h1>
</div>
<div class="d-flex">
     <!-- 검색 부분 -->
     <div class="flex-grow-1 pb-2 justify-content-between">
         <form action="">
             <div class="form-row">
                 <div class="form-group col-3">
                     <select name="type" id="type" class="form-control">
                         <option value="">------------</option>
                         <option value="T">제목</option>
                         <option value="C">내용</option>
                         <option value="W">작성자</option>
                         <option value="TC">제목 or 내용</option>
                         <option value="TW">제목 or 작성자</option>
                         <option value="TCW">제목 or 내용 or 작성자</option>                     
                     </select>
                 </div>
                 <div class="form-group col-5">
                     <input type="text" name="keyword" id="keyword" class="form-control"/>
                 </div>
                 <div class="form-group col-3">
                     <button type="button" class="btn btn-info">검색</button>
                 </div>
             </div>
         </form>
     </div>
     <!-- 검색 종료 -->
     
     <div class="pb-2">
         <button class="btn btn-xs btn-success" type="button" onclick="location.href='/board/register'">Register New Board</button>
     </div>
</div>
<table class="table table-striped table-bordered table-hover">
	<thead>
		<tr>
			<th scope="col">번호</th>
			<th scope="col">제목</th>
			<th scope="col">작성자</th>
			<th scope="col">작성일</th>
			<th scope="col">수정일</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="dto" items="${list}">
			<tr>
				<th scope="row">${dto.bno}</th>
				<td><a href="/board/read?bno=${dto.bno}">${dto.title}</a></td>
				<td>${dto.writer}</td>
				<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${dto.regDate}" /></td>
				<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${dto.updateDate}" /></td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<!-- 페이지 나누기 시작 -->
<nav aria-label="...">
	<ul class="pagination justify-content-center">
	    <c:if test="${pageDTO.prev}">
		    <li class="page-item"><a class="page-link" href="${pageDTO.startPage-1}">Previous</a></li>
		</c:if>
		
		<c:forEach begin="${pageDTO.startPage}" end="${pageDTO.endPage}" var="idx">
			<li class="page-item ${pageDTO.cri.pageNum==idx?'active':''}"><a class="page-link" href="${idx}">${idx}</a></li>
		</c:forEach>
		
		<c:if test="${pageDTO.next}">
			<li class="page-item"><a class="page-link" href="${pageDTO.endPage+1}">Next</a></li>
		</c:if>
	</ul>
</nav>
<!-- 페이지 나누기 종료 -->

<!-- Modal for 알림창 -->
<div class="modal" tabindex="-1" id="registerModal">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">게시글 등록</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <p>처리가 완료되었습니다.</p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>
<script>
    const result='${result}'; // 컨트롤러에 정의한 것에 따라 1. result='success'; => 수정 및 삭제 시 result에 넘어오는 값, 2. result='글 번호'; => 등록 시 result에 넘어오는 값
</script>
<script src="/resources/js/list.js"></script>
<%@ include file="../include/footer.jsp" %>