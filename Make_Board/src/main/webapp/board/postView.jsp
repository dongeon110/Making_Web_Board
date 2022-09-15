<%@ page import = "boardproject.vo.PostVO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>게시글 내용</title>
	</head>
	<body>
		
		<h1>게시글 내용</h1>
			번호 : ${postVO.postNo} <br>
			제목 : ${postVO.postSubject} <br>
			작성자 : ${postVO.posterName} <br>
			<br>
			내용 : ${postVO.postText} <br>
			<br>
			작성일 : ${postVO.postCreatedDate} <br>
			수정일 : ${postVO.postModifyDate} <br>
			
		<form action="repost.do" method="get">
			<input type='hidden' name='no' value='${postVO.repost}'>
			<input type='button' value='수정하기' onclick='location.href="update.do?no=${postVO.postNo}";'>
			<input type='button' value='돌아가기' onclick='history.back();'>
			<input type='button' value='삭제하기' onclick='location.href="delete.do?no=${postVO.postNo}";'>
			<input type='submit' value='답글달기'>
		</form>
			
	</body>
</html>