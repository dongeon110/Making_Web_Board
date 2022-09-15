<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.HashMap" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>게시글 목록</title>
	</head>
	<body>
	
		<jsp:include page="/Header.jsp"/>
		<h1>게시글 목록</h1>
		<p><a href='add.do'>신규 글 작성</a></p>
		
		<%
		// 페이지 구성
		int pageNum = (int)request.getAttribute("pageNum");
		int pageSize = (int)request.getAttribute("pageSize");
		int cntPost = (int)request.getAttribute("cntPost");
		int cntPage = cntPost / pageSize + (cntPost%pageSize==0? 0:1);
		
		pageContext.setAttribute("cntPage", cntPage);
		
		// 한 화면에 보여줄 페이지 블록 수
		int pageBlock = 5;
		
		// 블록 시작과 끝
		int startPage = ((pageNum-1)/pageBlock)*pageBlock +1;
		int endPage = startPage + pageBlock - 1;
		if (endPage>cntPage) {endPage = cntPage;}
		
		pageContext.setAttribute("startPage", startPage);
		pageContext.setAttribute("endPage", endPage);
		
		int startRow = (pageNum-1) * pageSize;
		pageContext.setAttribute("startRow", startRow);
		%>
		
		
		<table border="1">
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>작성일</th>
			</tr>
			
			
			<c:forEach var="postVO" items="${postVOs}" begin="${startRow}" end="${startRow + pageSize}">
				<tr>
					<td>${postVO.postNo}</td>
					<td>
						<c:if test="${postVO.repost != postVO.postNo}" var="re">
						┌[RE:]
						</c:if>
						<a href='view.do?no=${postVO.postNo}'>${postVO.postSubject}</a></td>
					<td>${postVO.posterName}</td>
					<td>${postVO.postCreatedDate}</td>
				</tr>
			</c:forEach>
		</table>
		<form action="list.do" method="POST">
			<button type='submit'>TEST</button>
		</form>
		
		<form action="list.do" method="POST">
			<select id="column" name="column">
				<option value="psubject" ${column == "psubject"? "selected" : ""}>제목</option>
				<option value="ptext" ${column == "ptext"? "selected" : ""}>내용</option>
				<option value="postername" ${column == "postername"? "selected" : ""}>작성자</option>
			</select>
			<input type='text' name='search' value='${search}'>
			<button type='submit'>검색</button>
		</form>
		
		<c:if test='${startPage != 1}' var='pre'>
			<a href='list.do?pageNum=${startPage-1}'>&lt;이전&gt;</a>
		</c:if>
		<c:forEach var='page' begin='${startPage}' end='${endPage}'>
			<a href='list.do?pageNum=${page}'>&lt;${page}&gt;</a>
		</c:forEach>
		<c:if test='${endPage != cntPage}' var='next'>
			<a href='list.do?pageNum=${endPage+1}'>&lt;다음&gt;</a>
		</c:if>
		
		<jsp:include page="/Tail.jsp"/>
		
	</body>
</html>