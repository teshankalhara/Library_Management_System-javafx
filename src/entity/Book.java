package entity;

import dto.BookDTO;
import java.util.ArrayList;
import java.util.List;

public class Book {
    private long id;
    private String title;
    private String author;
    private int availabilityStatus;
    private String generation;
    private Branch branch;
    private List<Borrow> borrows = new ArrayList<>();

    public Book() {
        // No-args constructor
    }

    public Book(long id, String title, String author, int availabilityStatus, String generation, Branch branch) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.availabilityStatus = availabilityStatus;
        this.generation = generation;
        this.branch = branch;
    }

    public Book(String title, String author, int availabilityStatus, String generation, Branch branch) {
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

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public List<Borrow> getBorrows() {
        return borrows;
    }

    public void setBorrows(List<Borrow> borrows) {
        this.borrows = borrows;
    }

    public BookDTO toDTO() {
        return new BookDTO(id, title, author, availabilityStatus, generation, branch.toDTO());
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", availabilityStatus=" + availabilityStatus +
                ", generation='" + generation + '\'' +
                ", branch=" + branch +
                ", borrows=" + borrows +
                '}';
    }
}
