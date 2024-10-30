package com.saeyan.controller.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.saeyan.dao.BoardDAO;
import com.saeyan.dto.BoardVO;

public class BoardListAction implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 게시판 목록을 보여줄 JSP 페이지 URL
        String url = "/board/boardList.jsp";
        
        // BoardDAO의 싱글톤 인스턴스를 가져와 데이터 조회
        BoardDAO bdao = BoardDAO.getInstance();
        
        // 현재 페이지에 해당하는 게시글 목록을 가져옴
        
        int page = 1; // 기본값을 1로 설정
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }
        
        int pageSize = 8;
        
        // 모든 게시글을 가져오는 메서드를 호출하여 리스트에 저장
        List<BoardVO> boardList = bdao.selectAllBoards(page,pageSize);
        
        // 조회한 게시글 리스트를 request 객체에 저장하여 JSP에 전달
        request.setAttribute("boardList", boardList);
        
        // 총 게시글 수를 가져와 총 페이지 수를 계산
        int totalRecords = bdao.getTotalBoardCount();
        int totalPages = totalRecords == 0 ? 1 : (int) Math.ceil(totalRecords / (double) pageSize);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("totalPages", totalPages); // 총 페이지 수를 JSP에 전달
        
        request.setAttribute("currentPage", page);// 현재 페이지 번호도 전달
        
        // RequestDispatcher를 사용하여 boardList.jsp 페이지로 포워딩
        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }
}
