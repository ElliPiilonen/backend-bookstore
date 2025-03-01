package bookstore.bookstore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import bookstore.bookstore.domain.Book;
import bookstore.bookstore.domain.BookRepository;
import bookstore.bookstore.domain.Category;
import bookstore.bookstore.domain.CategoryRepository;

@SpringBootApplication
public class BookstoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner books(CategoryRepository crepository, BookRepository repository) {
		return (args) -> {

			Category novel = new Category("Novel");
			Category autobio = new Category("Autobiography");
			Category fantasy = new Category("Fantasy");

			crepository.save(novel);
			crepository.save(autobio);
			crepository.save(fantasy);

			Book b1 = new Book("Testikirja", "Testi Testaaja", 2025, "123456-7", 29.9, novel);
			Book b2 = new Book("Ellin elämänkerta", "Elli Piilonen", 2003, "535252-7", 88.0, autobio);
			Book b3 = new Book("Ollin oravanpyörä", "Olli Oravainen", 1996, "48229-97", 32.9, fantasy);

			repository.save(b1);
			repository.save(b2);
			repository.save(b3);

		};
	}
}
