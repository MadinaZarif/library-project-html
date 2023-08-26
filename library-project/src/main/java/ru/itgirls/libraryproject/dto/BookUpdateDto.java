package ru.itgirls.libraryproject.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class BookUpdateDto {
    private Long id;
    private String name;
    private String genre;
}
