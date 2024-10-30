<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
<link rel="stylesheet" type="text/css" href="css/shopping.css">
</head>
<body>
	<div id="wrap" align="center">
		<h1>게시글 리스트</h1>
		<table class="list">
			<tr>
				<td colspan="5" style="border: white; text-align: right"><a
					href="BoardServlet?command=board_write_form">게시글 등록</a></td>
			</tr>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>작성일</th>
				<th>조회</th>
			</tr>
			<c:forEach var="board" items="${boardList}">
				<tr class="record">
					<td>${board.num}</td>
					<td><a href="BoardServlet?command=board_view&num=${board.num}">
							${board.title} </a></td>
					<td>${board.name}</td>
					<td><fmt:formatDate value="${board.writedate}" /></td>
					<td>${board.readcount}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<div id="link_num">
		<%-- 		<c:forEach var="i" begin="1" end="${totalPages}"> --%>
		<%-- 			<a href="BoardServlet?command=board_list&page=${i}">${i}</a> --%>
		<%-- 		</c:forEach> --%>

		<c:forEach var="i" begin="1" end="${totalPages}">
			<c:choose>
				<c:when test="${i == currentPage}">
					<span style="font-weight: bold; color: blue;">${i}</span>
				</c:when>
				<c:otherwise>
					<a href="BoardServlet?command=board_list&page=${i}">${i}</a>
				</c:otherwise>
			</c:choose>
		</c:forEach>
	</div>
</body>
</html>