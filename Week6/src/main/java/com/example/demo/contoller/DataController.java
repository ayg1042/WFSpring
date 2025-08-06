package com.example.demo.contoller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.BookDto;
import com.example.demo.response.MyResponse;
import com.example.demo.service.BookServiceImpl;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/books")
public class DataController {
	
	private final BookServiceImpl bookService;
	
	@GetMapping
	public ResponseEntity<MyResponse> getAllBooks() {
	    
		List<BookDto> list = bookService.showAll();

	    return ResponseEntity.ok(MyResponse.success(list));
	}
	
//	2. Key로 조회
	@GetMapping("/{key}")
	@ResponseBody
	public ResponseEntity<MyResponse> find(@PathVariable String key) {
		
		BookDto dto = bookService.find(key);
		
		return ResponseEntity.ok(MyResponse.success(dto));
	}
	
//	3. 신규 레코드 추가
	@PostMapping
	public ResponseEntity<MyResponse> addBook(@Validated @RequestBody BookDto book) {
	    // 저장 처리
	    bookService.addData(book);

	    return ResponseEntity.status(HttpStatus.CREATED).body(MyResponse.success(book));
	}
	
//	4. 수정
	@PutMapping
	public ResponseEntity<MyResponse> update(@Validated @RequestBody BookDto book) {

		Boolean ck = bookService.update(book);

		return ResponseEntity.ok(MyResponse.success(ck));
	}
	
//	5. 삭제
	@DeleteMapping("/{key}")
	public ResponseEntity<MyResponse> delete(@PathVariable String key) {
		
		BookDto dto = bookService.find(key);
		bookService.delete(dto);
		
		return ResponseEntity.ok(MyResponse.success(null));
	}
}
