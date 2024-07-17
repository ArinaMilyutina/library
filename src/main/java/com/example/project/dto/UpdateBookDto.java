package com.example.project.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateBookDto {
    @NotBlank(message = "Title is mandatory.")
    @Size(max = 255, message = "Title must be less than 255 characters.")
    private String title;
    @NotBlank(message = "Genre is mandatory.")
    @Size(max = 100, message = "Genre must be less than 100 characters.")
    private String genre;
    @NotNull(message = "Publication date is mandatory.")
    private LocalDate publicationDate;
    private AuthorDto authorDto;
}