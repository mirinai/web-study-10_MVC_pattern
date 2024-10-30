package com.saeyan.controller.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.saeyan.dao.BoardDAO;
import com.saeyan.dto.BoardVO;

public class BoardViewAction implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 게시글 상세보기 페이지로 이동할 URL 설정
        String url = "/board/boardView.jsp";
        
        // 요청 파라미터에서 게시글 번호(num) 값을 가져옴
        String num = request.getParameter("num");
        
        // BoardDAO의 싱글톤 인스턴스를 가져옴
        BoardDAO bdao = BoardDAO.getInstance();
        
        // 조회수 증가 메서드 호출 - 해당 게시글의 조회수를 1 증가시킴
        bdao.updateReadCount(num);
        
        // 게시글 번호(num)로 게시글 정보를 조회하여 BoardVO 객체에 저장
        BoardVO bvo = bdao.selectOneBoardByNum(num);
        
        // 조회한 게시글 정보를 request 객체에 저장하여 JSP에 전달
        request.setAttribute("board", bvo);
        
        // RequestDispatcher를 사용하여 boardView.jsp 페이지로 포워딩
        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }
}

