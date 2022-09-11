package by.anabios13.dao;

import by.anabios13.models.Book;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookMapper implements RowMapper<Book> {

    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
       Book book= new Book();

       book.setForeignId(rs.getInt("person_id"));
       return book;
    }
}
