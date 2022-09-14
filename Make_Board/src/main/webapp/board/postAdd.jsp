<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시글 등록</title>
</head>
<body>


<h1>게시글 등록</h1>
<form action='add.do' method='post'>
	제목: <input type='text' name='postsubject'><br>
	내용: <input type='text' size='100' name='posttext'><br>
	게시글 비밀번호: <input type='password' name='postpassword'><br>
	작성자명 : <input type='text' name='postername'><br>
	<input type='submit' value='추가'>
	<input type='reset' value='취소'>
</form>


</body>
</html>