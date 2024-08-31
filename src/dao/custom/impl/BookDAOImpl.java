package dao.custom.impl;

import dao.custom.BookDAO;
import db.DBConnection;
import entity.Book;
import entity.Branch;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAOImpl implements BookDAO {

    private Connection getConnection() throws SQLException, ClassNotFoundException {
        return DBConnection.getInstance().getConnection();
    }

    @Override
    public boolean save(Book entity) {
        String sql = "INSERT INTO Book (title, author, availability_status, generation, branch_id) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, entity.getTitle());
            statement.setString(2, entity.getAuthor());
            statement.setInt(3, entity.getAvailabilityStatus());
            statement.setString(4, entity.getGeneration());
            statement.setLong(5, entity.getBranch().getId()); // Assuming branch ID is available
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Book> getAll() {
        String sql = "SELECT * FROM Book";
        List<Book> books = new ArrayList<>();
        try (Connection connection = getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                Book book = new Book(
                        resultSet.getLong("id"),
                        resultSet.getString("title"),
                        resultSet.getString("author"),
                        resultSet.getInt("availability_status"),
                        resultSet.getString("generation"),
                        // You'll need to handle the branch ID appropriately
                        new Branch());
                books.add(book);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return books;
    }

    @Override
    public boolean update(Book entity) {
        String sql = "UPDATE Book SET title = ?, author = ?, availability_status = ?, generation = ?, branch_id = ? WHERE id = ?";
        try (Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, entity.getTitle());
            statement.setString(2, entity.getAuthor());
            statement.setInt(3, entity.getAvailabilityStatus());
            statement.setString(4, entity.getGeneration());
            statement.setLong(5, entity.getBranch().getId());
            statement.setLong(6, entity.getId());
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(long id) {
        String sql = "DELETE FROM Book WHERE id = ?";
        try (Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
}
