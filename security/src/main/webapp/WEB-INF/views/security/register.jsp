<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
    <h1>Register</h1>

<main class="form-signin w-100 m-auto">
	<form method="post" action="">
		<h1 class="h3 mb-3 fw-normal">회원가입</h1>

		<div class="form-floating">
			<label for="floatingInput">UserId</label>
			<input type="text" class="form-control" id="userid" placeholder="userid" name="userid">
		</div>
		<div class="form-floating">
			<label for="floatingPassword">Password</label>
			<input type="password" class="form-control" id="password" placeholder="password" name="password">
		</div>
		<div class="form-floating">
			<label for="floatingPassword">Email</label>
			<input type="email" class="form-control" id="email" placeholder="email" name="email">
		</div>
		
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		<button class="w-100 btn btn-lg btn-primary" type="submit">회원가입</button>
		<p class="mt-5 mb-3 text-body-secondary">&copy; 2017–2023</p>
	</form>
</main>
<%@ include file="../include/footer.jsp"%>