<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>회원 목록</title>
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT" crossorigin="anonymous">
	</head>
	<body>
		<jsp:include page="/Header.jsp"/>
		<h1>회원 목록</h1>
		<p><a href='add.do'>신규 회원</a></p>
		
		<%
		// 페이지 구성
		int pageNum = (int)request.getAttribute("pageNum");
		int pageSize = (int)request.getAttribute("pageSize");
		int cntUser = (int)request.getAttribute("cntUser");
		int cntPage = cntUser / pageSize + (cntUser%pageSize==0? 0:1);
		
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
				<th>ID</th>
				<th>이름</th>
				<th>가입일</th>
				<th>권한</th>
				<th></th>
			</tr>
			
			<c:forEach var="user" items="${userList}">
			<tr>
				<td>${user.userNo}</td>
				<td><a href='update.do?no=${user.userNo}'>${user.userID}</a></td>
				<td>${user.userName}</td>
				<td>${user.createdDate}</td>
				<td><c:choose>
					<c:when test='${user.grade == 1}'>관리자</c:when>
					<c:otherwise>일반</c:otherwise>
				</c:choose></td>
				<td><a href='delete.do?userNo=${user.userNo}'>[삭제]</a></td>
			</tr>
			</c:forEach>
		</table>
		
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
		<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js" integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3" crossorigin="anonymous"></script>
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.min.js" integrity="sha384-7VPbUDkoPSGFnVtYi0QogXtr74QeVeeIs99Qfg5YCF+TidwNdjvaKZX19NZ/e6oz" crossorigin="anonymous"></script>
	</body>
</html>