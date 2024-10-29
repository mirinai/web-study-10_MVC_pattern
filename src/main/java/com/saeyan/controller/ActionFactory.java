package com.saeyan.controller;

import com.saeyan.controller.action.Action;
import com.saeyan.controller.action.BoardListAction;
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
		System.out.println("ActionFactory :" + command);
		
		// command 값에 따라 action 인스턴스를 생성하여 요청을 처리
		if (command.equals("board_list")) { // 'board_list' 요청이 들어올 경우
			action = new BoardListAction(); // BoardListAction 객체 생성
		}
		
		else if(command.equals("board_write_form")) {
			action = new BoardWriteFormAction();
		}
		else if (command.equals("board_write")) {
			action = new BoardWriteFormAction();
		}
		
		return action; // 생성된 Action 객체를 반환
	}
}

