package bookstore.bookstore;

import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import bookstore.bookstore.domain.Book;
import bookstore.bookstore.domain.BookRepository;
import bookstore.bookstore.domain.Category;
import bookstore.bookstore.domain.CategoryRepository;

@DataJpaTest
public class BookRepositoryTest {
    @Autowired
    private BookRepository repository;
    @Autowired
    private CategoryRepository crepository;

    @Test
    public void findByTitleShouldReturnBook() {
        List<Book> books = repository.findByTitle("Testikirja");
        assertThat(books).hasSize(1);
        assertThat(books.get(0).getIsbn()).isEqualTo("123456-7");
    }

    @Test
    public void createNewBook() {
        Category category = new Category("TEST");
        crepository.save(category);

        Book book = new Book("Test", "Tester", 2000, "aaaaa-a", 99.99, new Category("TEST"));
        repository.save(book);
        assertThat(book.getId()).isNotNull();
    }

    @Test
    public void deleteBook() {
        Book book = new Book("Test", "Tester", 2000, "aaaaa-a", 99.99, new Category("TEST"));
        repository.save(book);
        Long bookId = book.getId();

        repository.delete(book);
        assertThat(repository.findById(bookId)).isEmpty();
    }
}
