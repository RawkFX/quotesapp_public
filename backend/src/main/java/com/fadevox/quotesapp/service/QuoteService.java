package com.fadevox.quotesapp.service;

// Java imports
import java.util.List;
import java.util.Random;

// Spring framework imports
import com.fadevox.quotesapp.exception.CustomException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

// Custom imports
import com.fadevox.quotesapp.model.Quote;
import com.fadevox.quotesapp.repository.QuoteRepository;

@Service
public class QuoteService {
    // Constructorul clasei QuoteService
    private final QuoteRepository quoteRepository;
    // Constante

    private final String API_NINJAS_URL;
    private final String API_DEEPL_URL;
    private final String API_DEEPL_KEY;
    private final String API_NINJAS_KEY;

    {
        API_NINJAS_URL = "https://api.api-ninjas.com/v1/quotes";
        API_DEEPL_URL = "https://api-free.deepl.com/v2/translate";
        API_DEEPL_KEY = "91b2461e-43dd-4df5-b6fb-080b329fc595:fx";
        API_NINJAS_KEY = "u/Up7kMqWn2eztpLQbkJaw==Vu5nU1NrgSONNTiJ";
    }

    // Constructorul clasei QuoteService
    public QuoteService(QuoteRepository quoteRepository) {
        this.quoteRepository = quoteRepository;
    }

    // Metoda care returneaza un citat random din baza de date
    public Quote getRandomQuote() {
        // Cream o lista de citate ce contine toate citatele din baza de date
        List<Quote> allQuotes = quoteRepository.findAll();

        // Returnam un citat random din lista de citate sau null daca lista este goala
        return !allQuotes.isEmpty() ? allQuotes.get(new Random().nextInt(allQuotes.size())) : null;
    }

    // Metoda care returneaza primul citat din baza de date
    public Quote getFirstQuote() {
        // Cream o lista de citate ce contine toate citatele din baza de date
        List<Quote> allQuotes = quoteRepository.findAll();

        // Returnam primul citat din lista de citate sau null daca lista este goala
        return !allQuotes.isEmpty() ? allQuotes.get(0) : null;
    }

    // Metoda care salveaza un citat random in baza de date si il returneaza pentru a fi afisat
    // Am marcat metoda ca @Transactional pentru a crea o singura tranzactie in baza de date
    @Transactional
    public Quote storeRandomQuote() {
        // Cream un obiect de tip HttpHeaders pentru a seta header-ul "X-Api-Key" si "Content-Type" in request-ul catre API-ul de la API-Ninjas
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Api-Key", API_NINJAS_KEY);
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Cream un obiect de tip HttpEntity care contine header-urile setate anterior
        HttpEntity<String> entity = new HttpEntity<>(headers);

        // Realizam un request de tip GET catre API-ul de la API-Ninjas pentru a prelua un citat random
        ResponseEntity<String> response = new RestTemplate().exchange(API_NINJAS_URL, HttpMethod.GET, entity, String.class);

        // Afisam in consola status code-ul si body-ul primit in raspuns
        System.out.println("Response body: " + response.getBody());

        // Verificam daca status code-ul primit in raspuns este unul de succes (2xx)
        if(response.getStatusCode().is2xxSuccessful()) {

            // Creem un obiect de tip ObjectMapper
            ObjectMapper mapper = new ObjectMapper();

            // Try-cath block pentru incercarea de a analiza JSON-ul primit in raspuns
            try {
                // Citim JSON-ul primit in raspuns si il transformam intr-un vector de obiecte de tip Quote
                Quote[] quotes = mapper.readValue(response.getBody(), Quote[].class);

                // Verificam daca vectorul de citate nu este gol si daca are cel putin un element
                if(quotes != null && quotes.length > 0) {
                    // Daca citatul este mai lung de 255 de caractere atunci cream o Exceptie
                    if(quotes[0].getQuote().length() > 255) {
                        // Excecutam din nou functia
                        return storeRandomQuote();
                    }

                    // Traducem citatul si categoria in limba romana
                    String translatedQuote = translateToRomanian(quotes[0].getQuote());
                    String translatedCategory = translateToRomanian(quotes[0].getCategory());

                    // Setam citatul si categoria traduse in obiectul de tip Quote
                    quotes[0].setQuote(translatedQuote);
                    quotes[0].setCategory(translatedCategory);

                    // Salvam citatul in baza de date si il returnam
                    return quoteRepository.save(quotes[0]);
                } else {
                    // Afisam in consola un mesaj de eroare in cazul in care vectorul de citate este gol
                    System.err.println("Failed to fetch quote from the API. Status code: " + response.getStatusCode());
                    // Returnam null
                    return null;
                }

            } catch (Exception e) {
                // Afisam in consola un mesaj de eroare in cazul in care apar erori la analizarea JSON-ului primit
                System.out.println("Failed to parse JSON: " + e.getMessage());
                // Utilizam exceptia creata de noi pentru a afisa erorile
                throw new CustomException(e);
            }

        } else {
            // Afisam in consola un mesaj de eroare in cazul in care status code-ul primit nu este unul de succes
            System.err.println("Failed to fetch quote from the API. Status code: " + response.getStatusCode());
            // Returnam null
            return null;
        }

    }

    // Metoda care traduce un text primit in limba engleza in limba romana
    public String translateToRomanian(String text) {
        // Facem un request de tip GET catre API-ul DeepL pentru a traduce textul primit in limba romana
        ResponseEntity<String> response = new RestTemplate().getForEntity(API_DEEPL_URL + "?auth_key=" + API_DEEPL_KEY + "&text=" + text + "&target_lang=RO", String.class);

        // Creem un obiect de tip ObjectMapper
        ObjectMapper mapper = new ObjectMapper();

        try {
            // Analizam JSON-ul primit din API si il transformam intr-un obiect de tip JsonNode
            JsonNode root = mapper.readTree(response.getBody());
            // Extragem nodul "translations" din JSON-ul primit
            // Exemplu de JSON primit:
            // Root: {"translations":[{"detected_source_language":"EN","text":"Dacă nu vreți ca femeile să facă tot ceea ce trebuie să facă, atunci trebuie să le oferiți hrană, trebuie să le oferiți un adăpost și să le asigurați drepturile fundamentale ale omului."}]}
            JsonNode translations = root.get("translations");

            // Returnam textul tradus din prima intrat a vectorului cu identificatorul "text" sau null daca nu exista niciun text tradus
            return translations != null ? translations.get(0).get("text").asText() : null;

        } catch (Exception e) {
            // In cazul in care apar erori, le afisam in consola prin exceptia creata de noi denumita CustomException
            throw new CustomException(e);
        }
    }

}
