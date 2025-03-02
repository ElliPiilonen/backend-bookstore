package bookstore.bookstore.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import bookstore.bookstore.domain.Book;
import bookstore.bookstore.domain.BookRepository;
import bookstore.bookstore.domain.CategoryRepository;

@Controller

public class BookController {
   @Autowired
   private BookRepository repository;
   @Autowired
   private CategoryRepository crepository;

   @GetMapping("/booklist")
   public String bookList(Model model) {
      model.addAttribute("books", repository.findAll());
      return "booklist";
   }

   @GetMapping("/books")
   public @ResponseBody List<Book> bookListRest() {
      return (List<Book>) repository.findAll();
   }

   @GetMapping("/book/{id}")
   public @ResponseBody Optional<Book> findBookRest(@PathVariable("id") Long bookId) {
      return repository.findById(bookId);
   }

   @RequestMapping("/add")
   public String addBook(Model model) {
      model.addAttribute("book", new Book());
      model.addAttribute("categories", crepository.findAll());
      return "addbook";
   }

   @PostMapping("/save")
   public String save(Book book) {
      repository.save(book);
      return "redirect:/booklist";
   }

   @GetMapping("/delete/{id}")
   @PreAuthorize("hasRole('ADMIN')")
   public String deleteBook(@PathVariable("id") Long bookId, Model model) {
      repository.deleteById(bookId);
      return "redirect:/booklist";
   }

   @RequestMapping("/edit/{id}")
   public String editBook(@PathVariable("id") Long bookId, Model model) {
      model.addAttribute("book", repository.findById(bookId));
      model.addAttribute("categories", crepository.findAll());
      return "editbook";
   }

   @GetMapping("/login")
   public String login(Model model) {
      return "login";
   }

   @GetMapping("/logout")
   public String logout(Model model) {
      return "redirect:/login?logout";
   }

}
