package com.example.demo.response;

import com.example.demo.exception.ErrorCode;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MyResponse<T> {

	private boolean result;
	private String message;
	private T data;
	
	@Builder
	public MyResponse(final boolean result, final String message, final T data) {
		this.result = result;
		this.message = message;
		this.data = data;
	}
	
	public static <T> MyResponse<T> success(T data){
		return new MyResponse<>(true, "성공", data);
	}
	
	public static <T> MyResponse<T> failure(String massage){
		return new MyResponse<>(false, massage, null);
	}
	
	public static <T> MyResponse<T> failure(ErrorCode code) {
	    return new MyResponse<>(false, code.getMessage(), null);
	}
	
	
}
