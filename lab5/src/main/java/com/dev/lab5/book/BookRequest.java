package com.dev.lab5.book;

import com.dev.lab5.author.Author;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class BookRequest {
	@Schema(description = "Book's title", example = "The Book")
	private String title;
	@Schema(description = "Book's author", example = "1")
	private Integer authorId;
	@Schema(description = "Book's pages", example = "100")
	private int pages;
}
