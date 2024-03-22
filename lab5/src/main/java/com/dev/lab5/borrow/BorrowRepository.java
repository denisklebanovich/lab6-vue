package com.dev.lab5.borrow;

import com.dev.lab5.book.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowRepository extends JpaRepository<Borrow, Integer> {
	boolean existsByBookAndReturnDateIsNull(Book book);
}
