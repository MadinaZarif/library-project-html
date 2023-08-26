package ru.itgirls.libraryproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itgirls.libraryproject.model.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {
}
