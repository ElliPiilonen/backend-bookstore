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
public class CategoryRepositoryTests {
    @Autowired
    private BookRepository repository;
    @Autowired
    private CategoryRepository crepository;

    @Test
    public void findByNameShouldReturnCategory() {
        List<Category> categories = crepository.findByName("Novel");
        assertThat(categories).hasSize(1);
    }

    @Test
    public void createNewCategory() {
        Category category = new Category("TEST");
        crepository.save(category);
        assertThat(category.getId()).isNotNull();

    }

    @Test
    public void deleteCategory() {
        Category category = new Category("TEST");
        crepository.save(category);
        Long catId = category.getId();

        crepository.delete(category);
        assertThat(crepository.findById(catId)).isEmpty();
    }
}
