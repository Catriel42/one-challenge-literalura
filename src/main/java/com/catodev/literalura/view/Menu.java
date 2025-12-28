package com.catodev.literalura.view;

import com.catodev.literalura.controller.BookController;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class Menu {
    private final BookController bookController;
    private final Scanner scanner = new Scanner(System.in);

    public Menu(BookController bookController) {
        this.bookController = bookController;
    }

    public void showMenu() {
        System.out.println("\nWelcome to Literalura, please choose an option:");
		while (true) {
            System.out.println("\nPlease choose an option:");
            System.out.println("1. Search books by title");
            System.out.println("0. Exit");
            System.out.print("Your choice: ");
            String option = scanner.nextLine();

            switch (option) {
                case "1":
                    System.out.print("Enter the book title: ");
                    String query = scanner.nextLine();
                    try {
                        String result = bookController.getSearchBooks(query, 1);
                        System.out.println("\nSearch results:");
                        System.out.println(result);
                        System.out.println("Libro y autor guardado en la base de datos!");
                    } catch (Exception e) {
                        System.err.println("An error occurred while fetching books: " + e.getMessage());
                    }
                    break;
                case "0":
                    System.out.println("Exiting application.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }
}