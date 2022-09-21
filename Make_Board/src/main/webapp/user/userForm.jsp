<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4loose.dtd">

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>회원 등록</title>
	</head>
	<body>
		
		<jsp:include page="/Header.jsp"/>
		<h1>회원 등록</h1>
		<form action='add.do' method='post'>
			ID: <input type='text' name='userID'><br>
			이름: <input type='text' name='userName'><br>
			비밀번호 : <input type='password' name='userPassword'><br>
			<input type='submit' value='추가'>
			<input type='reset' value='취소'>
		</form>
		<jsp:include page="/Tail.jsp"/>
		
	</body>
</html>