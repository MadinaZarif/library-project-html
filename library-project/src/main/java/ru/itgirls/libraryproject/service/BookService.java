package ru.itgirls.libraryproject.service;

import ru.itgirls.libraryproject.dto.BookCreateDto;
import ru.itgirls.libraryproject.dto.BookDto;
import ru.itgirls.libraryproject.dto.BookNewDto;
import ru.itgirls.libraryproject.dto.BookUpdateDto;

import java.util.List;

public interface BookService {

    BookDto getByNameV3(String name);

    BookDto getByNameV1(String name);

    BookDto getByNameV2(String name);

    BookNewDto creativeBook(BookCreateDto bookCreateDto);

    BookDto updateBook(BookUpdateDto bookUpdateDto);

    void deleteBook(Long id);

    List<BookDto> getAllBooks();
}
