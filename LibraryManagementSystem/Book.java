import java.time.LocalDate;

public class Book {

    private String bookId;
    private String title;
    private String author;
    private String publisherId;
    private LocalDate publicationDate;
    private boolean available;

    public Book(String bookId, String title, String author,
                String publisherId, LocalDate publicationDate) {

        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.publisherId = publisherId;
        this.publicationDate = publicationDate;
        this.available = true;
    }

    public String getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublisherId() {
        return publisherId;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "Book ID: " + bookId
                + " | Title: " + title
                + " | Author: " + author
                + " | Publisher ID: " + publisherId
                + " | Publication Date: " + publicationDate
                + " | Available: " + available;
    }
}