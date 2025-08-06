package com.example.demo.ControllerAdvice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import com.example.demo.exception.ErrorCode;
import com.example.demo.response.MyResponse;

import jakarta.validation.ValidationException;

@ControllerAdvice
public class ControllerAdivce {
	
	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<MyResponse> NPException(NullPointerException e){
		// return ResponseEntity.badRequest().body(MyResponse.failure("리스트가 비어 있습니다."));
		return ResponseEntity.status(ErrorCode.NULL_POINTER.getStatus()).body(MyResponse.failure(ErrorCode.NULL_POINTER));
	}
	
	// 필드별 key : value 표시
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<MyResponse> VDException(MethodArgumentNotValidException e){

//		return ResponseEntity
//				.status(ErrorCode.VALIDATION_ERROR.getStatus())
//                .body(new MyResponse(false, errorMessage, inputData));
		
		List<ObjectError> allErrors = e.getBindingResult().getAllErrors();
		
		Map<String, String> errors2 = new HashMap<>();
		
		ArrayList<String> errors = allErrors.stream()
		.map( er -> ((FieldError) er).getField() + " : " + er.getDefaultMessage()
		)
		.collect(Collectors.toCollection(ArrayList::new));
		
		allErrors.stream()
		.forEach( er -> {
			errors2.put(((FieldError) er).getField(), er.getDefaultMessage());
			}
		);
		
		return ResponseEntity
				.status(ErrorCode.VALIDATION_ERROR.getStatus())
                .body(new MyResponse(false, "입력값 오류", errors2));
		
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	public MyResponse IAException(IllegalArgumentException e) {
		String[] massage = e.getLocalizedMessage().split(" ");
		return new MyResponse(false, ErrorCode.ILLEGAL_ARGUMENT.getMessage(), massage[massage.length - 1]);
	}
	
	@ExceptionHandler(NoSuchElementException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	public MyResponse NSEException(NoSuchElementException e) {
		return new MyResponse(false, ErrorCode.NO_SUCH_ELEMENT.getMessage(), e.getLocalizedMessage());
	}
	
	@ExceptionHandler(NoResourceFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	public MyResponse NFException(NoResourceFoundException e){
		return MyResponse.failure(ErrorCode.NO_SUCH_ELEMENT.getMessage());
	}
	
	@ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
    public MyResponse handleException(Exception e) {
		return MyResponse.failure(ErrorCode.GENERIC_ERROR.getMessage() + " : " + e.getMessage());
    }
	
}
