package entity;

import dto.BranchDTO;
import java.time.LocalDate;
import java.util.List;

public class Branch {
    private long id;
    private String address;
    private LocalDate openedDate;
    private List<Book> books;

    // No-args constructor
    public Branch() {
    }

    // Constructor with all fields
    public Branch(long id, String address, LocalDate openedDate, List<Book> books) {
        this.id = id;
        this.address = address;
        this.openedDate = openedDate;
        this.books = books;
    }

    // Constructor without 'id' for new entries
    public Branch(String address, LocalDate openedDate) {
        this.address = address;
        this.openedDate = openedDate;
    }

    // Constructor with 'id', 'address', and 'openedDate'
    public Branch(long id, String address, LocalDate openedDate) {
        this.id = id;
        this.address = address;
        this.openedDate = openedDate;
    }

    // Getters and setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getOpenedDate() {
        return openedDate;
    }

    public void setOpenedDate(LocalDate openedDate) {
        this.openedDate = openedDate;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    // Method to convert Branch entity to DTO
    public BranchDTO toDTO() {
        return new BranchDTO(id, address, openedDate);
    }

    @Override
    public String toString() {
        return "Branch{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", openedDate=" + openedDate +
                ", books=" + books +
                '}';
    }
}
