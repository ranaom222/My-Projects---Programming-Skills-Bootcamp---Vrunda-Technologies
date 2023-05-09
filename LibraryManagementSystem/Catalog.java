

import java.util.ArrayList;
import java.util.List;

public class Catalog {
    List<Book> books;

    // using default constructor to initialze the list of books
    Catalog() {
        books = new ArrayList<>();
    }

    /*
     * @method: addBook()
     * @params: Book class' object
     * @purpose: add the book object in the books list
     */
    public void addBook(Book book) {
        books.add(book);
    }

    /*
     * @method: removeBook()
     * @params: Book class' object
     * @purpose: removes the book object from the books list
     */
    public void removeBook(Book book) {
        books.remove(book);
    }

    /*
     * @method: getBook()
     * @params: name of the book
     * @purpose: returns the book name having name passed in the arguement
     */
    public Book getBook(String name){
        for(Book b: books){
            if(b.getTitle().equals(name)){
                return b;
            }
        }
        return new Book();
    }

    /*
     * method overloading concept
     * @method: getBook()
     * @params: index from the book list
     * @purpose: returns the book name having index passed in the arguement
     */
    public Book getBook(int index){
        if(index < books.size()){
            return books.get(index);
        }
        return new Book();
    }

    /*
     * @method: searchBook()
     * @params: String
     * @purpose: fetches all the books from the list having keyword(passed in as arguement) in them
     * @returns: List of fetched books having keyword in them
     */
    public List<Book> searchBook(String keyword) {
        List<Book> result = new ArrayList<>();

        for(Book book: books){
            if(book.getTitle().toLowerCase().contains(keyword) || book.getAuthor().toLowerCase().contains(keyword)  || book.getGenre().toLowerCase().contains(keyword)){
                result.add(book);
            }
        }

        return result;
    }

    // returns the list of books
    public List<Book> getAllBooks() {
        return books;
    }

    /*
     * @method: updateBook()
     * @args: Book class object, String title, author, genre and Boolean isAvailable
     * @purpose: update the object of the book with the new passed parameters
     */
    public void updateBook(Book book, String title, String author, String genre, boolean isAvailable){
        book.setTitle(title);
        book.setAuthor(author);
        book.setGenre(genre);
        book.changeAvailability(isAvailable);
    }
}
