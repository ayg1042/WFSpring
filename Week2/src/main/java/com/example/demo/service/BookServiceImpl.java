package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.demo.dto.BookDto;

@Service
public class BookServiceImpl implements BookService {

	ArrayList<BookDto> bookList = new ArrayList<BookDto>();
	
	@Override
	public Boolean dataCheck() {
		
		if(this.bookList.isEmpty()) {
			return true;
		}
		
		return false;
	}
	
//	데이터 셋팅
	public void setData() {
		System.out.println("데이터 초기 설정");
		String[] test1 = {"책1", "작성자1", "소설"};
        String[] test2 = {"책2", "작성자2", "국문학"};
        String[] test3 = {"책3", "작성자3", "영문학"};
		this.bookList.add(new BookDto(test1));
		this.bookList.add(new BookDto(test2));
		this.bookList.add(new BookDto(test3));
		
	}

//	1. 전체 조회
	@Override
	public ArrayList<BookDto> showAll() {
		
		ArrayList<BookDto> list = new ArrayList<>();
		
		if(bookList.isEmpty()) {
			
			return null;
		
		}else {
			
			bookList.stream().forEach(i -> {				
				list.add(i);
			});
			
			return list;
		}
	}
	
//	KeyCheck
	public UUID keyCheck(String key) {
		
		UUID keyCk = null;
		
		try {
			
			keyCk = UUID.fromString(key);
			
		}catch (IllegalArgumentException e) {
			
		}
		
		return keyCk;
	}

// 2. key로 조회
	public BookDto find(String key) {
		
		UUID keyChk = keyCheck(key);
		
		if(keyChk == null) return null;
		
		BookDto dto = bookList.stream().filter( i -> i.getKey().equals(keyChk)).findFirst().orElse(null);
		
		return dto;
	}
	
// 3. 데이터 추가
	public Boolean addData(String[] data) {
		BookDto dto = new BookDto(data);
		
		try {			
			bookList.add(dto);
		}catch (NullPointerException e) {
			return false;
		}
		
		return true;
	}

//	4. 수정
	@Override
	public Boolean update(String key, String bookName, String author, String type) {
		
		BookDto dto = find(key);
		
		if(dto == null)	return false;
		
		dto.setBook_name(bookName);
		dto.setAuthor(author);
		dto.setType(type);
		
		return true;
		
	}

	
	@Override
	public void delete(BookDto dto) {
		bookList.remove(dto);
	}


}
