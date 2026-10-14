public class Librarian extends Member {

    public Librarian(String memberId, String name, String phone) {
        super(memberId, name, phone);
    }

    @Override
    public double calculateLateFees(int daysLate) {
        return 0;
    }

    @Override
    public String getRole() {
        return "Librarian";
    }
}