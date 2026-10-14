import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LibraryManager {

    private InMemoryRepository<Book> bookRepository;
    private InMemoryRepository<Member> memberRepository;
    private InMemoryRepository<Borrowing> borrowingRepository;

    public LibraryManager() {

        bookRepository =
                new InMemoryRepository<>(Book::getBookId);

        memberRepository =
                new InMemoryRepository<>(Member::getMemberId);

        borrowingRepository =
                new InMemoryRepository<>(Borrowing::getBorrowingId);
    }

    // ---------------- BOOKS ----------------

    public boolean addBook(Book book) {

        if (bookRepository.exists(book.getBookId())) {
            return false;
        }

        bookRepository.add(book);
        return true;
    }

    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    public Book findBook(String bookId) {
        return bookRepository.findById(bookId);
    }

    // ---------------- MEMBERS ----------------

    public boolean addMember(Member member) {

        if (memberRepository.exists(member.getMemberId())) {
            return false;
        }

        memberRepository.add(member);
        return true;
    }

    public List<Member> getMembers() {
        return memberRepository.findAll();
    }

    public Member findMember(String memberId) {
        return memberRepository.findById(memberId);
    }

    // ---------------- BORROWING ----------------

    public boolean borrowBook(
            String borrowingId,
            String bookId,
            String memberId) {

        Book book = findBook(bookId);
        Member member = findMember(memberId);

        if (book == null || member == null) {
            return false;
        }

        if (!book.isAvailable()) {
            return false;
        }

        if (borrowingRepository.exists(borrowingId)) {
            return false;
        }

        Borrowing borrowing =
                new Borrowing(
                        borrowingId,
                        bookId,
                        memberId,
                        LocalDate.now()
                );

        borrowingRepository.add(borrowing);

        book.setAvailable(false);

        return true;
    }

    public boolean returnBook(String borrowingId) {

        Borrowing borrowing =
                borrowingRepository.findById(borrowingId);

        if (borrowing == null) {
            return false;
        }

        if (!borrowing.isActive()) {
            return false;
        }

        borrowing.setReturnDate(LocalDate.now());

        Book book = findBook(borrowing.getBookId());

        if (book != null) {
            book.setAvailable(true);
        }

        return true;
    }

    public List<Borrowing> getBorrowings() {
        return borrowingRepository.findAll();
    }

    public List<Borrowing> getActiveBorrowings() {

        List<Borrowing> active =
                new ArrayList<>();

        for (Borrowing borrowing : getBorrowings()) {

            if (borrowing.isActive()) {
                active.add(borrowing);
            }
        }

        return active;
    }
}