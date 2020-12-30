package com.javaex.Book02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDao {

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
	// 메소드 g/s
	// 메소드 일반
	// DB 정리
	public void getconnection() {
		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName(driver);

			// 2. Connection 얻어오기
			conn = DriverManager.getConnection(url, id, pw);

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
	}

	// 자원 정리
	public void close() {
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

	// 책 정보 저장
	public int bookInsert(BookVo bookVo) {

		int count = 0;
		
		getconnection();

		try {
			
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += "insert into book \n";
			query += "values (SEQ_book_ID.nextval, ?, ?, ?, ?)";

			// 쿼리문 테스트
			System.out.println(query);

			// 쿼리문 만들기
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, bookVo.getTitle());
			pstmt.setString(2, bookVo.getPubs());
			pstmt.setString(3, bookVo.getPubDate());
			pstmt.setInt(4, bookVo.getAuthorId());

			count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println("[dao] " + count + "건 저장");

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		
		close();

		return count;
	}

	// 데이터 수정
	public int bookUpdate(BookVo bookVo) {

		int count = 0;

		getconnection();
		
		try {
			// 3. SQL문 준비 / 바인딩 / 실행

			String query = "";
			query += "update book \n";
			query += "set title = ? \n";
			query += "	pubs = ?";
			query += "	pub_date = ?";
			query += "	author_id = ?";
			query += "where book_id = ?";

			// 쿼리문 확인하기
			System.out.println(query);

			// 쿼리문 만들기
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, bookVo.getTitle());
			pstmt.setString(2, bookVo.getPubs());
			pstmt.setString(3, bookVo.getPubDate());
			pstmt.setInt(4, bookVo.getAuthorId());
			pstmt.setInt(5, bookVo.getBookId());

			count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println("[dao] " + count + "건 수정");

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
	public int bookDelete(int bookId) {

		int count = 0;
		
		getconnection();

		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += "delete from book";
			query += "where book_id = ?";

			// 쿼리문 test
			System.out.println(query);
			// 쿼리문 만들기
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bookId);

			count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println("[dao]" + count + "건 삭제");

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		
		close();

		return count;
	}

	// 데이터 가져오기 **책**
	public List<BookVo> getBookList() {
		List<BookVo> bookList = new ArrayList<BookVo>();
		
		getconnection();

		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += "select book_id, \n";
			query += "	title, \n";
			query += "	pubs, \n";
			query += "	to_char(pub_date, 'YYYY-MM-DD') pub_date, \n";
			query += "	author_id \n";
			query += " from book";

			System.out.println(query);

			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();

			// 4.결과처리
			while (rs.next()) {
				int bookId = rs.getInt("book_id");
				String title = rs.getString("title");
				String pubs = rs.getString("pubs");
				String pubDate = rs.getString("pub_date");
				int authorId = rs.getInt("author_id");

				BookVo vo = new BookVo(bookId, title, pubs, pubDate, authorId);
				bookList.add(vo);
			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		
		close();

		return bookList;
	}

	// 데이터 가져오기 **전체**
	public List<BookVo> getallList() {
		List<BookVo> allList = new ArrayList<BookVo>();

		getconnection();
		
		try {
			
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += "select b.book_id, \n";
			query += "		 b.title, \n";
			query += "		 b.pubs, \n";
			query += "		 b.pub_date, \n";
			query += "		 a.author_id, \n";
			query += "		 a.author_name, \n";
			query += "		 a.author_desc \n";
			query += " from book b, author a";
			query += " where b.author_id = a.author_id";

			// 쿼리 확인
			System.out.println(query);
			// 쿼리문 만들기
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();

			// 4.결과처리
			while (rs.next()) {
				int bookId = rs.getInt("book_id");
				String title = rs.getString("title");
				String pubs = rs.getString("pubs");
				String pubDate = rs.getString("pub_date");
				int authorId = rs.getInt("author_id");
				String authorName = rs.getString("author_name");
				String authorDesc = rs.getString("author_desc");

				BookVo allvo = new BookVo(bookId, title, pubs, pubDate, authorId, authorName, authorDesc);
				allList.add(allvo);
			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		
		close();

		return allList;
	}

	// 데이터 검색
	public List<BookVo> getsearch(String str) {
		List<BookVo> searchList = new ArrayList<BookVo>();
		
		
		getconnection();
		
		try {

		    // 3. SQL문 준비 / 바인딩 / 실행

			
			String query = "";
			query += "select 	b.book_id, ";
			query += "	 b.title, ";
			query += "	 b.pubs, ";
			query += "	 to_char(b.pub_date, 'YYYY-MM-DD') pub_date, ";
			query += "	 a.author_id, ";
			query += "	 a.author_name, ";
			query += "	 a.author_desc ";
			query += " from book b, author a ";
			query += " where b.author_id = a.author_id ";
			query += " and (b.title like ? ";
			query += " or b.pubs like ? ";
			query += " or a.author_name like ?) ";
			
			System.out.println(query);
			
			//쿼리문 만들기
			pstmt = conn.prepareStatement(query);
			
			str = "%" + str + "%";
			
			System.out.println(str);
			pstmt.setString(1, str);
			pstmt.setString(2, str);
			pstmt.setString(3, str);
			
			rs = pstmt.executeQuery();
		    
		    // 4.결과처리
			while (rs.next()) {
				int bookId = rs.getInt("book_id");
				String title = rs.getString("title");
				String pubs = rs.getString("pubs");
				String pubDate = rs.getString("pub_date");
				String authorName = rs.getString("author_name");

				BookVo bookVo = new BookVo(bookId, title, pubs, pubDate, authorName);
				searchList.add(bookVo);
			}
		} catch (SQLException e) {
		    System.out.println("error:" + e);
		}
		
		close();
		
		return searchList;
	
	}

}
