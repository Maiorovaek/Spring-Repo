package ru.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.dao.BookDAO;

import ru.dao.ReaderDAO;
import ru.models.Book;
import ru.models.Reader;

import java.util.Optional;


@Controller
@RequestMapping("/library/book")
public class BookController {

    private final BookDAO bookDAO;
    private final ReaderDAO readerDAO;

    public BookController(BookDAO bookDAO, ReaderDAO readerDAO) {
        this.bookDAO = bookDAO;
        this.readerDAO = readerDAO;
    }

    // список книг
    @GetMapping
    public String index(Model model) {
        model.addAttribute("books", bookDAO.index());
        return "library/book/index";
    }


    //информация о конкретной книге
    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model, @ModelAttribute("reader") Reader reader) {
        model.addAttribute("book", bookDAO.show(id));
        Optional<Reader> bookOwner = bookDAO.getReaderByBookId(id);

        if (bookOwner.isPresent())
            model.addAttribute("owner", bookOwner.get());
        else
            model.addAttribute("readers", readerDAO.index());
        //   model.addAttribute("reader", bookDAO.getReaderByBookId(id));
        //   System.out.println(model.addAttribute("reader", bookDAO.getReaderByBookId(id)));
        return "library/book/show";
    }

    //отредактировать информацию о  книге
    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("book", bookDAO.show(id));
        return "library/book/edit";
    }

    //страница создания новой книги
    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book) {
        return "library/book/new";
    }


    @PostMapping()
    public String create(@ModelAttribute("book") Book book) {
        bookDAO.save(book);
        return "redirect:/library/book";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") Book book, @PathVariable("id") int id) {
        bookDAO.update(id, book);
        return "redirect:/library/book";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        bookDAO.delete(id);
        return "redirect:/library/book";
    }

    //освобождает книгу
    @PatchMapping("/{id}/release")
    public String realease(@PathVariable("id") int id) {
        bookDAO.release(id);
        return "redirect:/library/book/" + id;
    }

    //назначить человека
    @PatchMapping("/{id}/assign")
    public String assign(@PathVariable("id") int id, @ModelAttribute("reader") Reader selectedReader) {
        //ModelAttribute создает пустого человека (пустой объект класса Person)
        System.out.println("id " +id);
        System.out.println("Readerid " + selectedReader.getReader_id());
        bookDAO.assign(id, selectedReader);
        System.out.println(id);
        return "redirect:/library/book/" + id;
    }

}
