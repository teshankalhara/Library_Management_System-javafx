package dao.custom.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dao.CrudUtil;
import dao.custom.BookDao;
import db.DBConnection;
import entity.Book;
import entity.Category;

public class BookDaoImpl implements BookDao {
    @Override
    public String save(Book book) throws Exception {
        String sql = "INSERT INTO books (title, author, category_id, is_available) VALUES (?, ?, ?, ?)";
        PreparedStatement stmt = null;
        try {
            stmt = CrudUtil.getPreparedStatement(sql,
                    book.getTitle(),
                    book.getAuthor(),
                    book.getCategory().getId(),
                    book.getIsAvailable());
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
    public String update(Book book) throws Exception {
        String sql = "UPDATE books SET title = ?, author = ?, category_id = ?, is_available = ? WHERE id = ?";
        PreparedStatement stmt = null;
        try {
            stmt = CrudUtil.getPreparedStatement(sql,
                    book.getTitle(),
                    book.getAuthor(),
                    book.getCategory().getId(),
                    book.getIsAvailable(),
                    book.getId());
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
        String sql = "DELETE FROM books WHERE id = ?";
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
    public Book get(Integer id) throws Exception {
        String sql = "SELECT b.*, c.id as category_id, c.name as category_name FROM books b JOIN categories c ON b.category_id = c.id WHERE b.id = ?";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = CrudUtil.getPreparedStatement(sql, id);
            rs = stmt.executeQuery();
            if (rs.next()) {
                Book book = new Book();
                book.setId(rs.getInt("id"));
                book.setTitle(rs.getString("title"));
                book.setAuthor(rs.getString("author"));

                Category category = new Category();
                category.setId(rs.getInt("category_id"));
                category.setName(rs.getString("category_name"));

                book.setCategory(category);
                book.setIsAvailable(rs.getInt("is_available"));
                return book;
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
    public ArrayList<Book> getAll() throws Exception {
        String sql = "SELECT b.*, c.id as category_id, c.name as category_name FROM books b JOIN categories c ON b.category_id = c.id";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = DBConnection.getInstance().getConnection().prepareStatement(sql);
            rs = stmt.executeQuery();
            ArrayList<Book> books = new ArrayList<>();
            while (rs.next()) {
                Book book = new Book();
                book.setId(rs.getInt("id"));
                book.setTitle(rs.getString("title"));
                book.setAuthor(rs.getString("author"));

                Category category = new Category();
                category.setId(rs.getInt("category_id"));
                category.setName(rs.getString("category_name"));

                book.setCategory(category);
                book.setIsAvailable(rs.getInt("is_available"));
                books.add(book);
            }
            return books;
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
    public List<Book> findByTitle(String title) throws Exception {
        String sql = "SELECT b.*, c.id as category_id, c.name as category_name FROM books b JOIN categories c ON b.category_id = c.id WHERE b.title LIKE ?";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = CrudUtil.getPreparedStatement(sql, "%" + title + "%");
            rs = stmt.executeQuery();
            List<Book> books = new ArrayList<>();
            while (rs.next()) {
                Book book = new Book();
                book.setId(rs.getInt("id"));
                book.setTitle(rs.getString("title"));
                book.setAuthor(rs.getString("author"));

                Category category = new Category();
                category.setId(rs.getInt("category_id"));
                category.setName(rs.getString("category_name"));

                book.setCategory(category);
                book.setIsAvailable(rs.getInt("is_available"));
                books.add(book);
            }
            return books;
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
