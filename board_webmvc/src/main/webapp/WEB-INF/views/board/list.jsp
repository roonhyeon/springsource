<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp" %>
<!-- Page Heading -->
<div class="d-sm-flex align-items-center justify-content-between mb-4">
	<h1 class="h3 mb-0 text-gray-800">Board List</h1>
</div>
<div class="row">
    <div class="col">
        <div class="panel panel-default">
            <div class="d-flex pb-2 justify-content-end">
                <button class="btn btn-xs btn-success" type="button" onclick="location.href='/board/register'">Register New Board</button>
            </div>
            <div class="d-flex">
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
			</div>
				<!-- 페이지 나누기 시작 -->
				<nav aria-label="...">
					<ul class="pagination justify-content-center">
						<li class="page-item disabled"><a class="page-link">Previous</a>
						</li>
						<li class="page-item"><a class="page-link" href="#">1</a></li>
						<li class="page-item active" aria-current="page"><a
							class="page-link" href="#">2</a></li>
						<li class="page-item"><a class="page-link" href="#">3</a></li>
						<li class="page-item"><a class="page-link" href="#">Next</a>
						</li>
					</ul>
				</nav>
				<!-- 페이지 나누기 종료 -->
	    </div>
    </div>
</div>
<%@ include file="../include/footer.jsp" %>