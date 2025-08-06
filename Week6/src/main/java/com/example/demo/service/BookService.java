package com.example.demo.service;

import java.util.ArrayList;
import java.util.UUID;

import org.springframework.web.bind.MethodArgumentNotValidException;

import com.example.demo.dto.BookDto;

public interface BookService {
	
	public Boolean dataCheck();
	
	public void setData();
	
	public ArrayList<BookDto> showAll() throws NullPointerException;
	
	public UUID keyCheck(String key);

	public BookDto find(String key);
	
	public Boolean addData(BookDto data);
	
	public Boolean update(BookDto dto);
	
	public void delete(BookDto dto);
}
