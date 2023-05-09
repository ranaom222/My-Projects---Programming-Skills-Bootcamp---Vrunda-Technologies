
public class Book {
    private String title, author, genre;
    private boolean isAvailable;

    // Default constructor in case of using setter methods
    Book() {}

    // Parameterized constructor to intialize the class properties on the creation of the object
    Book(String title, String author, String genre) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        isAvailable = true; // setting isAvailable to true by default because on the creation of the book object book will be already available in the library
    }

    Book(String title, String author, String genre, boolean status) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        isAvailable = status; // setting isAvailable to true by default because on the creation of the book object book will be already available in the library
    }
    

    /*
     * Setter methods:
     * setTitle() - sets or updates the title of the book
     * setAuthor() - sets or updates the author of the book
     * setGenre() - sets or updates the genre of the book
     * changeAvailability() - changes the availability of the book
     */
    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void changeAvailability(boolean status) {
        this.isAvailable = status;
    }

    /*
     * Getter methods:
     * getTitle() - returns the title of the book
     * getAuthor() - returns the author of the book
     * getGenre() - returns the genre of the book
     * getAvailability() - returns the book availability
     */
    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public boolean getAvailability() {
        return isAvailable;
    }

    /*
     * Overriding toString() method to print the book object in custom way
     */
    @Override
    public String toString(){
        return "\n------------------------\nBook Information\n------------------------\nName: " + title + "\nAuthor: " + author + "\nGenre: " + genre + "\nAvailability: " + isAvailable + "\n------------------------\n";
    }
}
