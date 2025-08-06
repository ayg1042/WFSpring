package com.example.demo.dto;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class BookDto {
	private UUID key;
	
	@NotEmpty(message = "책 이름은 필수있니다.")
	@Size(min = 1, message = "책 이름은 필수있니다.")
	private String book_name;
	
	@NotEmpty(message = "작성자는 빌 수 없습니다.")
	@Size(min = 1, message = "작성자는 빌 수 없습니다.")
    private String author;
	
	@NotEmpty(message = "Type타입은 빌 수 없습니다.")
	@Size(min = 1, message = "Type타입은 빌 수 없습니다.")
    private String type;
    
    private Date publication;
    
    public BookDto() {
    	this.key = UUID.randomUUID();
    	this.publication = new Date();
    }
    
//    public BookDto(BookDto Data) {
//    	this.key = Data.getKey();
//        this.book_name = Data.getBook_name();
//        this.author = Data.getAuthor();
//        this.type = Data.getType();
//        this.publication = Data.getPublication();
//    }
    
    public BookDto(String[] Data) {
    	this();
    	this.book_name = Data[0];
    	this.author = Data[1];
    	this.type = Data[2];
    }

}
