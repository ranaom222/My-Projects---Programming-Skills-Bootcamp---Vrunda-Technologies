

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in); // scanner class object to take user input

    static String takeInput(String toPrint) {
        System.out.print(toPrint);
        String input;
        input = sc.next(); // using next() instead of nextLine() to prevent not able to enter multiple nextLine() values because of pressing enter multiple times
        return input;
    }

    public static void main(String[] args) {
        List<Borrower> users = new ArrayList<>();
        Catalog catalog = new Catalog();

        // initially adding books
        catalog.addBook(new Book("Bhagavad Gita", "Vyasa", "Holy book of Hindus"));
        catalog.addBook(new Book("Cryptography and Network Security", "William Stallings", "Cryptography & Information Security"));
        catalog.addBook(new Book("Internet of Things", "Arshdeep Bahga", "IoT"));

        int choice = 0;
        do {
            System.out.println(
                    "\n------------------------------\n1. Add Book\n2. Remove Book\n3. Search Book\n4. List All Books\n5. Update a book\n6. Add user\n7. Remove user\n8. Get all borrowed books of yours\n9. Borrow a book\n10. Return a book\n------------------------------\n");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1: // add book
                    String bookName = takeInput("Enter book name: ");
                    String authorName = takeInput("Enter book author name: ");
                    String genre = takeInput("Enter genre of the book: ");
                    catalog.addBook(new Book(bookName, authorName, genre));
                    System.out.println("Book added successfully !!");
                    break;

                case 2: // remove book
                    System.out.println(catalog.getAllBooks());
                    System.out.println("Enter index of book to remove it: ");
                    int idx = sc.nextInt();
                    Book removed = catalog.getBook(idx);
                    catalog.removeBook(catalog.getBook(idx));
                    System.out.println("Book removed: ");
                    System.out.println(removed.toString());
                    break;

                case 3: // Search Book
                    String keyword = takeInput("Enter any book name, author name or genre of book: ");
                    System.out.println(catalog.searchBook(keyword));
                    break;

                case 4: // List all books
                    System.out.println(catalog.getAllBooks());
                    break;

                case 5: // update a book
                    System.out.println(catalog.getAllBooks());
                    System.out.println("Enter book number from above: ");
                    idx = sc.nextInt();
                    Book toUpdate = catalog.getBook(idx);
                    String newTitle = takeInput("Enter new title of the book: ");
                    String newAuthor = takeInput("Enter author of the book: ");
                    String newGenre = takeInput("Enter genre of the book: ");
                    catalog.updateBook(toUpdate, newTitle, newAuthor, newGenre, toUpdate.getAvailability());
                    System.out.println("Book updated !!");
                    break;

                case 6: // Add users
                    String name = takeInput("Enter your name: ");

                    Borrower newUser = new Borrower(name);

                    if (!users.contains(newUser)) {
                        users.add(newUser);
                        System.out.println("User added successfully !!");
                    } else {
                        System.out.println("User already exists !!");
                    }
                    break;

                case 7: // Remove user
                    name = takeInput("Enter username to remove: ");
                    Boolean isFound = false;

                    /*
                     * Exception handling save the program ;)
                     */
                    try{
                        for(Borrower b: users){
                            if(b.getName().equals(name)){
                                users.remove(b);
                                isFound = true;
                                System.out.println("User removed successfully !!");
                            }
                        }    
                    } catch(Exception e){}
                    finally{
                        if(!isFound){
                            System.out.println("User not found !!");
                        }
                    }

                    break;

                case 8: // get all borrowed books
                    name = takeInput("Enter username to get your borrowed books list: ");

                    for (Borrower b : users) {
                        if (b.getName().equals(name)) {
                            boolean isAnyBookThere = false;
                            System.out.print("Borrowed books: ");
                            for(BorrowedBook currBorrowedBook: b.getBorrowedBooks()){
                                System.out.println(currBorrowedBook.getBookInfo().getTitle());
                                isAnyBookThere = true;
                            }
                            if(!isAnyBookThere){ System.out.println("No books borrowed !!"); }
                            System.out.println();
                        }
                    }
                    break;
                
                case 9: // borrow a book
                    name = takeInput("Enter your username: ");
                    for (Borrower b : users) {
                        if (b.getName().equals(name)) {
                            System.out.println(catalog.getAllBooks());
                            System.out.println("Enter book number from above: ");
                            idx = sc.nextInt();
                            Book book = catalog.getBook(idx);
                            
                            b.borrowBook(book, LocalDate.now().plusDays(7));
                        }
                    }
                    break;
                
                case 10: // return book
                name = takeInput("Enter your username: ");
                for (Borrower b : users) {
                    if (b.getName().equals(name)) {
                        for(BorrowedBook currBorrowedBook: b.getBorrowedBooks()){
                            System.out.print(currBorrowedBook.getBookInfo().getTitle() + ", ");
                        }
                        System.out.println("Enter book number from above: ");
                        idx = sc.nextInt();
                        BorrowedBook borrowedBook = b.getBorrowedBook(idx);
                        
                        borrowedBook.getBookInfo().changeAvailability(true);
                        b.returnBook(borrowedBook);
                    }
                }
                break;

            }
        } while (choice != 0);
    }
}
