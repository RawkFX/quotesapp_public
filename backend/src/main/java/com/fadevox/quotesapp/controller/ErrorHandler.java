package com.fadevox.quotesapp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

// Clasa ErrorHandler care este folosita pentru a gestiona request-urile primite si a returna un mesaj de eroare
@ControllerAdvice
public class ErrorHandler {
    // Metoda care returneaza un mesaj de eroare in cazul in care apare o exceptie
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGlobalException() {
        // Returnam un mesaj de eroare
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aceasta pagina nu este disponibila");
    }
}
