package dao.custom.impl;

import dao.custom.BranchDAO;
import db.DBConnection;
import entity.Branch;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BranchDAOImpl implements BranchDAO {

    private Connection getConnection() throws SQLException, ClassNotFoundException {
        return DBConnection.getInstance().getConnection();
    }

    @Override
    public boolean save(Branch entity) {
        String sql = "INSERT INTO Branch (address, openDate) VALUES (?, ?)";
        try (Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, entity.getAddress());
            statement.setDate(2, Date.valueOf(entity.getOpenedDate()));
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Branch> getAll() {
        String sql = "SELECT * FROM Branch";
        List<Branch> branches = new ArrayList<>();
        try (Connection connection = getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                Branch branch = new Branch(
                        resultSet.getLong("id"),
                        resultSet.getString("address"),
                        resultSet.getDate("openDate").toLocalDate());
                branches.add(branch);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return branches;
    }

    @Override
    public boolean update(Branch entity) {
        String sql = "UPDATE Branch SET address = ?, openDate = ? WHERE id = ?";
        try (Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, entity.getAddress());
            statement.setDate(2, Date.valueOf(entity.getOpenedDate()));
            statement.setLong(3, entity.getId());
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(long id) {
        String sql = "DELETE FROM Branch WHERE id = ?";
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
