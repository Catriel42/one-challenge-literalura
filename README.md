# 📚 Literalura Challenge

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apache-maven&logoColor=white)

**Literalura** is a console-based book management application developed as part of the ONE (Oracle Next Education) challenge. It interacts with the [Gutendex API](https://gutendex.com/) to search for books, retrieve their metadata, and store them in a local PostgreSQL database for persistent access and advanced querying.

## ✨ Features

- **📖 Search & Save**: Search for books by title or author via the Gutendex API and save them automatically to the database.
- **📚 Library Catalog**: List all books currently stored in your local library.
- **✍️ Author Management**: List all registered authors.
- **📅 Historical Analysis**: Find authors who were alive in a specific year.
- **🌍 Language Filter**: Filter books by their language (e.g., English, Spanish, French).

## 🛠️ Tech Stack

- **Java 21**: Core programming language.
- **Spring Boot 3**: Framework for rapid application development.
- **Spring Data JPA**: For ORM and database interactions.
- **PostgreSQL**: Relational database management system.
- **Gutendex API**: External API for fetching book data.
- **Lombok**: Boilerplate code reduction.

## 🚀 Getting Started

### Prerequisites

- Java 21 SDK
- Maven
- PostgreSQL installed and running

### Installation

1.  **Clone the repository**
    ```bash
    git clone https://github.com/Catriel42/one-challenge-literalura.git
    cd one-challenge-literalura
    ```

2.  **Database Setup**
    Create a PostgreSQL database named `literalura` (or whatever you prefer, just update the env vars).

3.  **Environment Configuration**
    This application uses environment variables for database configuration. You can set them in your IDE or export them in your terminal:

    ```bash
    export DB_URL=jdbc:postgresql://localhost:5432/literalura
    export DB_USER=your_postgres_user
    export DB_PASS=your_postgres_password
    export DB_DIALECT=org.hibernate.dialect.PostgreSQLDialect
    export DB_DDL_AUTO=update
    ```

4.  **Run the Application**
    ```bash
    mvn spring-boot:run
    ```

## 🎮 Usage

Once the application is running, you will be presented with a console menu:

```text
Bievenido a Literalura

Por favor selecciona una opción:
1. Buscar libros por título y/o autor
2. Ver los libros registrados en la biblioteca literalura
3. Ver los autores registrados en la biblioteca literalura
4. Ver los autores vivos en determinado año
5. Ver libros por lenguaje
0. Salir
```

Select an option by entering the corresponding number.

## 📂 Class Diagram

A PlantUML class diagram (`literalura.puml`) is included in the project root to visualize the architecture.

![Class Diagram](/src/main/resources/literalurav2.png)

## Demo!

https://github.com/user-attachments/assets/0f9ae683-f17e-49cd-bba3-11ea7003cb47

## 📝 License

This project is part of the Alura / Oracle Next Education education program.

---
Made with ❤️ by [Catriel42](https://github.com/Catriel42)
