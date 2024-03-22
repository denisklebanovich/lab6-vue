package com.dev.lab5.borrow;

import com.dev.lab5.book.Book;
import com.dev.lab5.reader.Reader;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import java.time.LocalDateTime;

@Entity
@Table(name = "borrows")
@NoArgsConstructor
@Getter
@Setter
public class Borrow {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	private Book book;

	@ManyToOne
	private Reader reader;

	private LocalDateTime borrowDate;
	private LocalDateTime returnDate;

	public Borrow(Book book, Reader reader) {
		this.book = book;
		this.reader = reader;
		this.borrowDate = LocalDateTime.now();
	}

	public void setReturnDate() {
		this.returnDate = LocalDateTime.now();
	}
}
