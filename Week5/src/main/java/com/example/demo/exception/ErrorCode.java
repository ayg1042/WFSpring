package com.example.demo.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public enum ErrorCode {
	NULL_POINTER(HttpStatus.INTERNAL_SERVER_ERROR, "리스트가 비어 있습니다."),
    VALIDATION_ERROR(HttpStatus.BAD_REQUEST, "잘못된 요청입니다."),
    ILLEGAL_ARGUMENT(HttpStatus.NOT_FOUND, "key값을 확인하세요"),
    NO_SUCH_ELEMENT(HttpStatus.NOT_FOUND, "key값을 확인하세요"),
    RESOURCE_NOT_FOUND(HttpStatus.NOT_FOUND, "없는 주소입니다."),
    GENERIC_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "에러발생 예제, 서버 오류가 발생했습니다.");
	
	private final HttpStatus status;
    private final String message;

    ErrorCode(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
	
}
