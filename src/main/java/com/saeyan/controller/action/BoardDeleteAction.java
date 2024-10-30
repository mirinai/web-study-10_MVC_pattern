package com.saeyan.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.saeyan.dao.BoardDAO;

public class BoardDeleteAction implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 요청 파라미터에서 게시글 번호(num)를 가져옴
        String num = request.getParameter("num");
        System.out.println("Deleting board with num: " + num); // 디버깅 출력 - 삭제할 게시글 번호 확인
        
        // BoardDAO 인스턴스를 가져옴 (싱글톤 패턴)
        BoardDAO bdao = BoardDAO.getInstance();
        
        // 해당 게시글을 삭제하는 메서드 호출
        bdao.deleteBoard(num);
        System.out.println("Board deleted."); // 디버깅 출력 - 삭제 완료 확인

        // 게시글 삭제 후 게시글 목록 페이지로 이동 (forward 방식)
        new BoardListAction().execute(request, response);

        // sendRedirect 방식으로 페이지를 이동하려면 주석을 해제
        // response.sendRedirect("BoardServlet?command=board_list");
    }
}
