package com.dev.lab5.book;

import com.dev.lab5.author.Author;
import com.dev.lab5.author.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
	private final BookRepository bookRepository;
	private final BookMapper bookMapper;
	private final AuthorRepository authorRepository;


	public BookDto createBook(BookRequest request) {
		Author author = authorRepository.findById(request.getAuthorId())
				.orElseThrow(() -> new RuntimeException("Author not found"));
		Book book = new Book();
		book.setTitle(request.getTitle());
		book.setAuthor(author);
		book.setPages(request.getPages());
		return bookMapper.toDto(bookRepository.save(book));
	}

	public List<BookDto> getBooks() {
		return bookRepository.findAll().stream()
				.map(bookMapper::toDto)
				.toList();
	}

	public BookDto getBook(Integer id) {
		return bookRepository.findById(id)
				.map(bookMapper::toDto)
				.orElseThrow(() -> new RuntimeException("Book not found"));
	}

	public BookDto updateBook(Integer id, BookRequest book) {
		Author author = authorRepository.findById(book.getAuthorId())
				.orElseThrow(() -> new RuntimeException("Author not found"));
		Book entity = bookRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Book not found"));
		entity.setTitle(book.getTitle());
		entity.setAuthor(author);
		entity.setPages(book.getPages());
		return bookMapper.toDto(bookRepository.save(entity));
	}

	public void deleteBook(Integer id) {
		bookRepository.deleteById(id);
	}
}
