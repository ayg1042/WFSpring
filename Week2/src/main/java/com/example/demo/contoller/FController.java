package com.example.demo.contoller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.BookDto;
import com.example.demo.service.BookServiceImpl;

@Controller
public class FController {
	
	@Autowired BookServiceImpl bookService;
	
	@GetMapping("/")
	public String index() {
		if(bookService.dataCheck()) {
			bookService.setData();
		}
		return "index";
	}
	
//	1. 전체 조회
	@GetMapping("/showAll")
	public String showAll(Model model) {
		ArrayList<BookDto> list = bookService.showAll();
		model.addAttribute("showList", list);
		return "index";
	}
	
	@GetMapping("/showAll2")
	@ResponseBody
	public ArrayList<BookDto> showAll2() {
		return bookService.showAll();
		// return bookList;
	}
	
//	2. Key로 조회
	@GetMapping("/find")
	@ResponseBody
	public Map<String,BookDto> find(@RequestParam("key") String key) {
		
		Map<String, BookDto> result = new HashMap<>();
		BookDto dto = bookService.find(key);
		
		if(dto != null) {
			result.put("없는 Key 값 입니다", null);
			return result;
		}
		
		result.put("검색 결과 : ", dto);
		return result;
	}
	
//	3. 신규 레코드 추가
	@GetMapping("addBook")
	public String addBook(
			@RequestParam(name = "bookName", defaultValue = "null") String bookName,
			@RequestParam(name = "author", defaultValue = "null") String author,
			@RequestParam(name = "type", defaultValue = "null") String type,
			Model model
			) {
		
		// 입력값 확인
		if(bookName.equals("null") || author.equals("null") || type.equals("null")) {
			model.addAttribute("result", false);
			return "index";
		}
		
		// 데이터 저장 확인
		String[] data = {bookName, author, type};
		Boolean check = bookService.addData(data);
		if(!check) {
			model.addAttribute("result", false);
			return "index";
		}
		
		// 성공
		// bookList.add(dto);
		model.addAttribute("result", true);
		
		return "index";
	}
	
//	4. 수정
	@GetMapping("/update")
	public String update(@RequestParam(name = "key", defaultValue = "null") String key, Model model) {
		
		BookDto dto = bookService.find(key);
		
		if(dto == null) {
			model.addAttribute("notFind", 0);
			return "update";
		}
		model.addAttribute("notFind", 1);
		model.addAttribute("book", dto);
		
		return "update";
	}
	
	@PostMapping("/update")
	public String update(
			@RequestParam("key") String key,
			@RequestParam("bookName") String bookName,
			@RequestParam("author") String author,
			@RequestParam("type") String type,
			Model model
			) {
		
		Boolean ck = bookService.update(key, bookName, author, type);
		
		model.addAttribute("result", ck);
		
		return "index";
	}
	
//	5. 삭제
	@GetMapping("/delete")
	public String delete(@RequestParam("key") String key, Model model) {
		
		BookDto dto = bookService.find(key);
		
		if(dto == null) {
			model.addAttribute("result", false);
			return "index";
		}
		
		bookService.delete(dto);
		model.addAttribute("result", true);
		
		return "index";
	}
	
	
}
