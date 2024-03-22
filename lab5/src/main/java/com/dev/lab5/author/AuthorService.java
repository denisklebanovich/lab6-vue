package com.dev.lab5.author;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorService {
	private final AuthorRepository authorRepository;
	private final AuthorMapper authorMapper;

	public AuthorDto createAuthor(AuthorRequest author) {
		return authorMapper.toDto(authorRepository.save(authorMapper.toEntity(author)));
	}

	public List<AuthorDto> getAuthors() {
		return authorRepository.findAll().stream()
				.map(authorMapper::toDto)
				.toList();
	}

	public AuthorDto getAuthor(Integer id) {
		return authorRepository.findById(id)
				.map(authorMapper::toDto)
				.orElseThrow(() -> new RuntimeException("Author not found"));
	}

	public AuthorDto updateAuthor(Integer id, AuthorRequest author) {
		Author entity = authorRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Author not found"));
		entity.setName(author.getName());
		entity.setSurname(author.getSurname());
		return authorMapper.toDto(authorRepository.save(entity));
	}

	public void deleteAuthor(Integer id) {
		authorRepository.deleteById(id);
	}
}
