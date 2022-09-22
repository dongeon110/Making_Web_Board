<%@ page import = "boardproject.vo.User" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Update</title>
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT" crossorigin="anonymous">
	</head>
	<body>
		<jsp:include page="/Header.jsp"/>
		<h1>수정</h1>
		
		<form action='update.do' method='post'>
			<input type='hidden' name='no' value='${no}'><br>
			<input type='hidden' name='userNo' value='${user.userNo}'><br>
			ID: <input type='text' name='userID' value='${user.userID}'><br>
			이름: <input type='text' name='userName' value='${user.userName}'><br>
			암호: <input type='password' name='userPassword'><br>
			권한: <select id="grade" name='grade'>
				<option value="1" ${user.grade == 1? "selected" : ""}>관리자</option>
				<option value="2" ${user.grade == 2? "selected" : ""}>일반</option>
			</select>
			<input type='submit' value='수정'>
			<input type='reset' value='취소'>
			
		</form>
		<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js" integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3" crossorigin="anonymous"></script>
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.min.js" integrity="sha384-7VPbUDkoPSGFnVtYi0QogXtr74QeVeeIs99Qfg5YCF+TidwNdjvaKZX19NZ/e6oz" crossorigin="anonymous"></script>
		
	</body>
</html>