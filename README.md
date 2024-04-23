# QuoteSphere

*Inspiră-te zilnic cu cele mai frumoase citate*

QuoteSphere este o aplicație care oferă utilizatorilor lor o doză zilnică de inspirație prin citate alese aleatoriu. Folosind tehnologii de vârf precum Java, Spring Boot pentru backend, și Node.js - Next.js pentru frontend, împreună cu o bază de date PostgreSQL, aplicația garantează o experiență fluentă și captivantă. Fiecare componentă este găzduită pe platforme de încredere, precum Render.com, Vercel.com, și Supabase.com, cu gestionarea serviciilor CDN, DNS și securitate asigurată de Cloudflare.com. QuoteSphere invită contribuțiile comunității și promite să ofere un flux continuu de inspirație prin intermediul unui design simplu și atrăgător.

[Link-ul public al aplicației](https://quotes.fadevox.com/)

## Tehnologii utilizate:

### Backend:

- Java, Spring Boot (Hostat pe [Render.com](https://render.com/))

### Frontend:

- Node.js, Next.js 14, Axios (Hostat pe [Vercel.com](https://vercel.com/))

### Baza de date:

- PostgreSQL (Hostat pe [Supabase.com](https://supabase.com/)) - Populată la data de 29 Februarie 2024 cu 1382 de citate din diverse categorii.

### CDN & DNS & Security:

- [Cloudflare.com](https://cloudflare.com)

### Framework CSS:

- [TailwindCSS](https://tailwindcss.com/)

## Structura proiectului:

### Backend:

- `src/main/java/com.fadevox.quoteapp`:
  - `controller`: Controlere REST pentru gestionarea cererilor HTTP.
  - `service`: Clase de servicii pentru logică.
  - `repository`: Interfețe de depozit pentru interacțiunea cu baza de date.
  - `model`: Clase de entități reprezentând structuri de date.
  - `exception`: Excepție personalizată.
- `QuotesappApplication.java`: Controller-ul principal.

### Frontend:

- `app`: Conține fișierele pentru randarea layout-ului și landing page-ului.
- `public`: Conține resursele publice ale proiectului.
- `services`: Conține fișierele pentru preluarea datelor din REST API.
- Restul fișierelor de configurare din Next.js 14.

## Funcționalități:

- Afișarea unui citat ales aleator din baza de date.
- Adăugare de noi citate (automat).

## Licență:

Acest proiect este licențiat sub licența GPL-3.0.

## Utilizare:

1. Accesați adresa URL [https://quotes.fadevox.com/](https://quotes.fadevox.com/) în browserul dvs. web.
2. Veți vedea interfața grafică ce afișează un citat aleator.

## Notă:

Această aplicație este un exemplu simplu și poate fi extinsă cu ușurință pentru a include mai multe funcționalități.

## Contribuții:

Sunt binevenite contribuțiile la acest proiect. Puteți trimite pull request-uri pe GitHub (urmează a fi făcut public codul sursă).

## Mulțumiri:

- Proiectul Spring Boot
- Proiectul Next.js
- Comunitatea Java
- Comunitatea Node.js
