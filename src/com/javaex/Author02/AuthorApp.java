package com.javaex.Author02;

import java.util.List;

public class AuthorApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AuthorDao authorDao = new AuthorDao();
		List<AuthorVo> authorList = authorDao.getAuthorList();

		AuthorVo authorVo1 = new AuthorVo("이문열", "경북 양양");
		authorDao.authorInsert(authorVo1);	//작가 테이블에 저장
		
		AuthorVo authorVo2 = new AuthorVo("박경리", "경남 통영");
		authorDao.authorInsert(authorVo2);	//작가 테이블에 저장
		
		AuthorVo authorVo3 = new AuthorVo("유시민", "17대 국회의원");
		authorDao.authorInsert(authorVo3);	//작가 테이블에 저장	
		
		//리스트
		authorList = authorDao.getAuthorList();
		
		//리스트에 담은 뒤 for문을 이용해서 출력
		System.out.println("======작가 리스트======");
		for(int i=0; i<authorList.size(); i++) {
			AuthorVo vo = authorList.get(i);
			System.out.println(vo.getAuthorId() + ". " + vo.getAuthorName() + ", " + vo.getAuthorDesc());
		}
		
		//리스트 삭제
		authorDao.authorDelete(3);
		
		
		//리스트 출력
		authorList = authorDao.getAuthorList();
		
		System.out.println("======작가 리스트======");
		for(int i=0; i<authorList.size(); i++) {
			AuthorVo vo = authorList.get(i);
			System.out.println(vo.getAuthorId() + ". " + vo.getAuthorName() + ", " + vo.getAuthorDesc());
		}
		
		//리스트 수정
		AuthorVo authorVo4 = new AuthorVo(2, "김경리", "제주도");
		authorDao.authorUpdate(authorVo4);
		
		//리스트 출력
		System.out.println("======작가 리스트======");
		for(int i=0; i<authorList.size(); i++) {
			AuthorVo vo = authorList.get(i);
			System.out.println(vo.getAuthorId() + ". " + vo.getAuthorName() + ", " + vo.getAuthorDesc());
		}
		
		
		
	}

}
