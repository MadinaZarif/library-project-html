package ru.itgirls.libraryproject.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itgirls.libraryproject.dto.AuthorDto;
import ru.itgirls.libraryproject.dto.BookDto;
import ru.itgirls.libraryproject.dto.GenreDto;
import ru.itgirls.libraryproject.model.Genre;
import ru.itgirls.libraryproject.repository.GenreRepository;
import ru.itgirls.libraryproject.service.GenreService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {
    private final GenreRepository genreRepository;
    @Override
    public GenreDto getGenreById(Long id) {
        Genre genre = genreRepository.findById(id).orElseThrow();
        return convertEntityToDto(genre);
    }
    private GenreDto convertEntityToDto(Genre genre) {
        List<BookDto> bookDtoList = genre.getBooks()
                .stream()
                .map(book -> BookDto.builder()
                        .name(book.getName())
                        .id(book.getId())
                        .authors(book.getAuthors()
                                .stream()
                                .map(author -> AuthorDto.builder()
                                        .name(author.getName())
                                        .surname(author.getSurname())
                                        .build())
                                .toList())
                        .build())
                .toList();

        return GenreDto.builder()
                .books(bookDtoList)
                .id(genre.getId())
                .name(genre.getName())
                .build();


    }
}