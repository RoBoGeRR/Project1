package ua.kulynjak.project1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.kulynjak.project1.dao.BookDAO;
import ua.kulynjak.project1.dao.PersonDAO;
import ua.kulynjak.project1.models.Book;

@Controller
@RequestMapping("/books")
public class BooksController {
   private final BookDAO bookDAO;
   private final PersonDAO personDAO;
    @Autowired
    public BooksController(BookDAO bookDAO, PersonDAO personDAO) {
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
    }
    @GetMapping()
    public String index(Model model){
       model.addAttribute("books",bookDAO.index());
       return "books/index";
    }
    @RequestMapping("/{id}")
    public String show(@PathVariable("id")int id, Model model){
        model.addAttribute("book", bookDAO.show(id));
        return "books/show";
    }
    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id")int id, Model model){
        model.addAttribute("book",bookDAO.show(id));
        return "books/edit";
    }

   @GetMapping("/new")
   public String newBook( @ModelAttribute("book")Book book){

       return "books/create";

   }
    @PostMapping()
    public String create(@ModelAttribute("book") Book book){
        bookDAO.save(book);
        return "redirect:/books";
    }
    @PatchMapping("/{id}")
    public String update(@PathVariable("id")int id,@ModelAttribute("book")Book book){
        bookDAO.update(id,book);
        return "redirect:/books";
    }

}
