package com.fadevox.quotesapp.controller;

// Java imports

// Spring framework imports
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// Custom imports

// Clasa HomeController care este folosita pentru a gestiona request-urile primite in directorul principal
@RestController
@RequestMapping("/")
public final class HomeController {
    // Metoda care returneaza un mesaj de test
    @GetMapping
    public ResponseEntity<Object> handler() {
        return new ResponseEntity<>("Testing", HttpStatus.OK);
    }
}
