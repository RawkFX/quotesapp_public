package com.fadevox.quotesapp.controller;
// Java imports

// Spring framework imports
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// Custom imports
import com.fadevox.quotesapp.model.Quote;
import com.fadevox.quotesapp.service.QuoteService;


// Clasa QuoteController care este folosita pentru a gestiona request-urile primite in /api/v1
@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*")
public class QuoteController {

    private final QuoteService quoteService;

    // Constructorul clasei QuoteController
    @Autowired
    public QuoteController(QuoteService quoteService) {
        this.quoteService = quoteService;
    }

    // Metoda care returneaza un citat random din baza de date
    @GetMapping("/quote")
    public ResponseEntity<Quote> getQuote() {
        // Apelam metoda getRandomQuote din clasa QuoteService pentru a prelua un citat random din baza de date
        Quote randomQuote = quoteService.getRandomQuote();

        // Returnam citatul in format JSON sau status code-ul 404 daca citatul este null
        return randomQuote != null ? new ResponseEntity<>(randomQuote, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/firstQuote")
    // Metoda care returneaza primul citat din baza de date
    public ResponseEntity<Quote> getFirstQuote() {
        // Apelam metoda getFirstQuote din clasa QuoteService pentru a prelua primul citat din baza de date
        Quote firstQuote = quoteService.getFirstQuote();

        // Returnam citatul in format JSON sau status code-ul 404 daca citatul este null
        return firstQuote != null ? new ResponseEntity<>(firstQuote, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Metoda care salveaza un citat random in baza de date si il returneaza pentru a fi afisat
    @GetMapping("/storeQuote")
    public ResponseEntity<Quote> storeQuote() {
        // Apelam metoda storeRandomQuote din clasa QuoteService pentru a prelua, traduce, salva si returna un citat random din API-urile puse la dispozitie
        Quote quote = quoteService.storeRandomQuote();

        // Afisam in consola citatul returnat
        System.out.println("Quote: " + quote);

        // Returnam citatul in format JSON sau status code-ul 500 daca citatul este null
        return quote != null ? new ResponseEntity<>(quote, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
