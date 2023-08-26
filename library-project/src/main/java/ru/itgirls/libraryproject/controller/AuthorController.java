package ru.itgirls.libraryproject.controller;

import org.springframework.ui.Model;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.itgirls.libraryproject.dto.AuthorDto;
import ru.itgirls.libraryproject.service.AuthorService;
import ru.itgirls.libraryproject.service.BookService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AuthorController {
    private final AuthorService authorService;

    @GetMapping("/authors")
    String getAuthorsView(Model model) {
        List<AuthorDto> authors = authorService.getAllAuthors();
        model.addAttribute("authors", authors);
        return "authors";
    }
}
