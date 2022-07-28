package ru.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.models.Book;
import ru.models.Reader;

import java.util.List;

@Component
public class ReaderDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ReaderDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Reader> index() {
        return jdbcTemplate.query("SELECT * from reader",
                new BeanPropertyRowMapper<>(Reader.class));
    }


    public Reader show(int reader_id) {
        return jdbcTemplate.query("SELECT * from reader where reader_id=?",
                new Object[]{reader_id},
                new BeanPropertyRowMapper<>(Reader.class)).stream().findAny().orElse(null);
    }

    public void delete(int reader_id) {
        jdbcTemplate.update("DELETE  FROM Reader where reader_id=?",
                reader_id);
    }


    public void update(int reader_id, Reader updateReader) {
        jdbcTemplate.update("UPDATE reader SET name=?, year=?  WHERE reader_id=?",

                updateReader.getName(), updateReader.getYear(), reader_id);
    }


    public void save(Reader reader) {
        jdbcTemplate.update("INSERT INTO reader(name, year) VALUES (?,?)",
                reader.getName(), reader.getYear());
    }

    public List<Book> getBookByPersonId(int reader_id) {
       List<Book> books =  jdbcTemplate.query("SELECT * from book " +
                        " where reader_id=?", new Object[]{reader_id},
                new BeanPropertyRowMapper<>(Book.class));
        System.out.println(books);
       return books;
    }
}

