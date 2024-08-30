package entity;

import java.time.LocalDate;

public class Borrow {
    private long id;
    private LocalDate borrowedDate;
    private LocalDate returningDate;
    private User user;
    private Book book;

    // No-args constructor
    public Borrow() {
    }

    // Constructor with all fields
    public Borrow(long id, LocalDate borrowedDate, LocalDate returningDate, User user, Book book) {
        this.id = id;
        this.borrowedDate = borrowedDate;
        this.returningDate = returningDate;
        this.user = user;
        this.book = book;
    }

    // Constructor without 'id' for new entries
    public Borrow(LocalDate borrowedDate, LocalDate returningDate, User user, Book book) {
        this.borrowedDate = borrowedDate;
        this.returningDate = returningDate;
        this.user = user;
        this.book = book;
    }

    // Getters and setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public String toString() {
        return "Borrow{" +
                "id=" + id +
                ", borrowedDate=" + borrowedDate +
                ", returningDate=" + returningDate +
                ", user=" + user +
                ", book=" + book +
                '}';
    }
}
