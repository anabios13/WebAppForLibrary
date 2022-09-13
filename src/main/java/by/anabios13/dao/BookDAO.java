package by.anabios13.dao;

import by.anabios13.models.Book;
import by.anabios13.models.Person;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookDAO {

    private final JdbcTemplate jdbcTemplate;



    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public Person checkAvailabilityForBook(int bookId) {

        return checkPersonId(jdbcTemplate.query("SELECT person_id FROM book WHERE book_id=?",
                new Object[]{bookId}, new BookMapper()).stream().findAny().orElse(null));
    }

    private Person checkPersonId(Book book) {
        return jdbcTemplate.query("SELECT * FROM person WHERE person_id=?",
                new Object[]{book.getForeignId()}, new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
    }

    public List<Book> index() {
        return jdbcTemplate.query("SELECT * FROM book", new BeanPropertyRowMapper<>(Book.class));//отображение полученных от БД строк на объект Person(данный объект реализуется если все полученные столбцы таблицы совпадают с полями данного класса)
        //  в случае когда имеется несовпадение столбцов таблицы с полями класса(например, когда некоторые поля отутсвуют) необходимо реализовать собственный RowMapper
    }

    public List<Person> getPeopleNames(){
        return  jdbcTemplate.query("SELECT * FROM person", new BeanPropertyRowMapper<>(Person.class));
    };

    public Object show(int bookId) {
        return jdbcTemplate.query("SELECT * FROM book WHERE book_id=?", new Object[]{bookId}, new BeanPropertyRowMapper<>(Book.class))
                .stream().findAny().orElse(null);//лямбда выражение в котором проверяется есть ли в списке хотя бы 1 объект Person, если есть- возращает его, иначе возвращает null
    }

    public void save(Book book) {
        jdbcTemplate.update("INSERT INTO book(name_of_book , author, year_of_release) VALUES(?,?,?)", book.getNameOfBook(), book.getAuthor(), book.getYearOfRelease());
    }

    public void delete(int bookId) {
        jdbcTemplate.update("DELETE FROM book Where book_id=?", bookId);
    }

    public void update(int bookId, Book updateBook) {
        jdbcTemplate.update("UPDATE book SET name_of_book=?, author=?,year_of_release=? WHERE book_id=?", updateBook.getNameOfBook(), updateBook.getAuthor(), updateBook.getYearOfRelease(), bookId);
    }

    public void chooseOwner(int personId,int bookId) {
        jdbcTemplate.update("UPDATE book SET person_id=? WHERE book_id=?", personId,bookId);
    }

    public void removeOwner(int bookId) {
        jdbcTemplate.update("UPDATE book SET person_id=null WHERE book_id=?",bookId);
    }
}
