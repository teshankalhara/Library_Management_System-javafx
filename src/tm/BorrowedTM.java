package tm;

public class BorrowedTM {
    private long Bid;
    private String name;

    // No-argument constructor
    public BorrowedTM() {
    }

    // All-arguments constructor
    public BorrowedTM(long Bid, String name) {
        this.Bid = Bid;
        this.name = name;
    }

    // Getters and setters
    public long getBid() {
        return Bid;
    }

    public void setBid(long Bid) {
        this.Bid = Bid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Override toString method
    @Override
    public String toString() {
        return "BorrowedTM{" +
                "Bid=" + Bid +
                ", name='" + name + '\'' +
                '}';
    }
}
