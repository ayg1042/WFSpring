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
	
}
