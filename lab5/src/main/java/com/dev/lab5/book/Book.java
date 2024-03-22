package com.dev.lab5.book;

import com.dev.lab5.author.Author;
import com.dev.lab5.borrow.Borrow;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "books")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String title;
	@ManyToOne
	private Author author;
	private int pages;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "book")
	private List<Borrow> borrows = Collections.emptyList();
}
