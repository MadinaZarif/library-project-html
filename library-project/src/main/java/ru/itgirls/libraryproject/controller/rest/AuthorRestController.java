package ru.itgirls.libraryproject.controller.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.itgirls.libraryproject.dto.AuthorCreateDto;
import ru.itgirls.libraryproject.dto.AuthorDto;
import ru.itgirls.libraryproject.dto.AuthorUpdateDto;
import ru.itgirls.libraryproject.service.AuthorService;

@RestController
@RequiredArgsConstructor
public class AuthorRestController {
    private final AuthorService authorService;

@PutMapping("/author/update")
AuthorDto updateAuthor(@RequestBody AuthorUpdateDto authorUpdateDto){
    return authorService.updateAuthor(authorUpdateDto);
}
    @PostMapping("/author/create")
    AuthorDto creareAuthor(@RequestBody AuthorCreateDto authorCreateDto) {
        return authorService.createAuthor(authorCreateDto);
    }

    @DeleteMapping("/author/delete/{id}")
    void deleteAuthor(@PathVariable Long id) {
    authorService.deleteAuthor(id);
    }

    @GetMapping("author/a3")
    AuthorDto getAuthorByName_3(@RequestParam("name") String name) {
        return authorService.getByName_3(name);
    }

    @GetMapping("/author/a2")
    AuthorDto getAuthorByName_2(@RequestParam("name") String name) {
        return authorService.getByName_2(name);
    }

    @GetMapping("author/{id}")
    AuthorDto getAuthorById(@PathVariable("id") Long id) {
        return authorService.getAuthorById(id);
    }

    @GetMapping("/author")
    AuthorDto getAuthorByName(@RequestParam("name") String name) {
        return authorService.getByName_1(name);
    }

}
