package com.dev.lab5.borrow;

import com.dev.lab5.book.BookDto;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link Borrow}
 */
@Value
public class BorrowDto implements Serializable {
	Integer id;
	BookDto book;
	String readerEmail;
	String readerName;
	String readerSurname;
	LocalDateTime borrowDate;
	LocalDateTime returnDate;
}