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
public class AuthorDto {
    @NotBlank(message = "Name is mandatory.")
    @Size(max = 255,message = "Name must be less than 255 characters.")
    private String name;
    @NotBlank(message = "Surname is mandatory.")
    @Size(max = 255,message = "Surname must be less than 255 characters.")
    private String surname;
    @NotNull (message = "Birth date is mandatory.")
    private LocalDate birthDate;
}
