package ru.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.dao.ReaderDAO;
import ru.models.Book;
import ru.models.Reader;


@Controller
@RequestMapping("/library/reader")
public class ReaderController {

    private ReaderDAO readerDAO;

    public ReaderController(ReaderDAO readerDAO) {
        this.readerDAO = readerDAO;
    }

    // список читателей
    @GetMapping
    public String index(Model model) {
        model.addAttribute("readers", readerDAO.index());
        return "library/reader/index";
    }

    //информация о конкретном читателе
    @GetMapping("/{reader_id}")
    public String show(@PathVariable("reader_id") int reader_id, Model model) {
        model.addAttribute("reader", readerDAO.show(reader_id));
        model.addAttribute("books", readerDAO.getBookByPersonId(reader_id));
//        System.out.println(readerDAO.getBookByPersonId(7));
        return "library/reader/show";
    }

    //отредактировать информацию о  читателе
    @GetMapping("/{reader_id}/edit")
    public String edit(Model model, @PathVariable("reader_id") int reader_id) {
        model.addAttribute("reader", readerDAO.show(reader_id));
        return "library/reader/edit";
    }

    //страница создания новго пользователя
    @GetMapping("/new")
    public String newReader(@ModelAttribute("reader") Reader reader) {
        return "library/reader/new";
    }




    @PostMapping()
    public String create(@ModelAttribute("reader") Reader reader) {
        readerDAO.save(reader);
        return "redirect:/library/reader";
    }

    @PatchMapping("/{reader_id}")
    public String update(@ModelAttribute("reader") Reader reader, @PathVariable("reader_id") int reader_id) {
        System.out.println(reader_id);
        System.out.println(reader.getName());

     //   person.setId(resultSet.getInt("id"));
        readerDAO.update(reader_id, reader);
        return "redirect:/library/reader";
    }




    @DeleteMapping("/{reader_id}")
    public String delete(@PathVariable("reader_id") int reader_id) {
        readerDAO.delete(reader_id);
        return "redirect:/library/reader";
    }
}
