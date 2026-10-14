public abstract class Member {

    private String memberId;
    private String name;
    private String phone;

    public Member(String memberId, String name, String phone) {
        this.memberId = memberId;
        this.name = name;
        this.phone = phone;
    }

    public String getMemberId() {
        return memberId;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public abstract double calculateLateFees(int daysLate);

    public abstract String getRole();

    @Override
    public String toString() {
        return "Member ID: " + memberId
                + " | Name: " + name
                + " | Phone: " + phone
                + " | Role: " + getRole();
    }
}