<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- 게시글의 비밀번호가 서로 같은 값을 가질 때 처리를 위한 jsp페이지 -->
</head>
<body>
    <script type="text/javascript">
        // 현재 창의 이름이 'update'일 경우 게시글 수정 페이지로 이동
        if(window.name == 'update'){
            // 부모 창의 URL을 게시글 수정 페이지로 변경 (게시글 번호 num 포함)
            window.opener.parent.location.href="BoardServlet?command=board_update_form&num=${param.num}";
        }
        // 현재 창의 이름이 'delete'일 경우 게시글 삭제 페이지로 이동
        else if(window.name == 'delete'){
            alert('삭제됨'); // 삭제 알림을 표시
            // 부모 창의 URL을 게시글 삭제 페이지로 변경 (게시글 번호 num 포함)
            window.opener.parent.location.href="BoardServlet?command=board_delete&num=${param.num}";
        }
        // 작업 완료 후 현재 창을 닫음
        window.close();
    </script>
</body>
</html>
