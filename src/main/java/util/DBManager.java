package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
// 데이터베이스 연결을 관리하는 유틸리티 클래스
public class DBManager {
    
    // 데이터베이스 연결을 위한 메서드
    public static Connection getConnection() {
        Connection conn = null;
        
        try {
            // 초기 JNDI 컨텍스트를 가져옵니다.
            Context initContext = new InitialContext();
            
            // 환경 컨텍스트를 검색하여 설정된 리소스 접근
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            
            // jdbc/TestDB라는 이름의 객체를 찾아서 DataSource가 받는다
            DataSource ds = (DataSource) envContext.lookup("jdbc/TestDB");
            
            // ds가 생성되었으므로 Connection을 구한다
            conn = ds.getConnection();
            
        } catch (Exception e) {
            e.printStackTrace(); // 예외 발생 시 스택 트레이스를 출력
        }
        return conn; // 연결 객체 반환
    }
    
    // select를 수행한 뒤 리소스를 해제하는 메서드
    public static void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {
        try {
            rs.close(); // ResultSet 리소스 해제
            pstmt.close(); // PreparedStatement 리소스 해제
            conn.close(); // Connection 리소스 해제
        } catch (Exception e) {
            e.printStackTrace(); // 예외 발생 시 스택 트레이스를 출력
        }
    }
    
    // DML(INSERT, UPDATE, DELETE)를 수행 한 뒤 리소스 해제를 하는 메서드
    public static void close(Connection conn, PreparedStatement pstmt) {
        try {
            pstmt.close(); // PreparedStatement 리소스 해제
            conn.close(); // Connection 리소스 해제
        } catch (Exception e) {
            e.printStackTrace(); // 예외 발생 시 스택 트레이스를 출력
        }
    }
}
