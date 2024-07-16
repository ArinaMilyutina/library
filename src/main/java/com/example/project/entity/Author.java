package com.example.project.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "authors")
public class Author extends AbstractEntity {
    private String name;
    private String surname;
    private LocalDate birthDate;
    @OneToMany(mappedBy = "author",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Book> books;
}
