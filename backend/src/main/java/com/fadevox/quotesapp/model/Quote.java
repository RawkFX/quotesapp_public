package com.fadevox.quotesapp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

// Clasa Quote care este folosita pentru a crea un obiect de tip Quote
@Entity
@Table(name = "quote")
public class Quote {

    // Atributul id care reprezinta id-ul citatului
    @Id
    // Generarea automata a id-ului
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Atributul quote care reprezinta citatul
    @Column(name = "quote")
    private String quote;
    // Atributul author care reprezinta autorul
    @Column(name = "author")
    private String author;
    // Atributul category care reprezinta categoria
    @Column(name = "category")
    private String category;

    // Constructorul default al clasei Quote
    public Quote() {}

    // Constructorul Overloaded al clasei Quote
    public Quote(String quote, String author, String category) {
        this.quote = quote;
        this.author = author;
        this.category = category;
    }

    // Constructorul Overloaded al clasei ce include si id-ul citatului
    public Quote(Long id, String quote, String author, String category) {
        this.id = id;
        this.quote = quote;
        this.author = author;
        this.category = category;
    }

    // metoda setId care seteaza id-ul citatului
    public void setId(Long id) {
        this.id = id;
    }

    // metoda getId care returneaza id-ul citatului
    public Long getId() {
        return id;
    }

    // metoda setQuote care seteaza citatul
    public void setQuote(String quote) {
        this.quote = quote;
    }

    // metoda getQuote care returneaza citatul
    public String getQuote() {
        return quote;
    }

    // metoda getAuthor care returneaza autorul

    public String getAuthor() {
        return author;
    }

    // metoda setAuthor care seteaza autorul

    public void setAuthor(String author) {
        this.author = author;
    }

    // metoda getCategory care returneaza categoria

    public String getCategory() {
        return category;
    }

    // metoda setCategory care seteaza categoria

    public void setCategory(String category) {
        this.category = category;
    }

    // Override metodei toString pentru a afisa dupa formatul dorit
    @Override
    public  String toString() {
        return "Quote{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", category='" + category + '\'' +
                ", quote='" + quote + '\'' +
                '}';
    }

}
