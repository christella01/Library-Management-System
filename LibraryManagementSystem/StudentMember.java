public class StudentMember extends Member {

    public StudentMember(String memberId, String name, String phone) {
        super(memberId, name, phone);
    }

    @Override
    public double calculateLateFees(int daysLate) {

        if (daysLate <= 0) {
            return 0;
        }

        return daysLate * 100;
    }

    @Override
    public String getRole() {
        return "Student";
    }
}