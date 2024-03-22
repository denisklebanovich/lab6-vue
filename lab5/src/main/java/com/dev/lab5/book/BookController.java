package com.dev.lab5.book;

import io.swagger.v3.oas.annotations.Operation;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/books")
public class BookController {
	private final BookService bookService;

@PostMapping
	@Operation(summary = "Create book", description = "Create a new book")
	public BookDto createBook(@RequestBody BookRequest book) {
		return bookService.createBook(book);
	}

	@GetMapping
	@Operation(summary = "Get books", description = "Get all books")
	public List<BookDto> getBooks() {
		return bookService.getBooks();
	}

	@GetMapping("/{id}")
	@Operation(summary = "Get book", description = "Get book by id")
	public BookDto getBook(@PathVariable Integer id) {
		return bookService.getBook(id);
	}

	@PutMapping("/{id}")
	@Operation(summary = "Update book", description = "Update book by id")
	public BookDto updateBook(@PathVariable Integer id, @RequestBody BookRequest book) {
		return bookService.updateBook(id, book);
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Delete book", description = "Delete book by id")
	public void deleteBook(@PathVariable Integer id) {
		bookService.deleteBook(id);
	}
}
