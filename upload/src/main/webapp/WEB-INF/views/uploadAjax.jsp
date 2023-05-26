<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/resources/css/upload.css"/>
<link rel="stylesheet" href="/resources/css/lightbox.min.css"/>
<script src="/resources/js/jquery.min.js"></script>
<script src="/resources/js/lightbox.min.js"></script>
</head>
<body>
<h1>Upload Ajax</h1>
<div class="uploadDiv">
	<input type="file" name="uploadFile" id="uploadFile" accept="image/*, .txt" multiple/>
</div>
<button type="submit" id="uploadBtn">upload</button>

<div class="uploadResult">
	<ul></ul>
</div>
<script src="/resources/js/upload.js"></script>
</body>
</html>