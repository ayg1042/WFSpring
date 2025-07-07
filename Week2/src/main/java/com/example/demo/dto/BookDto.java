package com.example.demo.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class BookDto {
	private UUID key;
    private String book_name;
    private String author;
    private String type;
    
    public BookDto() {
    	this.key = UUID.randomUUID();
    }
    
    public BookDto(String[] Data) {
    	this();
        this.book_name = Data[0];
        this.author = Data[1];
        this.type = Data[2];
    }
    
    public void print(){
        System.out.println("Key : " + this.key + ", 제목 : " + this.book_name + ", 제작자 : " + this.author + ", 타입(장르, 종류) : " + this.type);
    }
}
