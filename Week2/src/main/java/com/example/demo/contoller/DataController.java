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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.BookDto;
import com.example.demo.response.MyResponse;
import com.example.demo.service.BookServiceImpl;

@RestController
public class DataController {
	
	@Autowired BookServiceImpl bookService;
	
	@GetMapping("/books")
	public ResponseEntity<MyResponse> getAllBooks() {
	    List<BookDto> list = bookService.showAll();
	    if (list.isEmpty()) {
	        return ResponseEntity.noContent().build();
	    }
	    return ResponseEntity.ok(MyResponse.success(list));
	}
	
//	2. Key로 조회
	@GetMapping("/find/{key}")
	@ResponseBody
	public ResponseEntity<MyResponse> find(@PathVariable String key) {
		
		BookDto dto = bookService.find(key);
		
		if(dto == null) {
			return ResponseEntity.badRequest().body(MyResponse.failure(key));
		}
		
		return ResponseEntity.ok(MyResponse.success(dto));
	}
	
//	3. 신규 레코드 추가
//	@PutMapping("addBook")
//	public String addBook(
//			@RequestParam(name = "bookName", defaultValue = "null") String bookName,
//			@RequestParam(name = "author", defaultValue = "null") String author,
//			@RequestParam(name = "type", defaultValue = "null") String type,
//			Model model
//			) {
//		
//		// 입력값 확인
//		if(bookName.equals("null") || author.equals("null") || type.equals("null")) {
//			model.addAttribute("result", false);
//			return "index";
//		}
//		
//		// 데이터 저장 확인
//		String[] data = {bookName, author, type};
//		Boolean check = bookService.addData(data);
//		if(!check) {
//			model.addAttribute("result", false);
//			return "index";
//		}
//		
//		// 성공
//		// bookList.add(dto);
//		model.addAttribute("result", true);
//		
//		return "index";
//	}
	
	@PostMapping("/addBook")
	public ResponseEntity<MyResponse> addBook(@Validated @RequestBody BookDto book) {

	    // 필수 데이터 체크
	    if (book.getBook_name() == null || book.getAuthor() == null || book.getType() == null) {
	        return ResponseEntity.badRequest().body(MyResponse.failure(null));
	    }

	    // 저장 처리
	    boolean check = bookService.addData(new String[] {
	        book.getBook_name(),
	        book.getAuthor(),
	        book.getType()
	    });

	    if (!check) {
	        return ResponseEntity.badRequest().body(MyResponse.failure(null));
	    }

	    return ResponseEntity.status(HttpStatus.CREATED).body(MyResponse.success(book));
	}
	
//	4. 수정
//	@GetMapping("/update")
//	public String update(@RequestParam(name = "key", defaultValue = "null") String key, Model model) {
//		
//		BookDto dto = bookService.find(key);
//		
//		if(dto == null) {
//			model.addAttribute("notFind", 0);
//			return "update";
//		}
//		model.addAttribute("notFind", 1);
//		model.addAttribute("book", dto);
//		
//		return "update";
//	}
//	
//	@PostMapping("/update")
//	public String update(
//			@RequestParam("key") String key,
//			@RequestParam("bookName") String bookName,
//			@RequestParam("author") String author,
//			@RequestParam("type") String type,
//			Model model
//			) {
//		
//		Boolean ck = bookService.update(key, bookName, author, type);
//		
//		model.addAttribute("result", ck);
//		
//		return "index";
//	}
	
	@PutMapping("/update/{key}")
	public ResponseEntity<MyResponse> update(@Validated @PathVariable String key,@RequestBody BookDto book) {
		Map<String, Object> response = new HashMap<>();
		
		if(book.getBook_name() == null || book.getAuthor() == null || book.getType() == null) {
			// response.put("result", false);
	        // response.put("message", "필수 데이터 누락");
			return ResponseEntity.badRequest().body(MyResponse.failure(key));
		}
		
		Boolean ck = bookService.update(key, book);
		// response.put("result", true);
		return ResponseEntity.ok(MyResponse.success(ck));
	}
	
//	5. 삭제
//	@PostMapping("/delete")
//	public String delete(@RequestParam("key") String key, Model model) {
//		
//		BookDto dto = bookService.find(key);
//		
//		if(dto == null) {
//			model.addAttribute("result", false);
//			return "index";
//		}
//		
//		bookService.delete(dto);
//		model.addAttribute("result", true);
//		
//		return "index";
//	}
	
	@DeleteMapping("/delete/{key}")
	public ResponseEntity<MyResponse> delete(@PathVariable String key) {
		BookDto dto = bookService.find(key);
		
		if(dto == null) {
			return ResponseEntity.badRequest().body(MyResponse.failure("Key값을 확인하세요"));
		}
		bookService.delete(dto);
		
		return ResponseEntity.ok(MyResponse.success(null));
	}
}
