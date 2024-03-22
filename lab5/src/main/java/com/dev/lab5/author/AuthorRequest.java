package com.dev.lab5.author;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class AuthorRequest {
	@Schema(description = "Author's name", example = "Mary")
	private String name;
	@Schema(description = "Author's surname", example = "Doe")
	private String surname;
}
