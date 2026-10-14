import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {

    private static Scanner input = new Scanner(System.in);
    private static LibraryManager library = new LibraryManager();

    public static void main(String[] args) {

        FileManager.initializeFiles();

        System.out.println("====================================");
        System.out.println("       LIBRARY MANAGEMENT SYSTEM");
        System.out.println("====================================");

        Member activeMember = createProfile();

        boolean running = true;

        while (running) {

            System.out.println();
            System.out.println("========== MAIN MENU ==========");
            System.out.println("Logged in as: "
                    + activeMember.getRole());
            System.out.println("1. Book & Publisher Menu");
            System.out.println("2. Member & Borrowing Menu");
            System.out.println("3. Reports");
            System.out.println("4. Exit");
            System.out.println("===============================");

            int choice = readInt("Choose an option: ");

            switch (choice) {

                case 1:
                    bookMenu(activeMember);
                    break;

                case 2:
                    memberMenu(activeMember);
                    break;

                case 3:
                    reportMenu();
                    break;

                case 4:
                    saveAll();
                    System.out.println("Thank you for using the system.");
                    running = false;
                    break;

                default:
                    System.out.println(
                            "Invalid option. Please try again.");
            }
        }

        input.close();
    }

    // ---------------- PROFILE ----------------

    private static Member createProfile() {

        System.out.println();
        System.out.println("Select your role:");
        System.out.println("1. Student");
        System.out.println("2. Publisher");
        System.out.println("3. Librarian");

        int role = readInt("Choose role: ");

        String id = readString("Enter your ID: ");
        String name = readString("Enter your name: ");
        String phone = readString("Enter your phone: ");

        Member member;

        if (role == 1) {

            member = new StudentMember(id, name, phone);

        } else if (role == 2) {

            member = new Publisher(id, name, phone);

        } else if (role == 3) {

            member = new Librarian(id, name, phone);

        } else {

            System.out.println(
                    "Invalid role. Student profile created.");

            member = new StudentMember(id, name, phone);
        }

        if (library.findMember(id) == null) {
            library.addMember(member);
        }

        return member;
    }

    // ---------------- BOOK MENU ----------------

    private static void bookMenu(Member activeMember) {

        boolean back = false;

        while (!back) {

            System.out.println();
            System.out.println("======= BOOK MENU =======");
            System.out.println("1. Add Book");
            System.out.println("2. View Books");
            System.out.println("3. Search Book");
            System.out.println("4. Back");
            System.out.println("=========================");

            int choice = readInt("Choose an option: ");

            switch (choice) {

                case 1:

                    if (activeMember instanceof Publisher
                            || activeMember instanceof Librarian) {

                        addBook(activeMember);

                    } else {

                        System.out.println(
                                "Only Publisher or Librarian can add books.");
                    }

                    break;

                case 2:
                    displayBooks();
                    break;

                case 3:
                    searchBook();
                    break;

                case 4:
                    back = true;
                    break;

                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private static void addBook(Member activeMember) {

        String bookId =
                readString("Enter book ID: ");

        if (library.findBook(bookId) != null) {

            System.out.println(
                    "A book with this ID already exists.");

            return;
        }

        String title =
                readString("Enter title: ");

        String author =
                readString("Enter author: ");

        String date =
                readString(
                        "Enter publication date (YYYY-MM-DD): ");

        String publisherId =
                activeMember.getMemberId();

        Book book =
                new Book(
                        bookId,
                        title,
                        author,
                        publisherId,
                        LocalDate.parse(date)
                );

        if (library.addBook(book)) {

            System.out.println(
                    "Book added successfully.");

        } else {

            System.out.println(
                    "Book could not be added.");
        }
    }

    private static void displayBooks() {

        List<Book> books =
                library.getBooks();

        if (books.isEmpty()) {

            System.out.println("No books available.");

            return;
        }

        System.out.println();
        System.out.println("========== BOOKS ==========");

        books.forEach(System.out::println);
    }

    private static void searchBook() {

        String keyword =
                readString("Enter title keyword: ");

        List<Book> results =
                LibraryUtils.searchBooksByTitle(
                        library.getBooks(),
                        keyword
                );

        if (results.isEmpty()) {

            System.out.println(
                    "No matching books found.");

        } else {

            results.forEach(System.out::println);
        }
    }

    // ---------------- MEMBER MENU ----------------

    private static void memberMenu(Member activeMember) {

        boolean back = false;

        while (!back) {

            System.out.println();
            System.out.println("===== MEMBER & BORROWING MENU =====");

            if (activeMember instanceof Librarian) {
                System.out.println("1. Register Member");
                System.out.println("2. View Members");
                System.out.println("3. Borrow Book");
                System.out.println("4. Return Book");
                System.out.println("5. Active Loans");
                System.out.println("6. Back");
            } else {
                System.out.println("1. Borrow Book");
                System.out.println("2. Return Book");
                System.out.println("3. Active Loans");
                System.out.println("4. Back");
            }

            int choice =
                    readInt("Choose an option: ");

            if (activeMember instanceof Librarian) {

                switch (choice) {

                    case 1:
                        registerMember();
                        break;

                    case 2:
                        displayMembers();
                        break;

                    case 3:
                        borrowBook();
                        break;

                    case 4:
                        returnBook();
                        break;

                    case 5:
                        displayActiveLoans();
                        break;

                    case 6:
                        back = true;
                        break;

                    default:
                        System.out.println("Invalid option.");
                }

            } else {

                switch (choice) {

                    case 1:
                        borrowBook();
                        break;

                    case 2:
                        returnBook();
                        break;

                    case 3:
                        displayActiveLoans();
                        break;

                    case 4:
                        back = true;
                        break;

                    default:
                        System.out.println("Invalid option.");
                }
            }
        }
    }

    private static void registerMember() {

        System.out.println();
        System.out.println("1. Student");
        System.out.println("2. Publisher");
        System.out.println("3. Librarian");

        int role =
                readInt("Choose member role: ");

        String id =
                readString("Enter member ID: ");

        String name =
                readString("Enter name: ");

        String phone =
                readString("Enter phone: ");

        Member member;

        if (role == 1) {

            member =
                    new StudentMember(id, name, phone);

        } else if (role == 2) {

            member =
                    new Publisher(id, name, phone);

        } else if (role == 3) {

            member =
                    new Librarian(id, name, phone);

        } else {

            System.out.println("Invalid role.");
            return;
        }

        if (library.addMember(member)) {

            System.out.println(
                    "Member registered successfully.");

        } else {

            System.out.println(
                    "Member ID already exists.");
        }
    }

    private static void displayMembers() {

        List<Member> members =
                library.getMembers();

        if (members.isEmpty()) {

            System.out.println("No members found.");

            return;
        }

        members.forEach(System.out::println);
    }

    private static void borrowBook() {

        String borrowingId =
                readString("Enter borrowing ID: ");

        String bookId =
                readString("Enter book ID: ");

        String memberId =
                readString("Enter member ID: ");

        if (library.borrowBook(
                borrowingId,
                bookId,
                memberId)) {

            System.out.println(
                    "Book borrowed successfully.");

        } else {

            System.out.println(
                    "Book could not be borrowed.");
        }
    }

    private static void returnBook() {

        String borrowingId =
                readString("Enter borrowing ID: ");

        if (library.returnBook(borrowingId)) {

            System.out.println(
                    "Book returned successfully.");

        } else {

            System.out.println(
                    "Borrowing record not found or already returned.");
        }
    }

    private static void displayActiveLoans() {

        List<Borrowing> loans =
                library.getActiveBorrowings();

        if (loans.isEmpty()) {

            System.out.println(
                    "No active loans.");

            return;
        }

        loans.forEach(System.out::println);
    }

    // ---------------- REPORTS ----------------

    private static void reportMenu() {

        boolean back = false;

        while (!back) {

            System.out.println();
            System.out.println("========== REPORTS ==========");
            System.out.println("1. Total Active Borrowings");
            System.out.println("2. Books by Starting Letter");
            System.out.println("3. Books by Publication Year");
            System.out.println("4. Sort Books by Title");
            System.out.println("5. Oldest Book");
            System.out.println("6. Newest Book");
            System.out.println("7. Back");
            System.out.println("=============================");

            int choice =
                    readInt("Choose an option: ");

            switch (choice) {

                case 1:

                    long count =
                            LibraryUtils.countBorrowedBooks(
                                    library.getBorrowings());

                    System.out.println(
                            "Active borrowed books: " + count);

                    break;

                case 2:

                    Map<Character, Long> letters =
                            LibraryUtils.groupBooksByStartingLetter(
                                    library.getBooks());

                    System.out.println(letters);

                    break;

                case 3:

                    Map<Integer, Long> years =
                            LibraryUtils.groupBooksByPublicationYear(
                                    library.getBooks());

                    System.out.println(years);

                    break;

                case 4:

                    List<Book> sorted =
                            LibraryUtils.sortBooksByTitle(
                                    library.getBooks());

                    sorted.forEach(System.out::println);

                    break;

                case 5:

                    Book oldest =
                            LibraryUtils.findOldestBook(
                                    library.getBooks());

                    System.out.println(oldest);

                    break;

                case 6:

                    Book newest =
                            LibraryUtils.findNewestBook(
                                    library.getBooks());

                    System.out.println(newest);

                    break;

                case 7:
                    back = true;
                    break;

                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    // ---------------- FILE SAVING ----------------

    private static void saveAll() {

        FileManager.saveBooks(
                library.getBooks());

        FileManager.saveMembers(
                library.getMembers());

        FileManager.saveBorrowings(
                library.getBorrowings());

        System.out.println(
                "Library data saved successfully.");
    }

    // ---------------- INPUT HELPERS ----------------

    private static String readString(String message) {

        System.out.print(message);

        return input.nextLine();
    }

    private static int readInt(String message) {

        while (true) {

            try {

                System.out.print(message);

                return Integer.parseInt(
                        input.nextLine());

            } catch (NumberFormatException e) {

                System.out.println(
                        "Invalid input. Please enter a number.");
            }
        }
    }
}