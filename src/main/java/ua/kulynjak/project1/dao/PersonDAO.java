package ua.kulynjak.project1.dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ua.kulynjak.project1.models.Person;

import java.util.List;

@Component
public class PersonDAO {
    private final JdbcTemplate jdbcTemplate;

    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index(){
        return jdbcTemplate.query("Select * from Person", new BeanPropertyRowMapper<>(Person.class));
    }
    public Person show(int id){
        return jdbcTemplate.query("Select * from Person where id = ?" ,new Object[]{id}, new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
    }
    public void save(Person person){
        jdbcTemplate.update("INSERT INTO person(name, age) values(?,?)", person.getName(),person.getAge());
    }
    public void delete(int id){
        jdbcTemplate.update("DELETE FROM person where id = ?",id);
    }
    public void update(int id , Person updatedPerson){
        jdbcTemplate.update("Update person set name = ?, age = ? where id = ?",updatedPerson.getName(),updatedPerson.getAge(),id );
    }
}
