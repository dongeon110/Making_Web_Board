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
		<title>게시글 내용</title>
	</head>
	<body>
		<jsp:include page="/Header.jsp"/>
		
		<h1>게시글 내용</h1>
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
			<input type='button' value='돌아가기' onclick='history.back()'>
			<input type='button' value='삭제하기' onclick='location.href="delete.do?no=${postVO.postNo}";'>
			<input type='submit' value='답글달기'>
		</form>
		
		<jsp:include page="/Tail.jsp"/>
	</body>
</html>