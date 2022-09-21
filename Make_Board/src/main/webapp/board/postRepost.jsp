<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>답글 등록</title>
	</head>
	<body>
		<jsp:include page="/Header.jsp"/>
		<h1>답글 등록</h1>
		
		<form action='repost.do' method='post'>
			<ul>
				<li><label for="postSubject">답글 제목</label>
				<input id="postSubject" type='text'
				name='postSubject' size='50' value='${postVO.postSubject}'></li>
				
				<li><label for="postText">내용</label>
				<textarea id="postText"
				name='postText' rows='5' cols='40'>${postVO.postText}</textarea></li>
				
				<c:if test="${empty sessionScope.loginUser or empty sessionScope.loginUser.userID}">
					<li><label for="postPassword">비밀번호</label>
					<input id="postPassword"
					type='password' name='postPassword'></li>
				</c:if>
				<c:if test="${!empty sessionScope.loginUser and !empty sessionScope.loginUser.userID}">
					<input id="postPassword"
					type='hidden' name='postPassword' value='user'>
				</c:if>
				
				<c:if test="${empty sessionScope.loginUser or empty sessionScope.loginUser.userID}">
					<li><label for="posterName">작성자</label>
					<input id="posterName"
					type='text' name='posterName'></li>
				</c:if>
				
				<c:if test="${!empty sessionScope.loginUser and !empty sessionScope.loginUser.userID}">
					<input id="posterName"
					type='hidden' name='posterName' value='${sessionScope.loginUser.userID}'>
				</c:if>
			</ul>
			<input type='hidden' name='no' value='${no}'>
			<input type='submit' value='추가'>
			<input type='reset' value='취소'>
		</form>
		
		<jsp:include page="/Tail.jsp"/>
	</body>
</html>