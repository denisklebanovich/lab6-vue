package com.dev.lab5.author;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AuthorController {
	private final AuthorService authorService;

	@PostMapping("/authors")
	@Operation(summary = "Create author", description = "Create a new author")
	public AuthorDto createAuthor(@RequestBody AuthorRequest author) {
		return authorService.createAuthor(author);
	}

	@GetMapping("/authors")
	@Operation(summary = "Get authors", description = "Get all authors")
	public List<AuthorDto> getAuthors() {
		return authorService.getAuthors();
	}

	@GetMapping("/authors/{id}")
	@Operation(summary = "Get author", description = "Get author by id")
	public AuthorDto getAuthor(@PathVariable Integer id) {
		return authorService.getAuthor(id);
	}

	@PutMapping("/authors/{id}")
	@Operation(summary = "Update author", description = "Update author by id")
	public AuthorDto updateAuthor(@PathVariable Integer id, @RequestBody AuthorRequest author) {
		return authorService.updateAuthor(id, author);
	}

	@DeleteMapping("/authors/{id}")
	@Operation(summary = "Delete author", description = "Delete author by id")
	public void deleteAuthor(@PathVariable Integer id) {
		authorService.deleteAuthor(id);
	}
}
