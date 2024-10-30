<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글</title>
</head>
<body>
	<%
	// 클라이언트가 요청한 컨텍스트 경로를 얻어와 BoardServlet의 board_list로 리다이렉트
	response.sendRedirect(request.getContextPath() + "/BoardServlet?command=board_list");
	%>
</body>
</html>