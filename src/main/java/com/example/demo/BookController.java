package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * BookController
 */
@Controller

@RequestMapping("books")
public class BookController {

    @GetMapping("/all")
    public String showAll(Model model) {
        
        var booksForm = new BooksPageModel();

        for (int i = 0; i < 10; i++) {
            var item = new Book();
            item.setId(i);
            item.setTitle("タイトル" + i);
            item.setAuthor("作者" + i);
            item.setPrice(i * 100);

            booksForm.addBook(item);
        }

        model.addAttribute("form", booksForm);
        return "books/allBooks";
    }

    @PostMapping("/save")
    public String saveBooks(@ModelAttribute BooksPageModel form, Model model) {

        return "redirect:/books/all";
    }
}
