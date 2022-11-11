package com.library.management;

import com.library.component.Book;
import com.library.component.Dictionary;
import com.library.component.EducationBook;
import com.library.component.ReferenceBook;

import java.util.Arrays;
import java.util.Scanner;

public class BookManagement implements Management {
    private Book[] books;
    private static String bookMenu = """
            1. Education Book
            2. Reference Book
            3. Dictionary
            """;
    private static String findMenu = """
            1. Find Book by ID
            2. Find Book by name
            3. Find Book between prices
            """;
    public BookManagement() {
        this.books = new Book[0];
    }

    public Book[] getBooks() {
        return books;
    }

    public void setBooks(Book[] books) {
        this.books = books;
    }

    @Override
    public void input() {
        Scanner sc = new Scanner(System.in);
        String choice, input;
        int n = 0;
        boolean hasError;
        do {
            hasError = false;
            System.out.print("Enter number of books: ");
            input = sc.nextLine();
            try {
                n = Integer.parseInt(input);
            } catch (Exception e) {
                hasError = true;
            }
        } while (hasError || n <= 0);

        books = new Book[n];
        for (int i = 0; i < n; i++) {
            do {
                books[i] = null;
                System.out.println("\t\t\tBOOK " + (i + 1));
                System.out.println(bookMenu);
                System.out.print("Enter your choice: ");
                choice = sc.nextLine();
                if (choice.equals("1"))
                    books[i] = new EducationBook();
                if (choice.equals("2"))
                    books[i] = new ReferenceBook();
                if (choice.equals("3"))
                    books[i] = new Dictionary();
            } while (books[i] == null);
            books[i].input();
        }
    }

    @Override
    public void output() {
        String temp = "";
        System.out.printf("   ID  %15s  NAME  %13s  REMAIN      PRICE  %4s  PUBLISH DAY  %14s  NOTE\n", temp, temp, temp, temp);
        for (Book book : books)
            book.output();
    }

    @Override
    public void add() {
        Scanner sc = new Scanner(System.in);
        String choice, input;
        int k = 0, n = books.length;
        boolean hasError;
        do {
            hasError = false;
            System.out.print("Enter number of books: ");
            input = sc.nextLine();
            try {
                k = Integer.parseInt(input);
            } catch (Exception e) {
                hasError = true;
            }
        } while (hasError || k <= 0);

        books = Arrays.copyOf(books, books.length + k);
        for (int i = n; i < books.length; i++) {
            do {
                books[i] = null;
                System.out.println("\t\t\tBOOK " + (i - n + 1));
                System.out.println(bookMenu);
                System.out.print("Enter your choice: ");
                choice = sc.nextLine();
                if (choice.equals("1"))
                    books[i] = new EducationBook();
                if (choice.equals("2"))
                    books[i] = new ReferenceBook();
                if (choice.equals("3"))
                    books[i] = new Dictionary();
            } while (books[i] == null);
            books[i].input();
        }
    }

    @Override
    public void edit() {
        Scanner sc = new Scanner(System.in);
        String input;
        int id = 0;
        boolean hasError;
        Book book;

        do {
            hasError = false;
            System.out.print("Enter ID: ");
            input = sc.nextLine();
            try {
                id = Integer.parseInt(input);
            } catch (Exception e) {
                hasError = true;
            }
        } while (hasError || input.length() != 4 || id < 0);

        book = findBook(id);
        if (book == null)
            System.out.println("Book not found!");
        else
            book.output();
        book.input();
    }

    @Override
    public void remove() {
        Scanner sc = new Scanner(System.in);
        String input;
        int id = 0;
        boolean hasError;

        do {
            hasError = false;
            System.out.print("Enter ID: ");
            input = sc.nextLine();
            try {
                id = Integer.parseInt(input);
            } catch (Exception e) {
                hasError = true;
            }
        } while (hasError || input.length() != 4 || id < 0);

        boolean isRemoved = false;
        for (int i = 0; i < books.length; i++) {
            if (books[i].getId() == id) {
                for (int j = i; j < books.length; j++)
                    books[j] = books[j + 1];
                Arrays.copyOf(books, books.length - 1);
                isRemoved = true;
                break;
            }
        }
        if (isRemoved)
            System.out.println("Book removed!");
        else
            System.out.println("Book not found!");
    }

    @Override
    public void find() {
        Scanner sc = new Scanner(System.in);
        String choice, input;
        int inputToInt = 0;
        double low = 0.0, high = 0.0;
        boolean hasError;
        Book[] book = new Book[1];

        do {
            System.out.println(findMenu);
            System.out.print("Enter your choice: ");
            choice = sc.nextLine();
            if (choice.equals("1")) {   // Find Book by ID
                do {
                    hasError = false;
                    System.out.print("Enter ID: ");
                    input = sc.nextLine();
                    try {
                        inputToInt = Integer.parseInt(input);
                    } catch (Exception e) {
                        hasError = true;
                    }
                } while (hasError || input.length() != 4 || inputToInt < 0);

                book[0] = findBook(inputToInt);
                break;
            }
            if (choice.equals("2")) {   // Find Book by name
                do {
                    System.out.print("Enter name: ");
                    input = sc.nextLine();
                } while (input.isBlank());

                book[0] = findBook(input);
                break;
            }
            if (choice.equals("3")) {   // Find Book between prices
                do {
                    hasError = false;
                    System.out.print("Enter low price");
                    input = sc.nextLine();
                    try {
                        low = Double.parseDouble(input);
                    } catch (Exception e) {
                        hasError = true;
                    }
                } while (hasError || low < 0.0);

                do {
                    hasError = false;
                    System.out.print("Enter high price");
                    input = sc.nextLine();
                    try {
                        high = Double.parseDouble(input);
                    } catch (Exception e) {
                        hasError = true;
                    }
                } while (hasError || high < 0.0);

                book = findBook(low, high);
                break;
            }
        } while (true);

        if (book.length == 0 || book[0] == null) // if find between prices not found || find by ID/name not found
            System.out.println("Book not found!");
        else {
            String temp = "";
            System.out.printf("   ID  %15s  NAME  %13s  REMAIN      PRICE  %4s  PUBLISH DAY  %14s  NOTE\n", temp, temp, temp, temp);
            for (Book b : book)
                b.output();
        }
    }

    public Book findBook(int id) {
        for (Book book : books)
            if (book.getId() == id)
                return book;
        return null;
    }

    public Book findBook(String name) {
        for (Book book : books)
            if (book.getName().equals(name))
                return book;
        return null;
    }

    public Book[] findBook(double low, double high) {
        Book[] booksFound = new Book[0];
        for (Book book : books) {
            if (book.getPrice() >= low && book.getPrice() <= high) {
                booksFound = Arrays.copyOf(booksFound, booksFound.length + 1);
                booksFound[booksFound.length - 1] = book; // if found, add the book to array
            }
        }
        return booksFound;
    }

    @Override
    public void statistic() {

    }
}
