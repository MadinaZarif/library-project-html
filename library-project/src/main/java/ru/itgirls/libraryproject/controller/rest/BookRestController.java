package ru.itgirls.libraryproject.controller.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.itgirls.libraryproject.dto.BookCreateDto;
import ru.itgirls.libraryproject.dto.BookDto;
import ru.itgirls.libraryproject.dto.BookNewDto;
import ru.itgirls.libraryproject.dto.BookUpdateDto;
import ru.itgirls.libraryproject.service.BookService;

@RequiredArgsConstructor
@RestController
public class BookRestController {
    private final BookService bookService;

    @PostMapping("/book/create")
BookNewDto createBook(@RequestBody BookCreateDto bookCreateDto) {

        return bookService.creativeBook(bookCreateDto);
    }
    @PutMapping("book/update")
    BookDto updateBook(@RequestBody BookUpdateDto bookUpdateDto) {
        return bookService.updateBook(bookUpdateDto);
    }
    @DeleteMapping("book/delete/{id}")
    void deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
    }
    @GetMapping("/book")
    BookDto getByNameV1(@RequestParam(value = "name", required = false) String name) {
        return bookService.getByNameV1(name);
    }

    @GetMapping("/book/v2")
    BookDto getByNameV2(@RequestParam(value = "name", required = false) String name) {
        return bookService.getByNameV2(name);
    }

    @GetMapping("/book/v3")
    BookDto getByNameV3(@RequestParam(value = "name", required = false) String name) {
        return bookService.getByNameV3(name);

    }
}
