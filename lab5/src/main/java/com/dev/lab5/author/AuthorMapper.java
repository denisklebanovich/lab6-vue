package com.dev.lab5.author;

import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface AuthorMapper {
	Author toEntity(AuthorDto authorDto);


	Author toEntity(AuthorRequest authorDto);

	AuthorDto toDto(Author author);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	Author partialUpdate(AuthorDto authorDto, @MappingTarget Author author);
}