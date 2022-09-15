<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>비밀번호 확인</title>
	</head>
	<body>
		<jsp:include page="/Header.jsp"/>
		<h2>비밀번호 확인</h2>
		<form action='update.do?no=${no}' method="post">
			글 번호 : <input type="text" name="no" value='${no}' readonly><br>
			비밀번호 : <input type="password" name="password" value='${password}'><br>
			<input type="submit" value="확인">
		
		</form>
		<jsp:include page="/Tail.jsp"/>
	</body>
</html>