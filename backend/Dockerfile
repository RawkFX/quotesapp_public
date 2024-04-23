# Foloseste Ubuntu ca imagine de baza
FROM ubuntu:latest

# Seteaza directorul de lucru in container
WORKDIR /app

# Instaleaza Java 17 si Maven
RUN apt-get update && \
    apt-get install -y openjdk-17-jdk maven

# Copiaza fisierele sursa pentru a construi proiectul
COPY . .

# Construieste proiectul
RUN mvn clean package -DskipTests

# Expune portul 8080
EXPOSE 8080

# Comanda de rulare a aplicatiei
CMD ["java", "-jar", "target/quotesapp-0.1.1.jar"]
