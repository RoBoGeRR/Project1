package ua.kulynjak.project1.dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ua.kulynjak.project1.models.Book;
import ua.kulynjak.project1.models.Person;

import java.util.ArrayList;
import java.util.List;

@Component
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;

    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public List<Book> index (){
        return jdbcTemplate.query("Select * from Book", new BeanPropertyRowMapper<>(Book.class));
    }
    public Book show(int id){
        return jdbcTemplate.query("select * from book where id =?", new Object[]{id}, new BeanPropertyRowMapper<>(Book.class)).stream().findAny().orElse(null);
    }
    public void save(Book book){
         jdbcTemplate.update("insert into book(name, author, age) values (?,?,?)", book.getName(),book.getAuthor(),book.getAge());
    }
    public void update(int id , Book book){
        jdbcTemplate.update("update book set name=?,author=?,age=? where id = ?", book.getName(),book.getAuthor(),book.getAge(),id);
    }
    public void delete(int id){
        jdbcTemplate.update("delete from book where id = ?", id);
    }
}
