package ru.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.models.Book;
import ru.models.Reader;

import java.util.List;
import java.util.Optional;

@Component
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index() {
        return jdbcTemplate.query("SELECT * from book",
                new BeanPropertyRowMapper<>(Book.class));
    }


    public Book show(int id) {
        return jdbcTemplate.query("SELECT * from book where id=?",
                new Object[]{id},
                new BeanPropertyRowMapper<>(Book.class)).stream().findAny().orElse(null);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE  FROM Book where id=?", id);
    }


    public void update(int id, Book updateBook) {
        jdbcTemplate.update("UPDATE book SET name=?, author=?, year=?  WHERE id=?",

                updateBook.getName(), updateBook.getAuthor(), updateBook.getYear(), id);
    }

    public void save(Book book) {
        jdbcTemplate.update("INSERT INTO book(name, author, year) VALUES (?,?,?)",
                book.getName(), book.getAuthor(), book.getYear());
    }

    public Optional<Reader> getReaderByBookId(int id) {
        return jdbcTemplate.query(
                "SELECT reader.* from book join reader " +
                        "on book.reader_id=reader.reader_id where book.id=?",
                new Object[]{id}, new BeanPropertyRowMapper<>(Reader.class)).stream().findAny();

    }

    public void release(int id) {
        jdbcTemplate.update(
                "UPDATE book set reader_id=null where id=?",
                id);
    }

    public void assign(int id, Reader selectedReader) {
        jdbcTemplate.update(
                "update book set reader_id=? where id=?",
                selectedReader.getReader_id(), id);
    }
}