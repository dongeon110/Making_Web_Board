<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>게시글 등록</title>
	</head>
	<body>
		<jsp:include page="/Header.jsp"/>
		<h1>게시글 등록</h1>
		
		<form action='add.do' method='post'>
			
			<ul>
				<li><label for="postSubject">제목</label>
				<input id="postSubject"
				type='text' name='postSubject' size='50'></li>
				
				<li><label for="postText">내용</label>
				<textarea id="postText"
				name='postText' rows='5' cols='40'></textarea></li>
				
				<li><label for="posterName">작성자</label>
				<input id="posterName"
				type='text' name='posterName'></li>
				
				<li><label for="postPassword">비밀번호</label>
				<input id="postPassword"
				type='password' name='postPassword'></li>
			</ul>
			
			<input type='submit' value='추가'>
			<input type='reset' value='취소'>
		</form>
		<jsp:include page="/Tail.jsp"/>
	</body>
</html>