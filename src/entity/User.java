package entity;

import dto.UserDTO;
import java.util.ArrayList;
import java.util.List;

public class User {
    private long id;
    private String name;
    private String email;
    private String password;
    private List<Borrow> borrows = new ArrayList<>();

    // No-args constructor
    public User() {
    }

    // Constructor with all fields
    public User(long id, String name, String email, String password, List<Borrow> borrows) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.borrows = borrows;
    }

    // Constructor without 'borrows' list
    public User(long id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    // Constructor without 'id' for new entries
    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    // Getters and setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Borrow> getBorrows() {
        return borrows;
    }

    public void setBorrows(List<Borrow> borrows) {
        this.borrows = borrows;
    }

    // Method to convert User entity to DTO
    public UserDTO toDTO() {
        return new UserDTO(id, name, email, password);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", borrows=" + borrows +
                '}';
    }
}
