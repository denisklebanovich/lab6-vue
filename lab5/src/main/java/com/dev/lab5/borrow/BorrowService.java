package com.dev.lab5.borrow;

import com.dev.lab5.book.BookRepository;
import com.dev.lab5.reader.ReaderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BorrowService {
	private final BorrowRepository borrowRepository;
	private final BorrowMapper borrowMapper;
	private final BookRepository bookRepository;
	private final ReaderRepository readerRepository;

	public BorrowDto borrowBook(BorrowRequest request) {
		var book = bookRepository.findById(request.getBookId()).orElseThrow(() -> new RuntimeException("Book not found"));
		var reader = readerRepository.findById(request.getReaderEmail()).orElseThrow(() -> new RuntimeException("Reader not found"));
		if (borrowRepository.existsByBookAndReturnDateIsNull(book)) {
			throw new RuntimeException("Book already borrowed");
		}
		var borrow = new Borrow(book, reader);
		return borrowMapper.toDto(borrowRepository.save(borrow));
	}

	public BorrowDto returnBook(Integer id) {
		var borrow = borrowRepository.findById(id).orElseThrow(() -> new RuntimeException("Borrow not found"));
		borrow.setReturnDate();
		return borrowMapper.toDto(borrowRepository.save(borrow));
	}
}
