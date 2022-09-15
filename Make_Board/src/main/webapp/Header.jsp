<%@ page import="boardproject.vo.PostVO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="postVO"
	scope="session"
	class="boardproject.vo.PostVO"/>


<div style="background-color:#00008b;color:#ffffff;height:20px;padding:5px;">
	<a style="color:white;" href="<%=request.getContextPath()%>/board/list.do">게시판</a>
	
	<span style="float:right;">
		<a style="color:white;" href="<%=request.getContextPath()%>/board/list.do">게시판</a>
		<!-- 
		<c:if test="${empty sessionScope.member or empty sessionScope.member.email}">
			<a style="color:white;"
				href="<%=request.getContextPath()%>/auth/login.do">로그인</a>
		</c:if>
		<c:if test="${!empty sessionScope.member and !empty sessionScope.member.email}">
			${sessionScope.member.name}
			(<a style="color:white;"
				href="<%=request.getContextPath()%>/auth/login.do">로그아웃</a>)
		</c:if>  -->
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