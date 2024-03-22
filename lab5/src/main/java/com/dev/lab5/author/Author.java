package com.dev.lab5.author;

import com.dev.lab5.book.Book;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collections;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "authors")
public class Author {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String surname;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "author")
	private List<Book> books = Collections.emptyList();
}
