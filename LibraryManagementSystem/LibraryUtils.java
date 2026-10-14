import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class LibraryUtils {

    private LibraryUtils() {
    }

    // Generic filtering method
    public static <T> List<T> filter(
            List<T> items,
            Predicate<T> condition) {

        return items.stream()
                .filter(condition)
                .collect(Collectors.toList());
    }

    // Search books by title
    public static List<Book> searchBooksByTitle(
            List<Book> books,
            String keyword) {

        return books.stream()
                .filter(book -> book.getTitle()
                        .toLowerCase()
                        .contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
    }

    // Sort books alphabetically by title
    public static List<Book> sortBooksByTitle(
            List<Book> books) {

        return books.stream()
                .sorted(Comparator.comparing(Book::getTitle))
                .collect(Collectors.toList());
    }

    // Count total borrowed books
    public static long countBorrowedBooks(
            List<Borrowing> borrowings) {

        return borrowings.stream()
                .filter(Borrowing::isActive)
                .count();
    }

    // Group books by first letter of title
    public static Map<Character, Long> groupBooksByStartingLetter(
            List<Book> books) {

        return books.stream()
                .collect(Collectors.groupingBy(
                        book -> Character.toUpperCase(
                                book.getTitle().charAt(0)),
                        Collectors.counting()
                ));
    }

    // Group books by publication year
    public static Map<Integer, Long> groupBooksByPublicationYear(
            List<Book> books) {

        return books.stream()
                .collect(Collectors.groupingBy(
                        book -> book.getPublicationDate().getYear(),
                        Collectors.counting()
                ));
    }

    // Find the book with the earliest publication date
    public static Book findOldestBook(List<Book> books) {

        return books.stream()
                .min(Comparator.comparing(Book::getPublicationDate))
                .orElse(null);
    }

    // Find the book with the latest publication date
    public static Book findNewestBook(List<Book> books) {

        return books.stream()
                .max(Comparator.comparing(Book::getPublicationDate))
                .orElse(null);
    }
}