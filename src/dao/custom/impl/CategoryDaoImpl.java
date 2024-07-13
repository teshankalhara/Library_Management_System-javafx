package dao.custom.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dao.CrudUtil;
import dao.custom.CategoryDao;
import db.DBConnection;
import entity.Category;

public class CategoryDaoImpl implements CategoryDao {

    @Override
    public String save(Category category) throws Exception {
        String sql = "INSERT INTO categories (name) VALUES (?)";
        PreparedStatement stmt = null;
        try {
            stmt = CrudUtil.getPreparedStatement(sql, category.getName());
            if (stmt.executeUpdate() > 0) {
                return "Added";
            } else {
                return "Failed";
            }
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    @Override
    public String update(Category category) throws Exception {
        String sql = "UPDATE categories SET name = ? WHERE id = ?";
        PreparedStatement stmt = null;
        try {
            stmt = CrudUtil.getPreparedStatement(sql, category.getName(), category.getId());
            if (stmt.executeUpdate() > 0) {
                return "Updated";
            } else {
                return "Failed";
            }
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    @Override
    public String delete(Integer id) throws Exception {
        String sql = "DELETE FROM categories WHERE id = ?";
        PreparedStatement stmt = null;
        try {
            stmt = CrudUtil.getPreparedStatement(sql, id);
            if (stmt.executeUpdate() > 0) {
                return "Deleted";
            } else {
                return "Failed";
            }
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    @Override
    public Category get(Integer id) throws Exception {
        String sql = "SELECT * FROM categories WHERE id = ?";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = CrudUtil.getPreparedStatement(sql, id);
            rs = stmt.executeQuery();
            if (rs.next()) {
                Category category = new Category();
                category.setId(rs.getInt("id"));
                category.setName(rs.getString("name"));
                return category;
            } else {
                return null;
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    @Override
    public ArrayList<Category> getAll() throws Exception {
        String sql = "SELECT * FROM categories";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = DBConnection.getInstance().getConnection().prepareStatement(sql);
            rs = stmt.executeQuery();
            ArrayList<Category> categories = new ArrayList<>();
            while (rs.next()) {
                Category category = new Category();
                category.setId(rs.getInt("id"));
                category.setName(rs.getString("name"));
                categories.add(category);
            }
            return categories;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    @Override
    public List<Category> findByName(String name) throws Exception {
        String sql = "SELECT * FROM categories WHERE name LIKE ?";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = CrudUtil.getPreparedStatement(sql, "%" + name + "%");
            rs = stmt.executeQuery();
            List<Category> categories = new ArrayList<>();
            while (rs.next()) {
                Category category = new Category();
                category.setId(rs.getInt("id"));
                category.setName(rs.getString("name"));
                categories.add(category);
            }
            return categories;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }
    }
}
