<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>답글 등록</title>
</head>
<body>


<h1>답글 등록</h1>
<form action='repost' method='post'>
	제목: [RE:]<input type='text' name='postSubject'><br>
	내용: <input type='text' size='100' name='postText'><br>
	게시글 비밀번호: <input type='password' name='postPassword'><br>
	작성자명 : <input type='text' name='posterName'><br>
	<input type='hidden' name='repost' value='${mainPostVO.repost}'>
	<input type='submit' value='추가'>
	<input type='reset' value='취소'>
</form>


</body>
</html>