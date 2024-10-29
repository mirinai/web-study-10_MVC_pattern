package com.saeyan.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.saeyan.dao.BoardDAO;
import com.saeyan.dto.BoardVO;

public class BoardWriteAction implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 사용자가 입력한 게시글 정보를 저장할 BoardVO 객체 생성
        BoardVO bvo = new BoardVO();
        
        // 요청에서 전달된 파라미터 값을 BoardVO 객체에 설정
        bvo.setName(request.getParameter("name")); // 작성자 이름
        bvo.setPass(request.getParameter("pass")); // 비밀번호
        bvo.setEmail(request.getParameter("email")); // 이메일
        bvo.setTitle(request.getParameter("title")); // 제목
        bvo.setContent(request.getParameter("content")); // 내용
        
        // BoardDAO의 싱글톤 인스턴스를 가져와 데이터베이스에 게시글 저장
        BoardDAO bdao = BoardDAO.getInstance();
        bdao.insertBoard(bvo); // 게시글을 데이터베이스에 삽입
        
        // 게시글 목록 페이지로 리다이렉트하기 위해 BoardListAction 실행
        new BoardListAction().execute(request, response);
    }
}

