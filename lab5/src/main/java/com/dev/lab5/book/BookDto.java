package com.dev.lab5.book;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link Book}
 */
@Value
public class BookDto implements Serializable {
	Integer id;
	String title;
	Integer authorId;
	String author;
	int pages;
}