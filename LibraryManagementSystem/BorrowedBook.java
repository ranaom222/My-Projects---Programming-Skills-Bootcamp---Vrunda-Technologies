
import java.time.LocalDate;

public class BorrowedBook {
    private Book book;
    private Borrower borrower;
    private LocalDate dueDate;

    // parameterized constructor to initialize the properties of this class
    BorrowedBook(Book book, Borrower borrower, LocalDate dueDate) {
        this.book = book;
        this.borrower = borrower;
        this.dueDate = dueDate;
    }

    /*
     * @method: getBookInfo()
     * 
     * @purpose: returns the borrowed book class object
     */
    public Book getBookInfo() {
        return book;
    }

    /*
     * @method: getBorrowerInfo()
     * 
     * @purpose: returns the borrower class object
     */
    public Borrower getBorrowerInfo() {
        return borrower;
    }

    /*
     * @method: getDueDate()
     * 
     * @purpose: returns the due date to return the book
     */
    public LocalDate getDueDate() {
        return dueDate;
    }
}
