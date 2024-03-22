package com.dev.lab5.author;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link Author}
 */
@Value
public class AuthorDto implements Serializable {
	Integer id;
	String name;
	String surname;
}