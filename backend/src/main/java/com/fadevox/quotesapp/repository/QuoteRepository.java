package com.fadevox.quotesapp.repository;

// Java imports

// Spring framework imports
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Custom imports
import com.fadevox.quotesapp.model.Quote;

// Interfata QuoteRepository care este folosita pentru a gestiona operatiile in baza de date
@Repository
public interface QuoteRepository extends JpaRepository<Quote, Long> {
    // Metode custom
}
