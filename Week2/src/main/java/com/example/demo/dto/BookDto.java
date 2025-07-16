package com.example.demo.dto;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
	
	@NotNull(message = "책 이름은 필수있니다.")
    private String book_name;
	@NotEmpty(message = "작성자는 빌 수 없습니다.")
    private String author;
	@NotEmpty(message = "타입은 빌 수 없습니다.")
    private String type;
    
    private Date publication;
    
    public BookDto() {
    	this.key = UUID.randomUUID();
    	this.publication = new Date();
    }
    
    public BookDto(String[] Data) {
    	this();
        this.book_name = Data[0];
        this.author = Data[1];
        this.type = Data[2];
    }

}
