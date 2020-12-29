package com.javaex.jdbc01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookSelectAll {

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
			/* oracle test
			select  b.book_id,
	        		b.title,
	        		b.pubs,
	        		to_char(b.pub_date, 'YYYY-MM-DD'),
	        		b.author_id,
	        		a.author_name,
	        		a.author_desc
	        from book b, author a
	        where b.author_id = a.author_id;
			*/
			//쿼리문 만들기
			String query = "";
			query += "select  b.book_id, \n";
			query += "	b.title, \n";
			query += "	b.pubs, \n";
			query += "	to_char(b.pub_date, 'YYYY-MM-DD') pub_date, \n";
			query += "	a.author_id, \n";
			query += "	a.author_name, \n";
			query += "	a.author_desc \n";
			query += " from book b, author a \n";
			query += " where b.author_id = a.author_id";
			
			//쿼리문 test
			System.out.println(query);
			
			pstmt = conn.prepareStatement(query); // 쿼리로 만들기
			
			rs = pstmt.executeQuery();
			
		    // 4.결과처리
			while(rs.next()) {
				int id = rs.getInt("book_id");
				String title = rs.getString("title");
				String pubs = rs.getString("pubs");
				String pubDate = rs.getString("pub_date");
				int authorId = rs.getInt("author_id");
				String authorName = rs.getString("author_name");
				String authorDesc = rs.getString("author_desc");
				
				System.out.println(id + "\t" + title + "\t" + pubs + "\t" + pubDate + "\t" + authorId + "\t" + authorName + "\t" + authorDesc);
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
