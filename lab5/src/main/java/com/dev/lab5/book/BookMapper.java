package com.dev.lab5.book;

import com.dev.lab5.author.Author;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface BookMapper {
	@Mapping(source = "authorId", target = "author.id")
	Book toEntity(BookDto bookDto);

	@Mapping(source = "author", target = "author")
	Book toEntity(BookRequest request, Author author);

	@Mapping(source = "author.id", target = "authorId")
	@Mapping(target = "author", expression = "java(authorToString(book.getAuthor()))")
	BookDto toDto(Book book);

	default String authorToString(Author author) {
		return author.getName() + " " + author.getSurname();
	}

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	@Mapping(source = "authorId", target = "author.id")
	@Mapping(target = "author", expression = "java(authorToString(book.getAuthor()))")
	Book partialUpdate(BookDto bookDto, @MappingTarget Book book);
}