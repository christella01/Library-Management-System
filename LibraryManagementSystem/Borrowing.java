import java.time.LocalDate;

public class Borrowing {

    private String borrowingId;
    private String bookId;
    private String memberId;
    private LocalDate borrowDate;
    private LocalDate returnDate;

    public Borrowing(String borrowingId,
                     String bookId,
                     String memberId,
                     LocalDate borrowDate) {

        this.borrowingId = borrowingId;
        this.bookId = bookId;
        this.memberId = memberId;
        this.borrowDate = borrowDate;
        this.returnDate = null;
    }

    public String getBorrowingId() {
        return borrowingId;
    }

    public String getBookId() {
        return bookId;
    }

    public String getMemberId() {
        return memberId;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public boolean isActive() {
        return returnDate == null;
    }

    @Override
    public String toString() {
        return "Borrowing ID: " + borrowingId
                + " | Book ID: " + bookId
                + " | Member ID: " + memberId
                + " | Borrow Date: " + borrowDate
                + " | Return Date: " + returnDate
                + " | Active: " + isActive();
    }
}