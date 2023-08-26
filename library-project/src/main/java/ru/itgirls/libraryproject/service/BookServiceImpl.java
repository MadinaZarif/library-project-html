package ru.itgirls.libraryproject.service;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.itgirls.libraryproject.dto.*;
import ru.itgirls.libraryproject.model.Book;
import ru.itgirls.libraryproject.model.Genre;
import ru.itgirls.libraryproject.repository.BookRepository;
import ru.itgirls.libraryproject.repository.GenreRepository;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {
    private final GenreRepository genreRepository;
    private final BookRepository bookRepository;

    @Override
    public BookDto getByNameV3(String name) {
        Specification<Book> bookSpecification = Specification.where(new Specification<Book>() {
            @Override
            public Predicate toPredicate(Root<Book> root,
                                         CriteriaQuery<?> query,
                                         CriteriaBuilder cb) {
                return cb.equal(root.get("name"), name);
            }
        });

        Book book = bookRepository.findOne(bookSpecification).orElseThrow();
        return convertToDto(book);
    }

    @Override
    public BookDto getByNameV1(String name) {
        Book book = bookRepository.findBookByName(name).orElseThrow();
        return convertToDto(book);
    }


    @Override
    public BookDto getByNameV2(String name) {
        Book book = bookRepository.findBookByNameBySql(name).orElseThrow();
        return convertToDto(book);
    }

    // Метод для создания book на основе переданных данных из Dto
    @Override
    public BookNewDto creativeBook(BookCreateDto bookCreateDto) {
        // Сохраняем книгу в репозитории после конвертации из Dto в сущность
        Genre genre = genreRepository.findById(bookCreateDto.getGenreId()).orElseThrow(RuntimeException::new);
        Book book = new Book();
        book.setName(bookCreateDto.getName());
        book.setGenre(genre);
        bookRepository.save(book);
        // Конвертируем сущность книги обратно в Dto
        BookNewDto bookNewDto = convertEntityDto(book);
        // Возвращаем созданный Dto книги
        return bookNewDto;
    }

    // Приватный метод для конвертации Dto в сущность Book
    private BookNewDto convertEntityDto(Book book) {
        BookNewDto bookNewDto = BookNewDto.builder()
                .name(book.getName())
                .id(book.getId())
                .genre_id(book.getGenre().getId())
                .build();
        return bookNewDto;
    }

    private BookDto convertEntityToDto(Book book) {
        List<AuthorDto> authorDtoList = null;
        if (book.getAuthors() != null) {
            // Конвертируем список авторов книги в список Dto авторов
            authorDtoList = book.getAuthors()
                    .stream()
                    .map(author -> AuthorDto.builder()
                            .id(author.getId())
                            .name(author.getName())
                            .surname(author.getSurname())
                            .build())
                    .toList();
        }

        // Создаем Dto книги с информацией об авторах и жанре
        BookDto bookDto = BookDto.builder()
                .id(book.getId())
                .name(book.getName())
                .authors(authorDtoList)
                .build();
        return bookDto;
    }


    @Override
    public BookDto updateBook(BookUpdateDto bookUpdateDto) {
        Book book = bookRepository.findById(bookUpdateDto.getId()).orElseThrow();
        book.setName(bookUpdateDto.getName());


        Book saveBook = bookRepository.save(book);
        BookDto bookDto = convertEntityToDto(saveBook);
        return bookDto;
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public List<BookDto> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        return books.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private BookDto convertToDto(Book book) {
        return BookDto.builder()
                .id(book.getId())
                .genre(book.getGenre().getName())
                .name(book.getName())
                .build();
    }
}
