package com.javaex.Author02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorDao {

	// 필드
	// 0. import java.sql.*;
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String id = "webdb";
	private String pw = "webdb";
	// 생성자
	// 디폴트 생성자 사용 시 생략 가능

	// 메소드 일반
	// 공통인 부분을 묶기
	// 상황 = 공통인 부분을 접속 메소드로 만들어서 필드에 놓고 어디서든 쓰게 만들고 싶다

	private void getConnection() {
		// DB 접속 기능
		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName(driver);

			// 2. Connection 얻어오기
			conn = DriverManager.getConnection(url, id, pw);
			
			System.out.println("[접속 성공]");

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		
	}

	// 자원 정리
	private void close() {
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
	// 작가 리스트 저장하기
		public int authorInsert(AuthorVo authorVo) {
			//DB 접속
			getConnection();
			
			int count = 0;
			try {
				
				// 3. SQL문 준비 / 바인딩 / 실행
				String query = "";
				query += "insert into author";
				query += " values (seq_author_id.nextval, ?, ?)";

				System.out.println(query);
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, authorVo.getAuthorName());
				pstmt.setString(2, authorVo.getAuthorDesc());

				count = pstmt.executeUpdate();

				// 4.결과처리
				System.out.println("[dao] " + count + "건 저장");

			}  catch (SQLException e) {
				System.out.println("error:" + e);
			} 
			
			//자원 정리
			close();

			return 1;
		}

	// 작가 수정
	public int authorUpdate(AuthorVo authorVo) {
		//DB 접속
		getConnection();

		int count = 0;

		try {
			
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += "update author";
			query += " set author_name = ?, ";
			query += "		author_desc = ?";
			query += " where author_id = ?";

			System.out.println(query);

			pstmt = conn.prepareStatement(query); // 쿼리문으로 만들기
			pstmt.setString(1, authorVo.getAuthorName());
			pstmt.setString(2, authorVo.getAuthorDesc());
			pstmt.setInt(3, authorVo.getAuthorId());

			count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println("[dao] " + count + "건 수정");

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} 
		
		
		//자원 정리
		close();


		
		return count;
	}

	// 작가 삭제 하기
	public int authorDelete(int authorId) {
		int count = 0;

		// DB 접속
		getConnection();

		try {
			
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += "delete from author";
			query += " where author_id = ?";

			// 쿼리문 test
			System.out.println(query);

			pstmt = conn.prepareStatement(query); // 쿼리문 만들기
			pstmt.setInt(1, authorId);

			count = pstmt.executeUpdate(); // insert, update, delete

			// 4.결과처리
			System.out.println("[dao]" + count + "건 삭제");

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} 
		
		//자원 정리
		close();


		return count;
	}

	// 작가 리스트 가져오기
	public List<AuthorVo> getAuthorList() {
		List<AuthorVo> authorList = new ArrayList<AuthorVo>();

		// DB 접속
		getConnection();


		try {
			
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += "select  author_id,";
			query += "		  author_name,";
			query += " 		  author_desc";
			query += " from author";

			System.out.println(query);

			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();

			// 4.결과처리
			while (rs.next()) {
				int authorId = rs.getInt("author_id");
				String authorName = rs.getString("author_name");
				String authorDesc = rs.getString("author_desc");

				AuthorVo vo = new AuthorVo(authorId, authorName, authorDesc);
				authorList.add(vo);
			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} 
		
		//자원 정리
		close();

		return authorList;
	}

	
}
