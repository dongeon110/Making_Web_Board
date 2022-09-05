<%@ page import="boardproject.vo.PostVO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:useBean
	id="updatePost"
	scope="request"
	class="boardproject.vo.PostVO"/>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		<jsp:setProperty name="updatePost" property="postSubject" param="postSubject"/>
		<jsp:setProperty name="updatePost" property="postText" param="postText"/>
		<jsp:setProperty name="updatePost" property="posterName" param="posterName"/>
		<jsp:setProperty name="updatePost" property="postPassword" param="postPassword"/>
	</body>
</html>