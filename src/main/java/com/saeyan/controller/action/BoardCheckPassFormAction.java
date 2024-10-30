package com.saeyan.controller.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//비밀번호 입력화면으로 이동하게 하는 액션클래스
public class BoardCheckPassFormAction implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 비밀번호 확인 화면으로 이동할 JSP 페이지 URL 설정
        String url = "/board/boardCheckPass.jsp";
        
        // RequestDispatcher를 사용하여 boardCheckPass 페이지로 포워딩
        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }
}
