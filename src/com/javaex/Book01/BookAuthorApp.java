package com.javaex.Book01;

import java.util.List;

import com.javaex.Author01.AuthorDao;
import com.javaex.Author01.AuthorVo;

public class BookAuthorApp {

	public static void main(String[] args) {
		AuthorDao authorDao = new AuthorDao();
		List<AuthorVo> authorList = authorDao.getAuthorList();

		BookDao bookDao = new BookDao();		
		//List<BookVo> bookList = 
		
		//작가 등록 6명
		//AuthorDao, AuthorVo 이용해서 등록
		//AuthorDao authorDao = new AuthorDao();
		
		
		//책 등록
		//BookDao, BookVo 이용해서 등록
		//(수정, 삭제, 리스트)
		
		//책 전체를 출력
		//(책 --> 책정보 + 작가정보)
		//BookVo --> 
		
	}

}
