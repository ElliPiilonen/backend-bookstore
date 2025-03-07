package bookstore.bookstore;

import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import bookstore.bookstore.domain.Book;
import bookstore.bookstore.domain.BookRepository;
import bookstore.bookstore.domain.Category;
import bookstore.bookstore.domain.CategoryRepository;
import bookstore.bookstore.domain.User;
import bookstore.bookstore.domain.UserRepository;

@DataJpaTest
public class UserRepositoryTests {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private CategoryRepository crepository;
    @Autowired
    private UserRepository urepository;

    @Test
    public void findByNameShouldReturnUser() {
        List<User> users = urepository.findByName("ADMIN");
        assertThat(users).hasSize(1);
    }

    @Test
    public void createNewUser() {
        User user = new User("testuser", passwordEncoder.encode("password"), "USER");
        urepository.save(user);
        assertThat(user.getId()).isNotNull();

    }

    @Test
    public void deleteUser() {
        User user = new User("testuser", passwordEncoder.encode("password"), "USER");
        urepository.save(user);
        Long userId = user.getId();

        urepository.delete(user);
        assertThat(urepository.findById(userId)).isEmpty();
    }
}
