public class Publisher extends Member {

    public Publisher(String memberId, String name, String phone) {
        super(memberId, name, phone);
    }

    @Override
    public double calculateLateFees(int daysLate) {

        if (daysLate <= 0) {
            return 0;
        }

        return daysLate * 50;
    }

    @Override
    public String getRole() {
        return "Publisher";
    }
}