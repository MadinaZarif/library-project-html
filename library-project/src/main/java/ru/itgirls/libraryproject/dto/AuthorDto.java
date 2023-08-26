package ru.itgirls.libraryproject.dto;

import liquibase.license.LicenseService;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

public class AuthorDto {
    private Long id;
    private String name;
    private String surname;

    private List<BookDto> books;

}
