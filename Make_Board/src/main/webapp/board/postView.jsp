<%@ page import = "boardproject.vo.PostVO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>게시글 내용</title>
	</head>
	<body>
		
		<h1>게시글 내용</h1>
		
		<form action='view' method='post'>
		
			번호 : ${postVO.postNo} <br>
			제목 : ${postVO.postSubject} <br>
			작성자 : ${postVO.posterName} <br>
			<br>
			내용 : ${postVO.postText} <br>
			<br>
			작성일 : ${postVO.postCreatedDate} <br>
			수정일 : ${postVO.postModifyDate} <br>
			
			<input type='button' value='수정하기' onclick='location.href="update?no=${postVO.postNo}";'>
			<input type='button' value='돌아가기' onclick='location.href="list";'>
			
		</form>
		
		
		
	</body>
</html>