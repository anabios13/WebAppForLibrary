package by.anabios13.dao;

import by.anabios13.models.Book;
import by.anabios13.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PersonDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book>  checkAvailability(int personId){
        return jdbcTemplate.query("SELECT * FROM Book WHERE person_id=?", new Object[]{personId},new BeanPropertyRowMapper<>(Book.class));
    };

    public void delete(int personId) {
        jdbcTemplate.update("DELETE FROM person Where person_id=?", personId);
    }

    public List<Person> index() {
        return jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class));//отображение полученных от БД строк на объект Person(данный объект реализуется если все полученные столбцы таблицы совпадают с полями данного класса)
        //  в случае когда имеется несовпадение столбцов таблицы с полями класса(например, когда некоторые поля отутсвуют) необходимо реализовать собственный RowMapper
    }

    public Person show(int personId) {

        return jdbcTemplate.query("SELECT * FROM person WHERE person_id=?", new Object[]{personId}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny().orElse(null);//лямбда выражение в котором проверяется есть ли в списке хотя бы 1 объект Person, если есть- возращает его, иначе возвращает null
    }

    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO person(name , year_of_birth) VALUES(?,?)", person.getName(), person.getYearOfBirth());
    }

    public void update(int personId, Person updatePerson) {
        jdbcTemplate.update("UPDATE person SET name=?, year_of_birth=? WHERE person_id=?", updatePerson.getName(), updatePerson.getYearOfBirth(), personId);
    }
}
