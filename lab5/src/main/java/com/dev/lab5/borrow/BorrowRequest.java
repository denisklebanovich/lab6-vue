package com.dev.lab5.borrow;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class BorrowRequest {
	@Schema(description = "Book's id", example = "1")
	Integer bookId;
	@Schema(description = "Reader's email", example = "john.doe@example.com")
	String readerEmail;
}
