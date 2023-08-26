package ru.itgirls.libraryproject.service;

import ru.itgirls.libraryproject.dto.GenreDto;

public interface GenreService {
    GenreDto getGenreById(Long id);
}
