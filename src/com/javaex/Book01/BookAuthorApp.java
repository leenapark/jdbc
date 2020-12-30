package com.javaex.Book01;

import java.util.List;

import com.javaex.Author01.AuthorDao;
import com.javaex.Author01.AuthorVo;

public class BookAuthorApp {

	public static void main(String[] args) {
		/*
		AuthorDao authorDao = new AuthorDao();
		List<AuthorVo> authorList = authorDao.getAuthorList();
		*/
		BookDao bookDao = new BookDao();
		List<BookVo> bookList = bookDao.getBookList();
		/*
		//작가 등록 6명
		//AuthorDao, AuthorVo 이용해서 등록
		AuthorVo authorVo1 = new AuthorVo("이문열", "경북 양양");
		authorDao.authorInsert(authorVo1);
		
		AuthorVo authorVo2 = new AuthorVo("박경리", "경남 통영");
		authorDao.authorInsert(authorVo2);
		
		AuthorVo authorVo3 = new AuthorVo("유시민", "17대 국회의원");
		authorDao.authorInsert(authorVo3);
		
		AuthorVo authorVo4 = new AuthorVo("기안84", "기안동에 사는 84년생");
		authorDao.authorInsert(authorVo4);
		
		AuthorVo authorVo5 = new AuthorVo("강풀", "온라인 만화 1세대");
		authorDao.authorInsert(authorVo5);
		
		AuthorVo authorVo6 = new AuthorVo("김영하", "알쓸신잡");
		authorDao.authorInsert(authorVo6);
		
		
		//책 등록
		//BookDao, BookVo 이용해서 등록
		//(수정, 삭제, 리스트)
		BookVo bookVo1 = new BookVo("우리들의 일그러진 영웅", "다림", "1998/02/22", 1);
		bookDao.bookInsert(bookVo1);
		
		BookVo bookVo2 = new BookVo("삼국지", "민음사", "2002/03/01", 1);
		bookDao.bookInsert(bookVo2);
		
		BookVo bookVo3 = new BookVo("토지", "마로니에북스", "2012/08/15", 2);
		bookDao.bookInsert(bookVo3);
		
		BookVo bookVo4 = new BookVo("유시민의 글쓰기 특강", "생각의 길", "2015/04/01", 3);
		bookDao.bookInsert(bookVo4);
		
		BookVo bookVo5 = new BookVo("패션왕", "중앙북스(books)", "2012/02/22", 4);
		bookDao.bookInsert(bookVo5);
		
		BookVo bookVo6 = new BookVo("순정만화", "재미주의", "2011/08/03", 5);
		bookDao.bookInsert(bookVo6);
		
		BookVo bookVo7 = new BookVo("오직 두사람", "문학동네", "2017/05/04", 6);
		bookDao.bookInsert(bookVo7);
		
		BookVo bookVo8 = new BookVo("26년", "재미주의", "2012/02/04", 5);
		bookDao.bookInsert(bookVo8);
		*/
		
		//책 전체를 출력
		//(책 --> 책정보 + 작가정보)
		//BookVo --> authorVo
		for(int i=0; i<bookList.size(); i++) {
			bookList.get(i).showInfo();
			//System.out.println(bookList.get(i).toString());
			//System.out.println(vo.getBookId() + ". " + vo.getTitle() + ", " + vo.getPubs() + ", " + vo.getPubDate() + ", " + vo.authorId);
		}
		
	}

}
