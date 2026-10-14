import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FileManager {

    private static final String DATA_FOLDER = "library_data";

    private FileManager() {
    }

    public static void initializeFiles() {

        File folder = new File(DATA_FOLDER);

        if (!folder.exists()) {
            folder.mkdir();
        }

        createFile("books.txt");
        createFile("members.txt");
        createFile("borrowings.txt");
    }

    private static void createFile(String fileName) {

        File file = new File(DATA_FOLDER, fileName);

        try {

            if (file.createNewFile()) {
                System.out.println("Created file: " + file.getPath());
            }

        } catch (IOException e) {

            System.out.println(
                    "Could not create file: " + fileName);
        }
    }

    public static void saveBooks(List<Book> books) {

        try (BufferedWriter writer =
                     new BufferedWriter(
                             new FileWriter(
                                     DATA_FOLDER + "/books.txt"))) {

            for (Book book : books) {

                writer.write(
                        book.getBookId() + "|" +
                        book.getTitle() + "|" +
                        book.getAuthor() + "|" +
                        book.getPublisherId() + "|" +
                        book.getPublicationDate() + "|" +
                        book.isAvailable()
                );

                writer.newLine();
            }

        } catch (IOException e) {

            System.out.println("Error saving books.");
        }
    }

    public static void saveMembers(List<Member> members) {

        try (BufferedWriter writer =
                     new BufferedWriter(
                             new FileWriter(
                                     DATA_FOLDER + "/members.txt"))) {

            for (Member member : members) {

                writer.write(
                        member.getMemberId() + "|" +
                        member.getName() + "|" +
                        member.getPhone() + "|" +
                        member.getRole()
                );

                writer.newLine();
            }

        } catch (IOException e) {

            System.out.println("Error saving members.");
        }
    }

    public static void saveBorrowings(
            List<Borrowing> borrowings) {

        try (BufferedWriter writer =
                     new BufferedWriter(
                             new FileWriter(
                                     DATA_FOLDER + "/borrowings.txt"))) {

            for (Borrowing borrowing : borrowings) {

                writer.write(
                        borrowing.getBorrowingId() + "|" +
                        borrowing.getBookId() + "|" +
                        borrowing.getMemberId() + "|" +
                        borrowing.getBorrowDate() + "|" +
                        borrowing.getReturnDate()
                );

                writer.newLine();
            }

        } catch (IOException e) {

            System.out.println("Error saving borrowings.");
        }
    }
}