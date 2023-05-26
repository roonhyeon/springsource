<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- form 안 file 요소 존재 시 enctype 변경 필수 -->
	<form action="" method="post" enctype="multipart/form-data">
		<div>
			<label for="name">name</label>
			<input type="text" name="name" id="name"/>
		</div>
		<div>
			<label for="file">file</label>
			<input type="file" name="file" id="file" multiple accept="image/*, .txt"/>
		</div>
		<div>
			<button type="submit">submit</button>
		</div>
	</form>
</body>
</html>