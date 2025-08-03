package com.additionalTask;

import java.util.*;

//Book class
class Book {
 int id;
 String title;
 String author;
 boolean isAvailable;

 Book(int id, String title, String author, boolean isAvailable) {
     this.id = id;
     this.title = title;
     this.author = author;
     this.isAvailable = isAvailable;
 }

 void display() {
     String status = isAvailable ? "Available" : "Borrowed";
     System.out.println("ID: " + id + ", Title: " + title + ", Author: " + author + ", Status: " + status);
 }
}

public class LibraryManagementSystem {
 public static void main(String[] args) {
     Scanner sc = new Scanner(System.in);

     // Library books using HashMap
     Map<Integer, Book> library = new HashMap<>();
     library.put(1, new Book(1, "The Alchemist", "Paulo Coelho", true));
     library.put(2, new Book(2, "1984", "George Orwell", true));
     library.put(3, new Book(3, "The Hobbit", "J.R.R. Tolkien", true));
     library.put(4, new Book(4, "To Kill a Mockingbird", "Harper Lee", true));

     // Borrowed books list
     List<Book> borrowedBooks = new ArrayList<>();

     int choice;
     do {
         System.out.println("\n--- Library Menu ---");
         System.out.println("1. View All Books");
         System.out.println("2. Borrow a Book");
         System.out.println("3. View Borrowed Books");
         System.out.println("4. Return a Book");
         System.out.println("0. Exit");
         System.out.print("Enter your choice: ");
         choice = sc.nextInt();

         switch (choice) {

             case 1:
                 System.out.println("\n--- All Books ---");
                 for (Book book : library.values()) {
                     book.display();
                 }
                 break;

             case 2:
                 System.out.print("Enter Book ID to borrow: ");
                 int borrowId = sc.nextInt();
                 Book borrowBook = library.get(borrowId);
                 if (borrowBook != null && borrowBook.isAvailable) {
                     borrowBook.isAvailable = false;
                     borrowedBooks.add(borrowBook);
                     System.out.println("You borrowed: " + borrowBook.title);
                 } else {
                     System.out.println("Book is not available or doesn't exist.");
                 }
                 break;

             case 3:
                 System.out.println("\n--- Borrowed Books ---");
                 if (borrowedBooks.isEmpty()) {
                     System.out.println("No books borrowed.");
                 } else {
                     for (Book b : borrowedBooks) {
                         b.display();
                     }
                 }
                 break;

             case 4:
                 System.out.print("Enter Book ID to return: ");
                 int returnId = sc.nextInt();
                 Book returnBook = library.get(returnId);
                 if (returnBook != null && !returnBook.isAvailable) {
                     returnBook.isAvailable = true;
                     borrowedBooks.removeIf(b -> b.id == returnId);
                     System.out.println("Book returned: " + returnBook.title);
                 } else {
                     System.out.println("Invalid Book ID or book wasn't borrowed.");
                 }
                 break;

             case 0:
                 System.out.println("Thank you for using the Library System.");
                 break;

             default:
                 System.out.println("Invalid choice!");
         }
     } while (choice != 0);

     sc.close();
 }
}

