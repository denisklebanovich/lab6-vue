package com.dev.lab5.borrow;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/borrows")
@RequiredArgsConstructor
public class BorrowController {
	private final BorrowService borrowService;

	@PostMapping
	@Operation(summary = "Borrow book", description = "Borrow a book")
	public BorrowDto borrow(BorrowRequest borrowRequest) {
		return borrowService.borrowBook(borrowRequest);
	}

	@PutMapping("/{id}/return")
	@Operation(summary = "Return book", description = "Return a book")
	public BorrowDto returnBook(@PathVariable Integer id) {
		return borrowService.returnBook(id);
	}
}
