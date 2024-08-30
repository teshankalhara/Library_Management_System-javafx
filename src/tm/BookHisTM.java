package tm;

import java.time.LocalDate;

public class BookHisTM {
    private long id;
    private String title;
    private String author;
    private LocalDate borrowedDate;
    private LocalDate expDate;

    // No-argument constructor
    public BookHisTM() {
    }

    // All-arguments constructor
    public BookHisTM(long id, String title, String author, LocalDate borrowedDate, LocalDate expDate) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.borrowedDate = borrowedDate;
        this.expDate = expDate;
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

    public LocalDate getBorrowedDate() {
        return borrowedDate;
    }

    public void setBorrowedDate(LocalDate borrowedDate) {
        this.borrowedDate = borrowedDate;
    }

    public LocalDate getExpDate() {
        return expDate;
    }

    public void setExpDate(LocalDate expDate) {
        this.expDate = expDate;
    }

    // Override toString method
    @Override
    public String toString() {
        return "BookHisTM{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", borrowedDate=" + borrowedDate +
                ", expDate=" + expDate +
                '}';
    }
}
