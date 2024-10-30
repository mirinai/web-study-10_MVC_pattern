package com.saeyan.controller;

import com.saeyan.controller.action.Action;
import com.saeyan.controller.action.BoardCheckPassAction;
import com.saeyan.controller.action.BoardCheckPassFormAction;
import com.saeyan.controller.action.BoardDeleteAction;
import com.saeyan.controller.action.BoardListAction;
import com.saeyan.controller.action.BoardUpdateAction;
import com.saeyan.controller.action.BoardUpdateFormAction;
import com.saeyan.controller.action.BoardViewAction;
import com.saeyan.controller.action.BoardWriteAction;
import com.saeyan.controller.action.BoardWriteFormAction;

public class ActionFactory {
	
	// --------------------------------------
	private static ActionFactory instance = new ActionFactory(); // 유일한 인스턴스를 생성 (싱글톤 패턴)
	
	public ActionFactory() {
		super(); // 상위 클래스 생성자 호출
	}
	
	// 인스턴스를 반환하는 메서드 (싱글톤 패턴)
	public static ActionFactory getInstance() {
		return instance;
	}
	// -------------------------------------single tone pattern
	
	// command 값에 따라 적절한 Action 객체를 반환하는 메서드
	public Action getAction(String command) {
		Action action = null;
		System.out.println("ActionFactory :" + command); // 전달받은 command 값 확인을 위한 출력
		
		// command 값에 따라 action 인스턴스를 생성하여 요청을 처리
		if (command.equals("board_list")) { // 'board_list' 요청이 들어올 경우
			action = new BoardListAction(); // BoardListAction 객체 생성 => BoardListAction에서 forwarding으로 그 페이지에 request, response 보냄
		}
		// command가 'board_write_form'일 경우 게시글 작성 화면으로 이동
		else if(command.equals("board_write_form")) {
			action = new BoardWriteFormAction();
		}
		// command가 'board_write'일 경우 게시글 작성 작업을 수행
		else if (command.equals("board_write")) {
			action = new BoardWriteAction();
		}
		// command가 'board_view'일 경우 특정 게시글 조회 작업 수행
		else if(command.equals("board_view")) {
			action = new BoardViewAction();
		}
		// command가 'board_check_pass_form'일 경우 비밀번호 확인 화면으로 이동
		else if(command.equals("board_check_pass_form")) {
			action = new BoardCheckPassFormAction();
		}
		// command가 'board_check_pass'일 경우 비밀번호를 확인하는 작업 수행
		else if(command.equals("board_check_pass")) {
			action = new BoardCheckPassAction();
		}
		// command가 'board_update_form'일 경우 게시글 수정 화면으로 이동
		else if(command.equals("board_update_form")) {
			action = new BoardUpdateFormAction();
		}
		// command가 'board_update'일 경우 게시글 수정 작업을 수행
		else if(command.equals("board_update")) {
			action = new BoardUpdateAction();
		}
		// command가 'board_delete'일 경우 게시글 삭제 작업을 수행
		else if(command.equals("board_delete")) {
			action = new BoardDeleteAction();
		}
		return action; // 생성된 Action 객체를 반환
	}
}
