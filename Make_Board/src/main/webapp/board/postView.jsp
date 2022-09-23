<%@ page import = "java.util.HashMap" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<% pageContext.setAttribute("replaceChar", "\n"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>게시글 내용</title>
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT" crossorigin="anonymous">
	</head>
	<body>
		<jsp:include page="/Header.jsp"/>
		<div class='text-center'>
			<h1>게시글 내용</h1>
		</div>
			<ul>
				<li><label>번호 : </label>
				${postVO.postNo}</li>
				
				<li><label>제목 : </label>
				${postVO.postSubject}</li>
				
				<li><label>조회수 : </label>
				${postVO.postViews}</li>
				
				<li><label>작성자 : </label>
					<c:if test="${postVO.postUserNo != null and postVO.postUserNo != ''}">
						<c:if test="${postVO.postPassword == '1'}">
							<span style="color:red">[관리자]</span></c:if>
						<c:if test="${postVO.postPassword == '2'}">
							<span style="color:blue">[사용자]</span></c:if>
					</c:if>
					${postVO.posterName}<br/><br/></li>
				
				<li><label>&nbsp;내용<br/></label>
				${fn:replace(postVO.postText, replaceChar, "<br/>")}<br/><br/></li>
				
				<li><label>작성일 : </label>
				${postVO.postCreatedDate}</li>
				
				<li><label>수정일 : </label>
				${postVO.postModifyDate}</li>
				
			</ul>
			
		<form action="repost.do" method="get">
			<input type='hidden' name='no' value='${postVO.repost}'>
			<input type='button' value='수정하기' onclick='location.href="update.do?no=${postVO.postNo}";'>
			<input type='button' value='돌아가기' onclick='location.href="list.do?viewNo=${postVO.postNo}"'>
			<input type='button' value='삭제하기' onclick='location.href="delete.do?no=${postVO.postNo}";'>
			<input type='submit' value='답글달기'>
		</form>
		
		<jsp:include page="/Tail.jsp"/>
		<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js" integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3" crossorigin="anonymous"></script>
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.min.js" integrity="sha384-7VPbUDkoPSGFnVtYi0QogXtr74QeVeeIs99Qfg5YCF+TidwNdjvaKZX19NZ/e6oz" crossorigin="anonymous"></script>
	</body>
</html>