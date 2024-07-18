package com.example.project.controller;

import com.example.project.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/authors")
@Validated
public class AuthorController {
    @Autowired
    private AuthorService authorService;

}
