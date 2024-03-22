package com.dev.lab5;

import com.dev.lab5.author.Author;
import com.dev.lab5.author.AuthorRepository;
import com.dev.lab5.book.Book;
import com.dev.lab5.book.BookRepository;
import com.dev.lab5.borrow.Borrow;
import com.dev.lab5.borrow.BorrowRepository;
import com.dev.lab5.reader.Reader;
import com.dev.lab5.reader.ReaderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MockDataGenerator implements ApplicationRunner {
	private final AuthorRepository authorRepository;
	private final BookRepository bookRepository;
	private final ReaderRepository readerRepository;
	private final BorrowRepository borrowRepository;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		String[] authorNames = {"John", "Jane", "Alice", "Bob", "Carol"};
		String[] authorSurnames = {"Doe", "Smith", "Johnson", "Brown", "Davis"};

		for (int i = 0; i < authorNames.length; i++) {
			Author author = new Author();
			author.setName(authorNames[i]);
			author.setSurname(authorSurnames[i]);
			authorRepository.save(author);
		}


		String[] bookTitles = {"Java Programming", "Spring Framework", "Data Structures", "Web Development", "Cloud Computing", "Machine Learning", "Introduction to Algorithms", "Effective Java", "Design Patterns", "Microservices Architecture"};
		int[] pageNumbers = {300, 250, 350, 275, 320, 400, 450, 280, 330, 360};

		for (int i = 0; i < bookTitles.length; i++) {
			Book book = new Book();
			book.setTitle(bookTitles[i]);
			Author author = authorRepository.findById((i % authorNames.length) + 1).orElse(null);
			book.setAuthor(author);
			book.setPages(pageNumbers[i]);
			bookRepository.save(book);
		}

		String[] readerEmails = {"john.doe@example.com", "jane.smith@example.com", "alice.johnson@example.com", "bob.brown@example.com", "carol.davis@example.com"};
		String[] readerNames = {"John", "Jane", "Alice", "Bob", "Carol"};

		for (int i = 0; i < readerEmails.length; i++) {
			Reader reader = new Reader();
			reader.setEmail(readerEmails[i]);
			reader.setName(readerNames[i]);
			reader.setSurname(authorSurnames[i]); // Reusing the surname array
			readerRepository.save(reader);
		}


		for (int i = 1; i <= 5; i++) {
			Book book = bookRepository.findById(i).orElse(null);
			Reader reader = readerRepository.findById(readerEmails[i - 1]).orElse(null);

			if (book != null && reader != null) {
				Borrow borrow = new Borrow(book, reader);
				boolean returned = i % 2 == 0;
				if (returned) {
					borrow.setReturnDate();
				}
				borrowRepository.save(borrow);
			}
		}
	}
}
