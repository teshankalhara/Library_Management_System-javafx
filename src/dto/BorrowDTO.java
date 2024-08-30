package dto;

import java.time.LocalDate;

public class BorrowDTO {
    private long id;
    private BookDTO book;
    private UserDTO user;
    private LocalDate borrowedDate;
    private LocalDate returningDate;

    // No-argument constructor
    public BorrowDTO() {
    }

    // All-arguments constructor
    public BorrowDTO(long id, BookDTO book, UserDTO user, LocalDate borrowedDate, LocalDate returningDate) {
        this.id = id;
        this.book = book;
        this.user = user;
        this.borrowedDate = borrowedDate;
        this.returningDate = returningDate;
    }

    // Constructor without 'id' for new entries
    public BorrowDTO(BookDTO book, UserDTO user, LocalDate borrowedDate, LocalDate returningDate) {
        this.book = book;
        this.user = user;
        this.borrowedDate = borrowedDate;
        this.returningDate = returningDate;
    }

    // Getters and setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BookDTO getBook() {
        return book;
    }

    public void setBook(BookDTO book) {
        this.book = book;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public LocalDate getBorrowedDate() {
        return borrowedDate;
    }

    public void setBorrowedDate(LocalDate borrowedDate) {
        this.borrowedDate = borrowedDate;
    }

    public LocalDate getReturningDate() {
        return returningDate;
    }

    public void setReturningDate(LocalDate returningDate) {
        this.returningDate = returningDate;
    }

    // Override toString method
    @Override
    public String toString() {
        return "BorrowDTO{" +
                "id=" + id +
                ", book=" + book +
                ", user=" + user +
                ", borrowedDate=" + borrowedDate +
                ", returningDate=" + returningDate +
                '}';
    }
}
