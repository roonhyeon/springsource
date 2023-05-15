<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <h1>REGISTER</h1>
    <form action="" method="post">
		<div>
			<label for="id">id</label> 
			<input type="text" name="id" id="id" />
		</div>
		<div>
			<label for="password">password</label> 
			<input type="password" name="password" id="password" />
		</div>
		<div>
			<label for="name">name</label> 
			<input type="text" name="name" id="name" />
		</div>
		<div>
			<label for="email">email</label> 
			<input type="email" name="email" id="email" />
		</div>
		<div>
			<button type="submit">회원가입</button>
		</div>
	</form>
</body>
</html>