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

    public boolean idExists(int id) {
        for (Book book : books)
            if (book.getStatus().equals("enabled") && id == book.getId())
                return true;
        return false;
    }

    @Override
    public void input() {
        Scanner sc = new Scanner(System.in);
        String choice, input;
        int n = 0;
        boolean hasError;

        do {
            System.out.println("""
                    Inputting books will overwrite all of the books.
                    All existing books will be lost.
                    \tDo you want to continue?
                    1. Yes
                    2. No
                    """);
            System.out.print("Enter your choice: ");
            choice = sc.nextLine();
            if (choice.equals("1"))
                break;
            if (choice.equals("2"))
                return;
        } while (true);

        do {
            hasError = false;
            System.out.print("Enter number of books: ");
            input = sc.nextLine();
            try {
                n = Integer.parseInt(input);
            } catch (Exception e) {
                hasError = true;
            }
        } while (hasError || n < 0);

        Book[] newBooks = new Book[n];
        books = new Book[0];
        for (int i = 0; i < n; i++) {
            do {
                newBooks[i] = null;
                System.out.println("\t\t\tBOOK " + (i + 1));
                System.out.println(bookMenu);
                System.out.print("Enter your choice: ");
                choice = sc.nextLine();
                if (choice.equals("1"))
                    newBooks[i] = new EducationBook();
                if (choice.equals("2"))
                    newBooks[i] = new ReferenceBook();
                if (choice.equals("3"))
                    newBooks[i] = new Dictionary();
            } while (newBooks[i] == null);
            newBooks[i].input();
            books = Arrays.copyOf(books, books.length + 1);
            books[i] = newBooks[i];
        }
        writeFile(books, false);
    }

    @Override
    public void output() {
        boolean hasBooks = false;
        for (Book book : books) {
            if (book.getStatus().equals("enabled")) {
                hasBooks = true;
                break;
            }
        }
        if (!hasBooks) {
            System.out.println("There are no books yet.");
            return;
        }
        System.out.printf("   ID  %15s  NAME  %13s  REMAIN      PRICE  %4s  PUBLISH DAY  %14s  NOTE\n", "", "", "", "");
        for (Book book : books)
            if (book.getStatus().equals("enabled"))
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
        } while (hasError || k < 0);

        Book[] newBooks = new Book[k];
        for (int i = 0; i < k; i++) {
            do {
                newBooks[i] = null;
                System.out.println("\t\t\tBOOK " + (i + 1));
                System.out.println(bookMenu);
                System.out.print("Enter your choice: ");
                choice = sc.nextLine();
                if (choice.equals("1"))
                    newBooks[i] = new EducationBook();
                if (choice.equals("2"))
                    newBooks[i] = new ReferenceBook();
                if (choice.equals("3"))
                    newBooks[i] = new Dictionary();
            } while (newBooks[i] == null);
            newBooks[i].input();
            books = Arrays.copyOf(books, books.length + 1);
            books[i + n] = newBooks[i];
        }
        writeFile(newBooks, true);
    }

    @Override
    public void edit() {
        Scanner sc = new Scanner(System.in);
        int id = Book.inputId("Enter ID (4 digits): ");
        Book book = findBook(id);
        if (book == null)
            System.out.println("Book not found!");
        else {
            System.out.printf("   ID  %15s  NAME  %13s  REMAIN      PRICE  %4s  PUBLISH DAY  %14s  NOTE\n", "", "", "", "");
            book.output();
            do {
                System.out.println("""
                        \n\tEdit?
                        1. Yes
                        2. No
                        """);
                System.out.print("Enter your choice: ");
                String choice = sc.nextLine();
                if (choice.equals("1")) {
                    book.setId(0); // reset ID to be able to edit ID
                    book.input();
                    System.out.println("Book edited!");
                    writeFile(books, false);
                    break;
                }
                if (choice.equals("2"))
                    break;
            } while (true);
        }
    }

    @Override
    public void remove() {
        Scanner sc = new Scanner(System.in);
        int id = Book.inputId("Enter ID (4 digits): ");
        Book book = findBook(id);
        if (book == null)
            System.out.println("Book not found!");
        else {
            System.out.printf("   ID  %15s  NAME  %13s  REMAIN      PRICE  %4s  PUBLISH DAY  %14s  NOTE\n", "", "", "", "");
            book.output();
            do {
                System.out.println("""
                            \n\tRemove?
                            1. Yes
                            2. No
                            """);
                System.out.print("Enter your choice: ");
                String choice = sc.nextLine();
                if (choice.equals("1")) {
                    book.setStatus("disabled");
                    System.out.println("Book removed!");
                    writeFile(books, false);
                    break;
                }
                if (choice.equals("2"))
                    break;
            } while (true);
        }
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
                int id = Book.inputId("Enter ID (4 digits): ");
                book[0] = findBook(id);
                break;
            }
            if (choice.equals("2")) {   // Find Book by name
                String name = Book.inputName("Enter name: ");
                book[0] = findBook(name);
                break;
            }
            if (choice.equals("3")) {   // Find Book between prices
                double low = Book.inputPrice("Enter low price: ");
                double high = Book.inputPrice("Enter high price: ");
                book = findBook(low, high);
                break;
            }
        } while (true);

        if (book.length == 0 || book[0] == null) // if (find between prices not found || find by ID/name not found)
            System.out.println("Book not found!");
        else {
            System.out.printf("   ID %17s NAME %15s REMAIN      PRICE %6s PUBLISH DAY %16s NOTE\n", "", "", "", "");
            for (Book b : book)
                b.output();
        }
    }

    public Book findBook(int id) {
        for (Book book : books)
            if (book.getStatus().equals("enabled") && book.getId() == id)
                return book;
        return null;
    }

    public Book findBook(String name) {
        for (Book book : books)
            if (book.getStatus().equals("enabled") && book.getName().equalsIgnoreCase(name))
                return book;
        return null;
    }

    public Book[] findBook(double low, double high) {
        Book[] booksFound = new Book[0];
        for (Book book : books) {
            if (book.getStatus().equals("enabled") && book.getPrice() >= low && book.getPrice() <= high) {
                booksFound = Arrays.copyOf(booksFound, booksFound.length + 1);
                booksFound[booksFound.length - 1] = book; // if found, add the book to array
            }
        }
        return booksFound;
    }

    @Override
    public void statistic() {
        /*
        Th???ng k?? s??? l?????ng s??ch theo t???ng th??? lo???i
        T??nh ph???n tr??m s??? l?????ng m???i th??? lo???i so v???i t???ng s??? l?????ng s??ch
         */
        int n = 0;
        int edu = 0, eduRemain = 0;
        int ref = 0, refRemain = 0;
        int dic = 0, dicRemain = 0;
        int all = 0, remain;
        for (Book book : books) {
            if (book.getStatus().equals("disabled"))
                continue;
            n++;
            remain = book.getRemain();
            if (book instanceof EducationBook) {
                edu++;
                eduRemain += remain;
            }
            if (book instanceof ReferenceBook) {
                ref++;
                refRemain += remain;
            }
            if (book instanceof Dictionary) {
                dic++;
                dicRemain += remain;
            }
            all += remain;
        }
        System.out.printf("Number of books: %d\n", n);
        System.out.printf("Education books: %d\t--> %.2f%%\n", edu, edu * 100.0 / n);
        System.out.printf("Reference books: %d\t--> %.2f%%\n", ref, ref * 100.0 / n);
        System.out.printf("   Dictionaries: %d\t--> %.2f%%\n", dic, dic * 100.0 / n);
        System.out.printf("\nNumber of remain books: %d\n", all);
        System.out.printf("Remain education books: %d\t--> %.2f%%\n", eduRemain, eduRemain * 100.0 / all);
        System.out.printf("Remain reference books: %d\t--> %.2f%%\n", refRemain, refRemain * 100.0 / all);
        System.out.printf("   Remain dictionaries: %d\t--> %.2f%%\n", dicRemain, dicRemain * 100.0 / all);
    }

    @Override
    public void readFile() {
        try {
            FileReader file = new FileReader("data\\books.txt");
            BufferedReader reader = new BufferedReader(file);
            String strLine;
            while ((strLine = reader.readLine()) != null)
                convertToObject(strLine);
            reader.close();
        } catch (Exception ignored) {}
    }

    @Override
    public void writeFile(Object[] objects, boolean append) {
        try {
            FileWriter file = new FileWriter("data\\books.txt", append);
            BufferedWriter writer = new BufferedWriter(file);
            for (Object book : objects)
                writer.write(book.toString() + "\n");
            writer.close();
        } catch (Exception ignored) {}
    }

    @Override
    public void convertToObject(String line) throws Exception {
        String[] object = line.split(", ");
        Book book = null;

        String status = object[1];
        int id = Integer.parseInt(object[2]);
        String name = object[3];
        int remain = Integer.parseInt(object[4]);
        double price = Double.parseDouble(object[5]);
        Day publishDay = Day.parseDay(object[6]);
        if (object[0].equals("EDU")) {
            String publisher = object[7];
            book = new EducationBook(status, id, name, remain, price, publishDay, publisher);
        }
        if (object[0].equals("REF")) {
            String author = object[7];
            book = new ReferenceBook(status, id, name, remain, price, publishDay, author);
        }
        if (object[0].equals("DIC")) {
            String language = object[7];
            book = new Dictionary(status, id, name, remain, price, publishDay, language);
        }
        books = Arrays.copyOf(books, books.length + 1);
        books[books.length - 1] = book;
    }
}
