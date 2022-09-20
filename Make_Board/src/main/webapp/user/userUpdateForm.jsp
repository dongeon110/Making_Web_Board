<%@ page import = "boardproject.vo.User" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Update</title>
	</head>
	<body>
		<jsp:include page="/Header.jsp"/>
		<h1>수정</h1>
		
		<form action='update.do' method='post'>
			<input type='text' name='no' value='${no}' readonly><br>
			<input type='text' name='userNo' value='${user.userNo}' readonly><br>
			ID: <input type='text' name='userID' value='${user.userID}'><br>
			이름: <input type='text' name='userName' value='${user.userName}'><br>
			암호: <input type='password' name='userPassword' value='${user.userPassword}'><br>
			권한:
			<input type='submit' value='수정'>
			<input type='reset' value='취소'>
			
		</form>
		
		
	</body>
</html>