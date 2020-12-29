package com.javaex.Book01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorDao {
	// 필드
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String id = "webdb";
	private String pw = "webdb";

	// 생성자

	// 메소드 g/s
	// 메소드 일반
	// 작가 리스트 저장
	public int authorInsert(AuthorVo authorVo) {
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;

		int count = 0;

		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName(driver);

			// 2. Connection 얻어오기
			conn = DriverManager.getConnection(url, id, pw);

			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += "insert into author \n";
			query += "values (seq_author_id.nextval, ?, ?)";

			System.out.println(query);
			// 쿼리문 만들기
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, authorVo.getAuthorName());
			pstmt.setString(2, authorVo.getAuthorDesc());

			count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println("[authorDao] " + count + "건 저장");

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {

			// 5. 자원정리
			try {
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

		return count;
	}

	// 작가 리스트 수정
	public int authorUpdate(AuthorVo authorVo) {
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;

		int count = 0;

		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName(driver);

			// 2. Connection 얻어오기
			conn = DriverManager.getConnection(url, id, pw);

			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += "update author \n";
			query += "set author_name = ? \n";
			query += "	author_desc = ? \n";
			query += "where author_id = ?";

			// 쿼리문 확인
			System.out.println(query);

			// 쿼리문 만들기
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, authorVo.getAuthorName());
			pstmt.setString(2, authorVo.getAuthorDesc());
			pstmt.setInt(3, authorVo.getAuthorId());

			count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println("[authorDao] " + count + "건 수정");

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {

			// 5. 자원정리
			try {

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

		return count;

	}

	// 데이터 삭제
	public int authorDelete(int authorId) {

		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;

		int count = 0;

		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName(driver);

			// 2. Connection 얻어오기
			conn = DriverManager.getConnection(url, id, pw);

			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += "delete from author \n";
			query += "where author_id = ?";
			
			//쿼리문 test
			System.out.println(query);
			
			//쿼리문 만들기
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, authorId);

			count = pstmt.executeUpdate();
			// 4.결과처리
			System.out.println("[authorDao] " + count + "건 삭제");
			
			
		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {

			// 5. 자원정리
			try {
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
		return count;
	}
	
	// 데이터 가져오기
	public List<AuthorVo> getAuthorList(){
		List<AuthorVo> authorList = new ArrayList<AuthorVo>();
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
		    // 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName(driver);

		    // 2. Connection 얻어오기
			conn = DriverManager.getConnection(url, id, pw);

		    // 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += "select author_id, \n";
			query += "	author_name, \n";
			query += "	author_desc \n";
			query += "from author";
			
			//쿼리문 확인
			System.out.println(query);
			
			//쿼리문 만들기
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
		    
		    // 4.결과처리
			// rs에 있는 데이터를 List<AuthorVo>로 구성해야 함
			// List의 값을 담아줌
			while(rs.next()) {
				int authorId = rs.getInt("author_id");
				String authorName = rs.getString("author_name");
				String authorDesc = rs.getString("author_desc");
				
				AuthorVo vo = new AuthorVo(authorId, authorName, authorDesc);
				authorList.add(vo);
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
		return authorList;
	}
}
