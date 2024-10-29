package com.saeyan.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/* 
 * Action은 각 요청을 처리하는 개별 Action 클래스의 기본 구조를 정의하는 인터페이스입니다. 
 * 이 인터페이스를 구현하는 클래스를 통해 다양한 요청 처리 로직을 일관되게 관리할 수 있습니다.
 */
public interface Action {
	// HTTP 요청과 응답 객체를 매개변수로 받아 처리를 실행하는 메서드
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
/*
 * 	execute(HttpServletRequest request, HttpServletResponse response)는 모든 요청을 처리할 때 사용할 공통 메서드입니다.
	HttpServletRequest와 HttpServletResponse 객체를 매개변수로 받아 요청과 응답에 대한 정보를 처리할 수 있습니다.
	execute 메서드는 예외를 던질 수 있는 구조로 설계되어 있어, 서블릿 관련 예외(ServletException)와 입출력 예외(IOException)를 처리할 수 있습니다.
 */