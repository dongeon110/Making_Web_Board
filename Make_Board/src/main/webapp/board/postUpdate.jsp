<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>게시글 수정</title>
	</head>
	<body>
		<h2>게시글 수정</h2>
			
			<form action="postUpdateSuccess.jsp" method="post">
				제목 : <input type='text' name='postSubject'><br>
				내용 : <input type='text' name='postText'><br>
				작성자 : <input type='text' name='posterName'><br>
				비밀번호 : <input type='password' name='postPassword'><br>
				
				<input type='submit' value='저장하기'>
				<input type='reset' value='취소'>
				
			</form>
			
	</body>
</html>