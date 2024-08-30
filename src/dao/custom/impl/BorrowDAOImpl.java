package dao.custom.impl;

import dao.custom.BorrowDAO;
import db.DBConnection;
import entity.Book;
import entity.Borrow;
import entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BorrowDAOImpl implements BorrowDAO {

    private Connection getConnection() throws SQLException, ClassNotFoundException {
        return DBConnection.getInstance().getConnection();
    }

    @Override
    public boolean save(Borrow entity) {
        String sql = "INSERT INTO Borrowed_info (borrowed_date, returning_date, user_id, book_id) VALUES (?, ?, ?, ?)";
        try (Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDate(1, Date.valueOf(entity.getBorrowedDate()));
            statement.setDate(2, Date.valueOf(entity.getReturningDate()));
            statement.setLong(3, entity.getUser().getId()); // Assuming user ID is available
            statement.setLong(4, entity.getBook().getId()); // Assuming book ID is available
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Borrow entity) {
        String sql = "UPDATE Borrowed_info SET borrowed_date = ?, returning_date = ?, user_id = ?, book_id = ? WHERE id = ?";
        try (Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDate(1, Date.valueOf(entity.getBorrowedDate()));
            statement.setDate(2, Date.valueOf(entity.getReturningDate()));
            statement.setLong(3, entity.getUser().getId());
            statement.setLong(4, entity.getBook().getId());
            statement.setLong(5, entity.getId());
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Borrow> getAll() {
        String sql = "SELECT * FROM Borrowed_info";
        List<Borrow> borrows = new ArrayList<>();
        try (Connection connection = getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                Borrow borrow = new Borrow(
                        resultSet.getLong("id"),
                        resultSet.getDate("borrowed_date").toLocalDate(),
                        resultSet.getDate("returning_date").toLocalDate(),
                        // You'll need to handle the user and book objects appropriately
                        new User(),
                        new Book());
                borrows.add(borrow);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return borrows;
    }

    @Override
    public boolean delete(long id) {
        String sql = "DELETE FROM Borrowed_info WHERE id = ?";
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
