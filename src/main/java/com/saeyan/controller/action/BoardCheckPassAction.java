package com.saeyan.controller.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.saeyan.dao.BoardDAO;
import com.saeyan.dto.BoardVO;
// 게시글의 비밀번호를 확인하기 위한 액션 클래스
public class BoardCheckPassAction implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 포워딩할 URL을 저장할 변수
        String url = null;
        
        // 요청 파라미터에서 게시글 번호(num)와 입력된 비밀번호(pass) 값을 가져옴
        String num = request.getParameter("num");
        String pass = request.getParameter("pass");
        
        // BoardDAO의 싱글톤 인스턴스를 가져옴
        BoardDAO bdao = BoardDAO.getInstance();
        
        // 게시글 번호(num)로 게시글 정보를 조회하여 BoardVO 객체에 저장
        BoardVO bvo = bdao.selectOneBoardByNum(num);
        
        // 게시글의 비밀번호와 입력된 비밀번호가 일치하는지 확인
        if (bvo.getPass().equals(pass)) {
            // 비밀번호가 일치하면 성공 페이지로 이동
            url = "/board/checkSuccess.jsp";
        } else {
            // 비밀번호가 틀리면 다시 비밀번호 확인 페이지로 이동하고, 오류 메시지를 설정
            url = "/board/boardCheckPass.jsp";
            request.setAttribute("message", "비밀번호가 틀림");
            
            // RequestDispatcher를 사용하여 boardCheckPass.jsp 페이지로 포워딩
           
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }
}

