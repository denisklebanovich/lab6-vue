package com.dev.lab5.borrow;

import com.dev.lab5.book.BookMapper;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {BookMapper.class})
public interface BorrowMapper {
	@Mapping(source = "readerSurname", target = "reader.surname")
	@Mapping(source = "readerName", target = "reader.name")
	@Mapping(source = "readerEmail", target = "reader.email")
	Borrow toEntity(BorrowDto borrowDto);

	@InheritInverseConfiguration(name = "toEntity")
	BorrowDto toDto(Borrow borrow);

	@InheritConfiguration(name = "toEntity")
	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	Borrow partialUpdate(BorrowDto borrowDto, @MappingTarget Borrow borrow);
}