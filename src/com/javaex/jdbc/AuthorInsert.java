package com.javaex.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorInsert {

	public static void main(String[] args) {
		//insert into author (author_id, author_name, author_desc)
		//values (seq_author_id.nextval, '박리나', '서울시 양천구');
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
		    // 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

		    // 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");	//DriverManager - class 대문자로 시작함 (static일 가능성 있음)

		    // 3. SQL문 준비 / 바인딩 / 실행
			String query = "insert into author values (seq_author_id.nextval, ?, ?)";	//commit 까지 완료 된다
			pstmt = conn.prepareStatement(query);	//쿼리로 만들기
			
			pstmt.setString(1, "박리나");
			pstmt.setString(2, "서울시 양천구");
			
			pstmt.setString(1, "싱숑");
			pstmt.setString(2, "스타스트림");
			
			int count = pstmt.executeUpdate();      //insert, update, delete
			
			//ResultSet rs = pstmt.executeQuery();    //select
			
			
		    // 4.결과처리
			System.out.println(count + "건이 처리되었습니다");
			
			
		} catch (ClassNotFoundException e) {
		    System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
		    System.out.println("error:" + e);
		} finally {
		   
		    // 5. 자원정리
		    try {
		    	/*
		        if (rs != null) {
		            rs.close();		        
		        }              
		        */  
		        if (pstmt != null) {
		            pstmt.close();
		        }
		        if (conn != null) {
		            conn.close();
		        }
		    } catch (SQLException e) {
		        System.out.println("error:" + e);
		    }

		}
		
	}

}
