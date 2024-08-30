package tm;

public class BookTM {
    private long id;
    private String title;
    private String author;
    private int availabilityStatus;
    private String generation;
    private String branch;

    // No-argument constructor
    public BookTM() {
    }

    // All-arguments constructor
    public BookTM(long id, String title, String author, int availabilityStatus, String generation, String branch) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.availabilityStatus = availabilityStatus;
        this.generation = generation;
        this.branch = branch;
    }

    // Getters and setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getAvailabilityStatus() {
        return availabilityStatus;
    }

    public void setAvailabilityStatus(int availabilityStatus) {
        this.availabilityStatus = availabilityStatus;
    }

    public String getGeneration() {
        return generation;
    }

    public void setGeneration(String generation) {
        this.generation = generation;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    // Override toString method
    @Override
    public String toString() {
        return "BookTM{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", availabilityStatus=" + availabilityStatus +
                ", generation='" + generation + '\'' +
                ", branch='" + branch + '\'' +
                '}';
    }
}
