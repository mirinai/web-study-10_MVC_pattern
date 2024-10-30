package com.saeyan.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.saeyan.controller.action.Action;

/**
 * Servlet implementation class BoardServlet
 */
// BoardServlet은 command 파라미터를 통해 어떤 작업(Action)을 수행할지 결정한 후, 해당 Action을 실행해 클라이언트 요청을 처리하는 구조
@WebServlet("/BoardServlet")
public class BoardServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
       
    /**
     * 기본 생성자 - HttpServlet 상속
     */
    public BoardServlet() {
        super();
        // 기본 생성자
    }

    /**
     * GET 요청 처리 메서드
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 클라이언트 요청에서 command 파라미터 값을 추출
    	// /BoardServlet?command=값
        String command = request.getParameter("command");
        // command의 값을 얻어 옴 
        System.out.println("BoardServlet에서 요청을 받음을 확인 : " + command);
        // BoardServlet에서 요청을 받음을 확인 : 값
        
        // ActionFactory의 싱글톤 인스턴스를 가져와 command에 따른 Action 객체 생성
        ActionFactory af = ActionFactory.getInstance();
        Action action = af.getAction(command); // ==  new Board@@@Action()
        
        // action이 null이 아니면 execute 메서드를 호출하여 요청을 처리
        if (action != null) {
            action.execute(request, response);
        }
    }
    
    /**
     * POST 요청 처리 메서드
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // POST 요청에서 인코딩 설정 (한글 처리)
        request.setCharacterEncoding("UTF-8");
        
        // POST 요청을 doGet 방식으로 처리
        doGet(request, response);
    }

}
