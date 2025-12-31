package com.catodev.literalura.view;

import com.catodev.literalura.controller.AuthorController;
import com.catodev.literalura.controller.BookController;
import com.catodev.literalura.service.BookService;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class Menu {
    private final BookController bookController;
    private final AuthorController authorController;
    private final Scanner scanner = new Scanner(System.in);

    public Menu(BookController bookController,  AuthorController authorController) {
        this.bookController = bookController;
        this.authorController = authorController;
    }

    public void showMenu() {
        System.out.println("\nBievenido a Literalura");
		while (true) {
            System.out.println("\nPor favor selecciona una opción:");
            System.out.println("1. Buscar libros por título y/o autor");
            System.out.println("2. Ver los libros registrados en la biblioteca literalura");
            System.out.println("3. Ver los autores registrados en la biblioteca literalura");
            System.out.println("0. Salir");
            System.out.print("Tu elección: ");
            String option = scanner.nextLine();

            switch (option) {
                case "1":
                    System.out.print("Ingrea el titulo y/o autor: ");
                    String query = scanner.nextLine();
                    try {
                        String result = bookController.getSearchBooks(query, 1);
                        System.out.println("\nResultados encontrados: :");
                        System.out.println(result);
                        System.out.println("Libro y autor guardado en la la biblioteca literalura!");
                    } catch (Exception e) {
                        System.err.println("Ocurrió un error durante la búsqueda: " + e.getMessage());
                    }
                    break;
                case "2":
                    System.out.println("Todos los libros en la biblioteca literalura:");
                    String booksResult = bookController.getLibraryBooks();
                    System.out.println(booksResult);
                    break;
                case "3":
                    System.out.println("Todos los autores en la biblioteca literalura:");
                    String authorResult = authorController.getAuthors();
                    System.out.println(authorResult);
                    break;
                case "0":
                    System.out.println("Saliendo de la aplicación.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opción inválida, por favor intenta de nuevo.");
                    break;
            }
        }
    }
}