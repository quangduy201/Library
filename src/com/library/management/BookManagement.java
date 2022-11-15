package com.library.management;

import com.library.component.Book;
import com.library.component.Dictionary;
import com.library.component.EducationBook;
import com.library.component.ReferenceBook;
import com.library.util.Day;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class BookManagement implements Management, File {
    private Book[] books;
    private static String bookMenu = """
            1. Education Book
            2. Reference Book
            3. Dictionary
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
        writeFile();
    }

    @Override
    public void output() {
        if (books.length == 0) {
            System.out.println("There are no books yet.");
            return;
        }
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
        writeFile();
    }

    @Override
    public void edit() {
        int id = Book.inputId("Enter ID: ");
        Book book = findBook(id);
        if (book == null)
            System.out.println("Book not found!");
        else {
            String temp = "";
            System.out.printf("   ID  %15s  NAME  %13s  REMAIN      PRICE  %4s  PUBLISH DAY  %14s  NOTE\n", temp, temp, temp, temp);
            book.output();
            System.out.println("\t\tEdit Book");
            book.input();
            writeFile();
        }
    }

    @Override
    public void remove() {
        int id = Book.inputId("Enter ID: ");
        boolean isRemoved = false;
        for (int i = 0; i < books.length; i++) {
            if (books[i].getId() == id) {   // found the book
                for (int j = i; j < books.length; j++)
                    books[j] = books[j + 1];
                books = Arrays.copyOf(books, books.length - 1);
                isRemoved = true;
                break;
            }
        }
        if (isRemoved) {
            System.out.println("Book removed!");
            writeFile();
        }
        else
            System.out.println("Book not found!");
    }

    @Override
    public void find() {
        Scanner sc = new Scanner(System.in);
        String choice;
        Book[] book = new Book[1];

        do {
            System.out.println("""
                    1. Find Book by ID.
                    2. Find Book by name.
                    3. Find Book between low price and high price
                    """);
            System.out.print("Enter your choice: ");
            choice = sc.nextLine();
            if (choice.equals("1")) {   // Find Book by ID
                int id = Book.inputId("Enter ID: ");
                book[0] = findBook(id);
                break;
            }
            if (choice.equals("2")) {   // Find Book by name
                String name = Book.inputName("Enter name: ");
                book[0] = findBook(name);
                break;
            }
            if (choice.equals("3")) {   // Find Book between prices
                double low = Book.inputPrice("Enter low price");
                double high = Book.inputPrice("Enter high price");
                book = findBook(low, high);
                break;
            }
        } while (true);

        if (book.length == 0 || book[0] == null) // if find between prices not found || find by ID/name not found
            System.out.println("Book not found!");
        else {
            String temp = "";
            System.out.printf("   ID %17s NAME %15s REMAIN      PRICE %6s PUBLISH DAY %16s NOTE\n", temp, temp, temp, temp);
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

    @Override
    public void readFile() {
        try {
            FileReader file = new FileReader("res\\books.dat");
            BufferedReader reader = new BufferedReader(file);
            String strLine;
            while ((strLine = reader.readLine()) != null)
                convertToObject(strLine);
            reader.close();
        } catch (Exception ignored) {}
    }

    @Override
    public void writeFile() {
        try {
            FileWriter file = new FileWriter("res\\books.dat");
            BufferedWriter writer = new BufferedWriter(file);
            for (Book book : books)
                writer.write(book.toString() + "\n");
            writer.close();
        } catch (Exception ignored) {}
    }

    @Override
    public void convertToObject(String line) {
        String[] object = line.split(", ");
        Book book = null;

        int id = Integer.parseInt(object[1]);
        String name = object[2];
        int remain = Integer.parseInt(object[3]);
        double price = Double.parseDouble(object[4]);
        Day publishDay = Day.parseDay(object[5]);
        if (object[0].equals("EDU")) {
            String publisher = object[6];
            book = new EducationBook(id, name, remain, price, publishDay, publisher);
        }
        if (object[0].equals("REF")) {
            String author = object[6];
            String translator = object[7];
            book = new ReferenceBook(id, name, remain, price, publishDay, author, translator);
        }
        if (object[0].equals("DIC")) {
            String language = object[6];
            book = new Dictionary(id, name, remain, price, publishDay, language);
        }
        books = Arrays.copyOf(books, books.length + 1);
        books[books.length - 1] = book;
    }
}
