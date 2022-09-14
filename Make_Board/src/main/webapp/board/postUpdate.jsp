<%@ page import = "boardproject.vo.PostVO" %>
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

			<form action="update.do" method="post">
				<ul>
					<li><label for="postNo">번호</label>
					<input id="postNo"
					type='text' name='postNo' size='10' value='${postVO.postNo}' readonly></li>
					
					<li><label for="postSubject">제목</label>
					<input id="postSubject"
					type='text' name='postSubject' size='50' value='${postVO.postSubject}'></li>
					
					<li><label for="postText">내용</label>
					<textarea id="postText"
					name='postText' rows='5' cols='40'>${postVO.postText}</textarea></li>
					
					<li><label for="posterName">작성자</label>
					<input id="posterName"
					type='text' name='posterName' value='${postVO.posterName}'></li>
					
					<li><label for="postPassword">비밀번호</label>
					<input id="postPassword"
					type='password' name='postPassword' value='${postVO.postPassword}'></li>

				</ul>
				<input type='hidden' name='repost' value='${postVO.repost}'>
				<input type='hidden' name='no' value='${postVO.postNo}'>
				<input type='submit' value='저장하기'>
				<input type='reset' value='다시쓰기'>
				
			</form>
			
	</body>
</html>