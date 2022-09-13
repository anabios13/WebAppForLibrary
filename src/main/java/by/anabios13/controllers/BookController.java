package by.anabios13.controllers;

import by.anabios13.dao.BookDAO;
import by.anabios13.models.Book;
import by.anabios13.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookDAO bookDAO;

    @Autowired
    public BookController(BookDAO bookDAO) {this.bookDAO = bookDAO;}

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("books", bookDAO.index());
        return "books/index";
    }
    @GetMapping("/{id}")
    public String show(@PathVariable("id") int bookId, Model model,@ModelAttribute("person") Person person) {
        model.addAttribute("book", bookDAO.show(bookId));
        model.addAttribute("bookOwner", bookDAO.checkAvailabilityForBook(bookId));
        model.addAttribute("people",bookDAO.getPeopleNames());
        return "books/show";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book) {
        return "books/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {
        if(bindingResult.hasErrors())
            return "books/new";
        bookDAO.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int bookId) {
        model.addAttribute("book", bookDAO.show(bookId));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult,
                         @PathVariable("id") int bookId) {
        if(bindingResult.hasErrors())
            return "books/edit";

        bookDAO.update(bookId, book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int bookId) {
        bookDAO.delete(bookId);
        return "redirect:/books";
    }
    @PatchMapping("/add/{id}")
    public String chooseBookOwner(@ModelAttribute("person") Person person,@PathVariable("id") int bookId){
        bookDAO.chooseOwner(person.getPersonId(), bookId);
        return "redirect:/books/"+bookId;
    }

    @PatchMapping("/remove/{id}")
    public String removeBookOwner(@PathVariable("id") int bookId){
        bookDAO.removeOwner(bookId);
        return "redirect:/books/"+bookId;
    }
}
