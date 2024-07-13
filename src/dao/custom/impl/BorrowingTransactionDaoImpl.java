package dao.custom.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dao.CrudUtil;
import dao.custom.BorrowingTransactionDao;
import db.DBConnection;
import entity.BorrowingTransaction;

public class BorrowingTransactionDaoImpl implements BorrowingTransactionDao {

    @Override
    public String save(BorrowingTransaction borrowingTransaction) throws Exception {
        String sql = "INSERT INTO borrowing_transactions (member_id, book_id, borrow_date, due_date, return_date, fine) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement stmt = null;
        try {
            stmt = CrudUtil.getPreparedStatement(sql,
                    borrowingTransaction.getMemberId(),
                    borrowingTransaction.getBookId(),
                    borrowingTransaction.getBorrowDate(),
                    borrowingTransaction.getDueDate(),
                    borrowingTransaction.getReturnDate(),
                    borrowingTransaction.getFine());
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
    public String update(BorrowingTransaction borrowingTransaction) throws Exception {
        String sql = "UPDATE borrowing_transactions SET member_id = ?, book_id = ?, borrow_date = ?, due_date = ?, return_date = ?, fine = ? WHERE id = ?";
        PreparedStatement stmt = null;
        try {
            stmt = CrudUtil.getPreparedStatement(sql,
                    borrowingTransaction.getMemberId(),
                    borrowingTransaction.getBookId(),
                    borrowingTransaction.getBorrowDate(),
                    borrowingTransaction.getDueDate(),
                    borrowingTransaction.getReturnDate(),
                    borrowingTransaction.getFine(),
                    borrowingTransaction.getId());
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
        String sql = "DELETE FROM borrowing_transactions WHERE id = ?";
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
    public BorrowingTransaction get(Integer id) throws Exception {
        String sql = "SELECT * FROM borrowing_transactions WHERE id = ?";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = CrudUtil.getPreparedStatement(sql, id);
            rs = stmt.executeQuery();
            if (rs.next()) {
                BorrowingTransaction borrowingTransaction = new BorrowingTransaction();
                borrowingTransaction.setId(rs.getInt("id"));
                borrowingTransaction.setMemberId(rs.getInt("member_id"));
                borrowingTransaction.setBookId(rs.getInt("book_id"));
                borrowingTransaction.setBorrowDate(rs.getDate("borrow_date"));
                borrowingTransaction.setDueDate(rs.getDate("due_date"));
                borrowingTransaction.setReturnDate(rs.getDate("return_date"));
                borrowingTransaction.setFine(rs.getDouble("fine"));
                return borrowingTransaction;
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
    public ArrayList<BorrowingTransaction> getAll() throws Exception {
        String sql = "SELECT * FROM borrowing_transactions";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = DBConnection.getInstance().getConnection().prepareStatement(sql);
            rs = stmt.executeQuery();
            ArrayList<BorrowingTransaction> transactions = new ArrayList<>();
            while (rs.next()) {
                BorrowingTransaction borrowingTransaction = new BorrowingTransaction();
                borrowingTransaction.setId(rs.getInt("id"));
                borrowingTransaction.setMemberId(rs.getInt("member_id"));
                borrowingTransaction.setBookId(rs.getInt("book_id"));
                borrowingTransaction.setBorrowDate(rs.getDate("borrow_date"));
                borrowingTransaction.setDueDate(rs.getDate("due_date"));
                borrowingTransaction.setReturnDate(rs.getDate("return_date"));
                borrowingTransaction.setFine(rs.getDouble("fine"));
                transactions.add(borrowingTransaction);
            }
            return transactions;
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
    public List<BorrowingTransaction> findByMemberId(Integer memberId) throws Exception {
        String sql = "SELECT * FROM borrowing_transactions WHERE member_id = ?";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = CrudUtil.getPreparedStatement(sql, memberId);
            rs = stmt.executeQuery();
            List<BorrowingTransaction> transactions = new ArrayList<>();
            while (rs.next()) {
                BorrowingTransaction borrowingTransaction = new BorrowingTransaction();
                borrowingTransaction.setId(rs.getInt("id"));
                borrowingTransaction.setMemberId(rs.getInt("member_id"));
                borrowingTransaction.setBookId(rs.getInt("book_id"));
                borrowingTransaction.setBorrowDate(rs.getDate("borrow_date"));
                borrowingTransaction.setDueDate(rs.getDate("due_date"));
                borrowingTransaction.setReturnDate(rs.getDate("return_date"));
                borrowingTransaction.setFine(rs.getDouble("fine"));
                transactions.add(borrowingTransaction);
            }
            return transactions;
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
    public List<BorrowingTransaction> findByBookId(Integer bookId) throws Exception {
        String sql = "SELECT * FROM borrowing_transactions WHERE book_id = ?";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = CrudUtil.getPreparedStatement(sql, bookId);
            rs = stmt.executeQuery();
            List<BorrowingTransaction> transactions = new ArrayList<>();
            while (rs.next()) {
                BorrowingTransaction borrowingTransaction = new BorrowingTransaction();
                borrowingTransaction.setId(rs.getInt("id"));
                borrowingTransaction.setMemberId(rs.getInt("member_id"));
                borrowingTransaction.setBookId(rs.getInt("book_id"));
                borrowingTransaction.setBorrowDate(rs.getDate("borrow_date"));
                borrowingTransaction.setDueDate(rs.getDate("due_date"));
                borrowingTransaction.setReturnDate(rs.getDate("return_date"));
                borrowingTransaction.setFine(rs.getDouble("fine"));
                transactions.add(borrowingTransaction);
            }
            return transactions;
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
