package dto;

import entity.Book;

public class BookDto {
    private int id;
    private String title;
    private String author;
    private CategoryDto category;
    private int isAvailable;

    public BookDto(int id, String title, String author, CategoryDto category, int isAvailable) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.category = category;
        this.isAvailable = isAvailable;
    }

    public Book toEntity() {
        return new Book(id, title, author, category.toEntity(), isAvailable);
    }
}
