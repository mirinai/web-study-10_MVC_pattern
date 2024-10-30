package com.saeyan.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.saeyan.dao.BoardDAO;
import com.saeyan.dto.BoardVO;

public class BoardUpdateAction implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // BoardVO 객체를 생성하여 수정할 게시글 정보를 저장
        BoardVO bvo = new BoardVO();
        
        // 요청 파라미터에서 게시글 수정 정보를 가져옴
        String num = request.getParameter("num");
        String name = request.getParameter("name");
        String pass = request.getParameter("pass");
        String email = request.getParameter("email");
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        
        // BoardVO 객체에 게시글 수정 정보를 설정
        bvo.setNum(Integer.parseInt(num));
        
        System.out.println("Updating board with bvo.getNum(): " + bvo.getNum());
        
        bvo.setName(name);
        bvo.setPass(pass);
        bvo.setEmail(email);
        bvo.setTitle(title);
        bvo.setContent(content);
        
        // BoardDAO의 인스턴스를 가져와 데이터베이스에 게시글 수정 요청
        BoardDAO bdao = BoardDAO.getInstance();
        bdao.updateBoard(bvo); // 게시글 정보를 업데이트
        System.out.println("board updated");
        // 게시글 수정 후 게시글 목록 페이지로 리다이렉트
        new BoardListAction().execute(request, response);
    }
}

