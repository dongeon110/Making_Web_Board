<%@ page import="boardproject.vo.PostVO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="postVO"
	scope="session"
	class="boardproject.vo.PostVO"/>


<div style="background-color:#00008b;color:#ffffff;height:35px;padding:5px;">
	<a style="color:white;" href="<%=request.getContextPath()%>/board/list.do">게시판</a>
	
	<span style="float:right;">
		<a style="color:white;" href="<%=request.getContextPath()%>/board/list.do">게시판</a>
		
		<c:if test="${empty sessionScope.loginUser or empty sessionScope.loginUser.userID}">
			<a style="color:white;"
				href="<%=request.getContextPath()%>/auth/login.do">로그인</a>
			<a style="color:white;"
				href="<%=request.getContextPath()%>/user/add.do">회원가입</a>
		</c:if>
		<c:if test="${!empty sessionScope.loginUser and !empty sessionScope.loginUser.userID}">
			<c:if test="${sessionScope.loginUser.grade == 1}">
				<a style="color:white;" href="<%=request.getContextPath()%>/user/list.do">회원 관리</a>
				${sessionScope.loginUser.userName} [관리자]
			</c:if>
			<c:if test="${sessionScope.loginUser.grade != 1}">
				${sessionScope.loginUser.userName} [일반]
			</c:if>
			(<a style="color:white;"
				href="<%=request.getContextPath()%>/auth/logout.do">로그아웃</a>)
		</c:if>
	</span>
	

</div>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		
	</body>
</html>