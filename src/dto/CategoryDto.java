package dto;

import entity.Category;

public class CategoryDto {
    private int id;
    private String name;

    public CategoryDto(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Category toEntity() {
        return new Category(id, name);
    }
}
