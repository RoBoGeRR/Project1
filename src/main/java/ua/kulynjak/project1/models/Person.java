package ua.kulynjak.project1.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

public class Person {
    private int id;
    @NotEmpty
    private String name;
    @Min(value = 0, message = "age should be more than 0")
    private int age;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Person() {
    }

    public Person(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

}
