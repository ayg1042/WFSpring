package com.example.demo.service;

import java.util.ArrayList;
import java.util.UUID;

import com.example.demo.dto.BookDto;

public interface BookService {
	
	public Boolean dataCheck();
	
	public void setData();
	
	public ArrayList<BookDto> showAll();
	
	public UUID keyCheck(String key);

	public BookDto find(String key);
	
	public Boolean addData(String[] data);
	
	public Boolean update(String key, BookDto dto);
	
	public void delete(BookDto dto);
}
