package com.javaex.Book01;

public class BookVo extends AuthorVo {

	//필드
	private int bookId;
	private String title;
	private String pubs;
	private String pubDate;
	
	//생성자
	public BookVo() {
	}
	
	
	public BookVo(String title, String pubs, String pubDate, int authorId) {
		super(authorId);
		this.title = title;
		this.pubs = pubs;
		this.pubDate = pubDate;
	}
	

	public BookVo(int bookId, String title, String pubs, String pubDate, int authorId) {
		super(authorId);
		this.bookId = bookId;
		this.title = title;
		this.pubs = pubs;
		this.pubDate = pubDate;
	}
	
	//메소드 g/s
	public int getBookId() {
		return bookId;
	}


	public void setBookId(int bookId) {
		this.bookId = bookId;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getPubs() {
		return pubs;
	}


	public void setPubs(String pubs) {
		this.pubs = pubs;
	}


	public String getPubDate() {
		return pubDate;
	}


	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}

	
	//메소드 일반
	
	//출력
	public void showInfo() {
		System.out.println(bookId + ". " + title + ", " + pubs + ", " + pubDate + ", " + this.authorId);	
	}
	
	
	@Override
	public String toString() {
		return "BookVo [bookId=" + bookId + ", title=" + title + ", pubs=" + pubs + ", pubDate=" + pubDate + "]";
	}
}
