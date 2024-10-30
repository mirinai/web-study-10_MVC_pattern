package com.saeyan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.saeyan.dto.BoardVO;

import util.DBManager;

public class BoardDAO {
	// single tone pattern -------------------
	private BoardDAO() {
		
	}
	private static BoardDAO instance = new BoardDAO();
	
	public static BoardDAO getInstance() {
		return instance;
	}
	// single tone pattern -------------------
	
	public List<BoardVO> selectAllBoards(int page, int pageSize) {
	    // 모든 게시글을 가져오는 SQL 쿼리, 최신 순으로 정렬
	    String sql = "select * from board order by num desc limit ? offset ?";
	    
	    List<BoardVO> list = new ArrayList<BoardVO>(); // 결과를 저장할 리스트
	    
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    
	    try {
	        conn = DBManager.getConnection(); // DB 연결을 가져옴
	        pstmt = conn.prepareStatement(sql); // SQL 쿼리를 준비
	        
	        pstmt.setInt(1, pageSize);
	        pstmt.setInt(2, (page-1)*pageSize);
	        
	        rs = pstmt.executeQuery(); // 쿼리 실행 및 결과를 ResultSet에 저장
	        while(rs.next()) { // 결과 집합에서 다음 행을 읽음
	            BoardVO bvo = new BoardVO(); // 각 행을 담을 BoardVO 객체 생성
	            
	            bvo.setNum(rs.getInt("num")); // 게시글 번호 설정
	            bvo.setName(rs.getString("name")); // 작성자 이름 설정
	            bvo.setEmail(rs.getString("email")); // 이메일 설정
	            bvo.setPass(rs.getString("pass")); // 비밀번호 설정
	            bvo.setTitle(rs.getString("title")); // 제목 설정
	            bvo.setReadcount(rs.getInt("readcount")); // 조회수 설정
	            bvo.setWritedate(rs.getTimestamp("writedate")); // 작성일 설정
	            
	            list.add(bvo); // 리스트에 BoardVO 객체 추가
	        }
	    } catch (SQLException e) {
	        e.printStackTrace(); // SQL 예외 발생 시 스택 트레이스를 출력
	    } finally {
	        DBManager.close(conn, pstmt, rs); // 리소스 해제
	    }
	    return list; // 모든 게시글이 담긴 리스트 반환
	}
	
	public void insertBoard(BoardVO bvo) {
	    // BOARD 테이블에 데이터를 삽입하는 SQL 쿼리
	    String sql = "INSERT INTO BOARD("
	                + "NAME, EMAIL, PASS, TITLE, CONTENT)"
	                + " VALUES(?, ?, ?, ?, ?)";
	    
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    
	    try {
	        conn = DBManager.getConnection(); // DB 연결을 가져옴
	        pstmt = conn.prepareStatement(sql); // SQL 쿼리를 준비
	        
	        // BoardVO 객체의 각 필드 값을 SQL 쿼리의 파라미터로 설정
	        pstmt.setString(1, bvo.getName()); // 첫 번째 파라미터 - 이름
	        pstmt.setString(2, bvo.getEmail()); // 두 번째 파라미터 - 이메일
	        pstmt.setString(3, bvo.getPass()); // 세 번째 파라미터 - 비밀번호
	        pstmt.setString(4, bvo.getTitle()); // 네 번째 파라미터 - 제목
	        pstmt.setString(5, bvo.getContent()); // 다섯 번째 파라미터 - 내용
	        
	        pstmt.executeUpdate(); // SQL 쿼리를 실행하여 데이터 삽입
	    } catch (Exception e) {
	        e.printStackTrace(); // 예외 발생 시 스택 트레이스를 출력
	    } finally {
	        DBManager.close(conn, pstmt); // 리소스 해제
	    }
	}

	
	public void updateReadCount(String num) {
	    // 게시글의 조회수를 1 증가시키는 SQL 쿼리
	    String sql = "UPDATE BOARD SET READCOUNT=READCOUNT+1 WHERE NUM=?";
	    
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    
	    try {
	        conn = DBManager.getConnection(); // DB 연결을 가져옴
	        
	        pstmt = conn.prepareStatement(sql); // SQL 쿼리를 준비
	        
	        pstmt.setString(1, num); // 게시글 번호를 SQL 쿼리의 파라미터로 설정
	        
	        pstmt.executeUpdate(); // 쿼리 실행, 조회수 증가
	        
	    } catch (Exception e) {
	        e.printStackTrace(); // 예외 발생 시 스택 트레이스를 출력
	    } finally {
	        DBManager.close(conn, pstmt); // 리소스 해제
	    }
	}

	// 게시판 글 상세 내용 보기 : 글번호로 찾아온다. : 실패 시 null 반환
	public BoardVO selectOneBoardByNum(String num) {
	    String sql = "SELECT * FROM BOARD WHERE NUM=?";
	    
	    BoardVO bvo = null; // 결과를 저장할 BoardVO 객체, 초기값은 null
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    
	    try {
	        conn = DBManager.getConnection(); // DB 연결을 가져옴
	        
	        pstmt = conn.prepareStatement(sql); // SQL 쿼리를 준비
	        pstmt.setString(1, num); // 글 번호 파라미터 설정
	        
	        rs = pstmt.executeQuery(); // 쿼리 실행 및 결과를 ResultSet에 저장
	        
	        // ResultSet에 데이터가 있으면, 해당 데이터를 BoardVO 객체에 매핑
	        while(rs.next()) { // 거짓이면 bvo는 null로 유지됨
	            bvo = new BoardVO(); // 각 행의 데이터를 담을 BoardVO 객체 생성
	            
	            bvo.setNum(rs.getInt("num")); // 글 번호 설정
	            bvo.setName(rs.getString("name")); // 작성자 이름 설정
	            bvo.setPass(rs.getString("pass")); // 비밀번호 설정
	            bvo.setEmail(rs.getString("email")); // 이메일 설정
	            bvo.setTitle(rs.getString("title")); // 제목 설정
	            bvo.setContent(rs.getString("content")); // 내용 설정 - 누락된 부분 추가
	            bvo.setWritedate(rs.getTimestamp("writedate")); // 작성일 설정
	            bvo.setReadcount(rs.getInt("readcount")); // 조회수 설정
	        }
	    } catch (Exception e) {
	        e.printStackTrace(); // 예외 발생 시 스택 트레이스를 출력 bvo => null로 유지됨
	    } finally {
	        DBManager.close(conn, pstmt, rs); // 리소스 해제
	    }
	    
	    return bvo; // 게시글 데이터가 담긴 BoardVO 객체 반환, 실패 시 null 반환
	}

	public void updateBoard(BoardVO bvo) {
	    // 게시글 정보를 업데이트하는 SQL 쿼리
	    String sql = "UPDATE BOARD SET NAME=?, EMAIL=?, PASS=?, "
	                + "TITLE=?, CONTENT=? WHERE NUM=?";
	    
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    
	    try {
	        conn = DBManager.getConnection(); // DB 연결을 가져옴
	        
	        pstmt = conn.prepareStatement(sql); // SQL 쿼리를 준비
	        
	        // BoardVO 객체의 각 필드 값을 SQL 쿼리의 파라미터로 설정
	        pstmt.setString(1, bvo.getName()); // 첫 번째 파라미터 - 이름
	        pstmt.setString(2, bvo.getEmail()); // 두 번째 파라미터 - 이메일
	        pstmt.setString(3, bvo.getPass()); // 세 번째 파라미터 - 비밀번호
	        pstmt.setString(4, bvo.getTitle()); // 네 번째 파라미터 - 제목
	        pstmt.setString(5, bvo.getContent()); // 다섯 번째 파라미터 - 내용
	        pstmt.setInt(6, bvo.getNum()); // 여섯 번째 파라미터 - 게시글 번호 (WHERE 조건)
	        
	        pstmt.executeUpdate(); // 쿼리 실행, 게시글 정보 업데이트
	        
	    } catch (Exception e) {
	        e.printStackTrace(); // 예외 발생 시 스택 트레이스를 출력
	    } finally {
	        DBManager.close(conn, pstmt); // 리소스 해제
	    }
	}

	
	public BoardVO checkPassWord(String pass, String num) {
	    // 특정 글 번호와 비밀번호로 게시글을 조회하는 SQL 쿼리
	    String sql = "SELECT * FROM BOARD WHERE PASS=? AND NUM=?";
	    
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    BoardVO bvo = null; // 결과를 저장할 BoardVO 객체, 초기값은 null
	    
	    try {
	        conn = DBManager.getConnection(); // DB 연결을 가져옴
	        pstmt = conn.prepareStatement(sql); // SQL 쿼리를 준비
	        
	        pstmt.setString(1, pass); // 첫 번째 파라미터 - 비밀번호
	        pstmt.setString(2, num); // 두 번째 파라미터 - 글 번호
	        
	        rs = pstmt.executeQuery(); // 쿼리 실행 및 결과를 ResultSet에 저장
	        
	        // 조회 결과가 존재하면 BoardVO 객체에 각 필드를 설정
	        if (rs.next()) {
	            bvo = new BoardVO(); // 게시글 정보를 담을 BoardVO 객체 생성
	            
	            bvo.setNum(rs.getInt("num")); // 글 번호 설정
	            bvo.setName(rs.getString("name")); // 작성자 이름 설정
	            bvo.setEmail(rs.getString("email")); // 이메일 설정
	            bvo.setPass(rs.getString("pass")); // 비밀번호 설정
	            bvo.setTitle(rs.getString("title")); // 제목 설정
	            bvo.setContent(rs.getString("content")); // 내용 설정
	            bvo.setReadcount(rs.getInt("readcount")); // 조회수 설정
	            bvo.setWritedate(rs.getTimestamp("writedate")); // 작성일 설정
	        }
	        
	    } catch (Exception e) {
	        e.printStackTrace(); // 예외 발생 시 스택 트레이스를 출력
	    } finally {
	        DBManager.close(conn, pstmt, rs); // 리소스 해제
	    }
	    
	    return bvo; // 조회된 게시글 정보가 담긴 BoardVO 객체 반환, 실패 시 null 반환
	}
	
	public void deleteBoard(String num) {
	    // 특정 글 번호로 게시글을 삭제하는 SQL 쿼리
	    String sql = "DELETE FROM BOARD WHERE NUM=?";
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    
	    try {
	        conn = DBManager.getConnection(); // DB 연결을 가져옴
	        pstmt = conn.prepareStatement(sql); // SQL 쿼리를 준비
	        
	        pstmt.setString(1, num); // 첫 번째 파라미터 - 글 번호 설정
	        
	        pstmt.executeUpdate(); // 쿼리 실행, 게시글 삭제
	        
	    } catch (Exception e) {
	        e.printStackTrace(); // 예외 발생 시 스택 트레이스를 출력
	    } finally {
	        DBManager.close(conn, pstmt); // 리소스 해제
	    }
	}
	
	public int getTotalBoardCount() {
		String sql = "select count(*) from board";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				return rs.getInt(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt, rs);
		}
		return 0;
	}
	
}
