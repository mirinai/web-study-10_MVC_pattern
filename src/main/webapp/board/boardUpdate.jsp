<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
<link rel="stylesheet" type="text/css" href="css/shopping.css">
<script type="text/javascript" src="script/board.js"></script>
</head>
<body>
	<div id="wrap" align="center">
		<h1> 게시글 수정 </h1>
		<form action="BoardServlet" method="post" name="frm">
			<input type="hidden" name="command" value="board_update">
			<input type="hidden" name="num" value="${board.num}">
			<table>
				<tr>
					<th>
						작성자
					</th>
					<td>
						<input type="text" size="12" name="name" value="${board.name}">
					</td>
				</tr>
				<tr>
					<th>
						비밀번호
					</th>
					<td>
						<input type="text" size="12" name="pass">
						* 필수 (게시물 수정 삭제시 필요함)
					</td>
				</tr>
				<tr>
					<th>
						이메일
					</th>
					<td>
						<input type="text" size="40" maxlength="50" name="email" value="${board.email}">
					</td>
				</tr>
				<tr>
					<th>제목</th>
					<td>
						<input type="text" size="70" name="title" value="${board.title}">
					</td>
				</tr>
				<tr>
					<td>
						<textarea cols="70" rows="15" name="content">${board.content}</textarea>
					</td>
				</tr>
			</table>
			<br><br>
			<input type="submit" value="등록" onclick="return boardCheck()">
			<input type="button" value="목록" onclick="location.href='BoardServlet?command=board_list'">
		</form>
	</div>
</body>
</html>