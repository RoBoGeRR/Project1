package ua.kulynjak.project1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.kulynjak.project1.dao.BookDAO;
import ua.kulynjak.project1.dao.PersonDAO;
import ua.kulynjak.project1.models.Person;

@Controller
@RequestMapping("/people")
public class PeopleController {
    @Autowired
    public PeopleController(PersonDAO personDAO, BookDAO bookDAO) {
        this.personDAO = personDAO;
        this.bookDAO = bookDAO;
    }
    private final PersonDAO personDAO;
    private final BookDAO bookDAO;

    @GetMapping()
    public String index(Model model){
        model.addAttribute("people", personDAO.index());
        return "people/index";
    }
    @RequestMapping("/{id}")
    public String show(@PathVariable("id")int id, Model model){
        model.addAttribute("person", personDAO.show(id));
        return "people/show";
    }
    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person){

        return "people/create";
    }
    @RequestMapping("/{id}/edit")
    public String edit(@PathVariable("id")int id,Model model){
        model.addAttribute("person", personDAO.show(id));
        return "people/edit";
    }
    @PatchMapping("/{id}")
    public String update(@PathVariable("id")int id,@ModelAttribute("person")Person person){
        personDAO.update(id,person);
        return "redirect:/people";
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id")int id){
        personDAO.delete(id);
        return "redirect:/people";
    }
    @PostMapping()
    public String create(@ModelAttribute("person") Person person ){
        personDAO.save(person);
        return "redirect:/people";
    }
}
