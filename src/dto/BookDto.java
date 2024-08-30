package dto;

import entity.Book;

public class BookDTO {
    private long id;
    private String title;
    private String author;
    private int availabilityStatus;
    private String generation;
    private BranchDTO branchDTO;

    // No-argument constructor
    public BookDTO() {
    }

    // All-arguments constructor
    public BookDTO(long id, String title, String author, int availabilityStatus, String generation,
            BranchDTO branchDTO) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.availabilityStatus = availabilityStatus;
        this.generation = generation;
        this.branchDTO = branchDTO;
    }

    // Constructor without 'id' for new entries
    public BookDTO(String title, String author, int availabilityStatus, String generation, BranchDTO branchDTO) {
        this.title = title;
        this.author = author;
        this.availabilityStatus = availabilityStatus;
        this.generation = generation;
        this.branchDTO = branchDTO;
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

    public BranchDTO getBranchDTO() {
        return branchDTO;
    }

    public void setBranchDTO(BranchDTO branchDTO) {
        this.branchDTO = branchDTO;
    }

    // Convert DTO to Entity
    public Book toEntity() {
        return new Book(id, title, author, availabilityStatus, generation, branchDTO.ToEntity());
    }

    // Override toString method
    @Override
    public String toString() {
        return "BookDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", availabilityStatus=" + availabilityStatus +
                ", generation='" + generation + '\'' +
                ", branchDTO=" + branchDTO +
                '}';
    }
}
