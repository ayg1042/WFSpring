package com.example.demo.ControllerAdvice;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import com.example.demo.response.MyResponse;

import jakarta.validation.ValidationException;

@ControllerAdvice
public class ControllerAdivce {
	
	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<MyResponse> NPException(NullPointerException e){
		return ResponseEntity
				.badRequest()
				.body(MyResponse.failure("리스트가 비어 있습니다."));
		// return new ResponseEntity<>("리스트가 비어 있습니다.", HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<MyResponse> VDException(MethodArgumentNotValidException e){
		Object inputData = e.getBindingResult().getTarget();
		// MyResponse.builder().result(false).data(inputData).message(e.getBindingResult().getFieldError().getDefaultMessage());
		
		return ResponseEntity
				.badRequest()
				.body(new MyResponse(false, e.getBindingResult().getFieldError().getDefaultMessage(), inputData));
	}
	
	@ExceptionHandler(NoResourceFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<MyResponse> NFException(NoResourceFoundException e){
		return ResponseEntity.badRequest().body(MyResponse.failure("없는 주소입니다."));
	}
	
	@ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<MyResponse> handleException(Exception e) {
		return ResponseEntity
				.badRequest()
				.body(MyResponse.failure("에러발생 예제, 서버 오류가 발생했습니다: " + e.getMessage() + HttpStatus.INTERNAL_SERVER_ERROR));
    }
	
}
