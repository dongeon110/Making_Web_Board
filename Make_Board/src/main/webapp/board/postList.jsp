<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.HashMap" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		
		<title>게시글 목록</title>
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT" crossorigin="anonymous">
	</head>
	<body>
	
		<jsp:include page="/Header.jsp"/>
		
		<div class='text-center'>
			<h1>게시글 목록</h1>
		</div>
		
		<div class="container">
			<button style="float:right;" onclick="location.href='add.do';">신규 글 작성</button>
			<table class="table table-hover">
				<tr>
					<th style="text-align: center;">번호</th>
					<th style="text-align: center;">제목</th>
					<th style="text-align: center;">조회수</th>
					<th style="text-align: center;">작성자</th>
					<th style="text-align: center;">작성일</th>
				</tr>
				
				
				<c:forEach var="postVO" items="${postVOs}" begin="${startRow}" end="${startRow + pageSize-1}">
					<tr>
						<td style="text-align: center;">${postVO.postNo}</td>
						<td>
							<c:if test="${postVO.repost != postVO.postNo}" var="re">
							┌[RE:]
							</c:if>
							<a href='view.do?no=${postVO.postNo}'>${postVO.postSubject}</a></td>
						<td style="text-align: center;">${postVO.postViews}</td>
						<td style="text-align: center;">
							<c:if test="${postVO.postUserNo != null and postVO.postUserNo !=''}">
								<c:if test="${postVO.postPassword == '1'}">
									<span style="color:red">[관리자]</span></c:if>
								<c:if test="${postVO.postPassword == '2'}">
									<span style="color:blue">[사용자]</span></c:if>
							</c:if>
							${postVO.posterName}
						</td>
						
						<td style="text-align: center;">${postVO.postCreatedDate}</td>
					</tr>
				</c:forEach>
			</table>
			<div class='d-flex justify-content-center'>
				<form action="list.do" method="POST">
					<select id="column" name="column">
						<option value="psubject" ${column == "psubject"? "selected" : ""}>제목</option>
						<option value="ptext" ${column == "ptext"? "selected" : ""}>내용</option>
						<option value="postername" ${column == "postername"? "selected" : ""}>작성자</option>
					</select>
					<input type='text' name='search' value='${search}' size='50'>
					<button type='submit'>검색</button>
				</form>
			</div>
			<div class='d-flex justify-content-center'>
				<ul class='pagination'>
					<c:if test='${startPage != 1}' var='pre'>
						<li><a href='list.do?pageNum=${startPage-1}'>&lt;이전&gt;</a></li>
					</c:if>
					<c:forEach var='page' begin='${startPage}' end='${endPage}'>
						<li><a href='list.do?pageNum=${page}' ${page == pageNum? "style='color: black'" : ""}>&lt;${page}&gt;</a></li>
					</c:forEach>
					<c:if test='${endPage != cntPage}' var='next'>
						<li><a href='list.do?pageNum=${endPage+1}'>&lt;다음&gt;</a></li>
					</c:if>
				</ul>
			</div>
		</div>
		
		<jsp:include page="/Tail.jsp"/>
		<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js" integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3" crossorigin="anonymous"></script>
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.min.js" integrity="sha384-7VPbUDkoPSGFnVtYi0QogXtr74QeVeeIs99Qfg5YCF+TidwNdjvaKZX19NZ/e6oz" crossorigin="anonymous"></script>
	</body>
</html>