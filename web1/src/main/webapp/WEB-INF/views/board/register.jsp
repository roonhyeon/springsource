<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <h1>BOARD REGISTER</h1>
    <form action="" method="post">
		<div>
			<label for="name">name</label> 
			<input type="text" name="name" id="name" value="${dto.name}"/>
		</div>
		<div>
			<label for="password">password</label> 
			<input type="password" name="password" id="password" value="${dto.password}"/>
		</div>
		<div>
			<label for="title">title</label> 
			<input type="text" name="title" id="title" value="${dto.title}"/>
		</div>
		<div>
			<label for="content">content</label> 
			<textarea rows="5" cols="30" name="content">${dto.content}</textarea>
		</div>
		<div>
			<button type="submit">등록</button>
		</div>
	</form>
</body>
</html>