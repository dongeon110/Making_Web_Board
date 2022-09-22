<%@ page import = "boardproject.vo.PostVO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>게시글 수정</title>
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT" crossorigin="anonymous">
	</head>
	<body>
		<jsp:include page="/Header.jsp"/>
		<div class='text-center'>
			<h2>게시글 수정</h2>
		</div>
		<form action="update.do" method="post">
			<ul>
				<li><label for="postNo">번호</label>
				<input id="postNo"
				type='text' name='postNo' size='10' value='${postVO.postNo}' readonly></li>
				
				<li><label for="postSubject">제목</label>
				<input id="postSubject"
				type='text' name='postSubject' size='50' value='${postVO.postSubject}'></li>
				
				<li><label for="postText">내용</label>
				<textarea id="postText"
				name='postText' rows='5' cols='40'>${postVO.postText}</textarea></li>
				
				<c:if test="${empty sessionScope.loginUser or empty sessionScope.loginUser.userID}">
					<li><label for="posterName">작성자</label>
					<input id="posterName"
					type='text' name='posterName' value='${postVO.posterName}'></li>
				</c:if>
				
				<c:if test="${empty sessionScope.loginUser or empty sessionScope.loginUser.userID}">
					<li><label for="postPassword">비밀번호</label>
					<input id="postPassword"
					type='password' name='postPassword' value='${postVO.postPassword}'></li>
				</c:if>
				
			</ul>
			<input type='hidden' name='repost' value='${postVO.repost}'>
			<input type='hidden' name='no' value='${postVO.postNo}'>
			<input type='submit' value='저장하기'>
			<input type='reset' value='다시쓰기'>
			
		</form>
		<jsp:include page="/Tail.jsp"/>
		<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js" integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3" crossorigin="anonymous"></script>
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.min.js" integrity="sha384-7VPbUDkoPSGFnVtYi0QogXtr74QeVeeIs99Qfg5YCF+TidwNdjvaKZX19NZ/e6oz" crossorigin="anonymous"></script>
	</body>
</html>