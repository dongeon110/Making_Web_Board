<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>게시글 목록</title>
	</head>
	<body>
	
		
		<h1>게시글 목록</h1>
		<p><a href='add'>신규 글 작성</a></p>
		
		<c:forEach var="postVO" items="${postVOs}">
		${postVO.postNo}
		<a href='view?no=${postVO.postNo}'>${postVO.postSubject}</a>
		${postVO.posterName}
		${postVO.postCreatedDate}<br>
		</c:forEach>
		
		
		<c:if test='${startPage != 1}' var='pre'>
			<a href='list?pageNum=${startPage-1}'>&lt;이전&gt;</a>
		</c:if>
		<c:forEach var='page' begin='${startPage}' end='${endPage}'>
			<a href='list?pageNum=${page}'>&lt;${page}&gt;</a>
		</c:forEach>
		<c:if test='${endPage != cntPage}' var='next'>
			<a href='list?pageNum=${endPage+1}'>&lt;다음&gt;</a>
		</c:if>
		
		
		
	</body>
</html>