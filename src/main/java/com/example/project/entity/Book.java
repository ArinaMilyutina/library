package com.example.project.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "books")
public class Book extends AbstractEntity {
    private String title;
    private String genre;
    private LocalDate dateOfPublication;
    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;
}
