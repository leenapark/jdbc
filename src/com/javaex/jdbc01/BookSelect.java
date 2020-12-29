package com.javaex.jdbc01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookSelect {

	public static void main(String[] args) {
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			 // 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");	
			
		    // 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

		    // 3. SQL문 준비 / 바인딩 / 실행
			/* Oracle test
			select  book_id id,
	        		title title,
	        		pubs pubs,
	        		pub_date pub_date
	        from book;
		    */
		    
			//쿼리문 만들기
			String query = "";
			query += "select book_id, \n";
			query += "title, \n";
			query += "pubs, \n";
			query += "to_char(pub_date, 'YYYY-MM-DD') pub_date, \n";
			query += "author_id \n";
			query += "from book";

			//System.out.println(query);
			
			pstmt = conn.prepareStatement(query);

			rs = pstmt.executeQuery();
			
			// 4.결과처리
			while(rs.next()) {
				int id = rs.getInt("book_id");
				String title = rs.getString("title");
				String pubs = rs.getString("pubs");
				String pubDate = rs.getString("pub_date");
				int authorId = rs.getInt("author_id");

				
				System.out.println(id + "\t" + title + "\t" + pubs + "\t" + pubDate + "\t" + authorId);
			}
			
		} catch (ClassNotFoundException e) {
		    System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
		    System.out.println("error:" + e);
		} finally {
		   
		    // 5. 자원정리
		    try {
		        if (rs != null) {
		            rs.close();
		        }                
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
