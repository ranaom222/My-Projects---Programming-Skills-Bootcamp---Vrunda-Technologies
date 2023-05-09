
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class Borrower {
    private String name;
    private List<BorrowedBook> borrowedBooks;

    Borrower(String name) {
        this.name = name;
        borrowedBooks = new ArrayList<BorrowedBook>();
    }

    /*
     * @method: returns the books borrowed by this borrower
     */
    public List<BorrowedBook> getBorrowedBooks() {
        return borrowedBooks;
    }

    /*
     * @method: returns the borrowedBook present at the idx
     */
    public BorrowedBook getBorrowedBook(int idx){
        return borrowedBooks.get(idx);
    }

    /*
     * @method: returns the name of the borrower
     */
    public String getName() {
        return name;
    }

    /*
     * @method: borrows a book if available and adds it in the borrowedBooks list
     * 
     * @params: Book class object and LocalDate class object
     */
    public void borrowBook(Book book, LocalDate dueDate) {
        if (book.getAvailability()) {
            book.changeAvailability(false);
            borrowedBooks.add(new BorrowedBook(book, this, dueDate));
            System.out.println("Book borrowed successfully !!");
        } else {
            System.out.println("Book is not available to borrow !!");
        }
    }

    /*
     * @method: returns the borrwed book
     * 
     * @params: BorrowedBook class object
     */    
    public void returnBook(BorrowedBook borrowedBook) {
        Book book = borrowedBook.getBookInfo();
        if (borrowedBooks.contains(borrowedBook)) {
            book.changeAvailability(true); // changing availability of book to true as this borrower is returning the book
            borrowedBooks.remove(borrowedBook);
            System.out.println("Book returned successfully !!");
        } else {
            System.out.println("Book is not borrowed by you !! ");
        }
    }

}
