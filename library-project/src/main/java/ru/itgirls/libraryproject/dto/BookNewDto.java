package ru.itgirls.libraryproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.transform.sax.SAXResult;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class BookNewDto {
    private String name;
    private Long id;
    private Long genre_id;
}
