package bo.custom.impl;

import bo.custom.BorrowBO;
import dao.DAOFactory;
import dao.custom.BookDAO;
import dao.custom.BorrowDAO;
import dto.BookDTO;
import dto.BorrowDTO;
import dto.BranchDTO;
import entity.Book;
import entity.Borrow;
import entity.Branch;
import db.DBConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BorrowBOImpl implements BorrowBO {
    private final BorrowDAO borrowDAO = (BorrowDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.BORROW);
    private final BookDAO bookDAO = (BookDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.BOOK);

    @Override
    public List<BookDTO> getAllBook() {
        List<BookDTO> bookDTOS = new ArrayList<>();
        List<Book> books = bookDAO.getAll();
        for (Book book : books) {
            Branch branch = book.getBranch();
            bookDTOS.add(new BookDTO(book.getId(), book.getTitle(), book.getAuthor(), book.getAvailabilityStatus(),
                    book.getGeneration(),
                    new BranchDTO(branch.getId(), branch.getAddress(), branch.getOpenedDate())));
        }
        return bookDTOS;
    }

    @Override
    public boolean saveBorrow(BorrowDTO dto) {
        Connection connection = null;
        try {
            try {
                connection = DBConnection.getInstance().getConnection();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            connection.setAutoCommit(false);

            // Save Borrow record
            boolean isBorrowedSaved = borrowDAO.save(new Borrow(dto.getBorrowedDate(), dto.getReturningDate(),
                    dto.getUser().toEntity(), dto.getBook().toEntity()));

            if (isBorrowedSaved) {
                // Update Book availability
                int newBookCount = dto.getBook().getAvailabilityStatus() - 1;
                Book newBooks = dto.getBook().toEntity();
                newBooks.setAvailabilityStatus(newBookCount);

                boolean isUpdateBookCount = bookDAO.update(newBooks);
                if (isUpdateBookCount) {
                    connection.commit();
                    return true;
                }
            }

            connection.rollback();
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            return false;
        } finally {
            if (connection != null) {
                try {
                    connection.setAutoCommit(true);
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public List<BorrowDTO> getAllBorrow() {
        List<BorrowDTO> borrowDTOS = new ArrayList<>();
        List<Borrow> borrows = borrowDAO.getAll();
        for (Borrow borrow : borrows) {
            borrowDTOS.add(new BorrowDTO(borrow.getId(), borrow.getBook().toDTO(), borrow.getUser().toDTO(),
                    borrow.getBorrowedDate(), borrow.getReturningDate()));
        }
        return borrowDTOS;
    }

    @Override
    public boolean returnBook(long id, BookDTO dto) {
        Connection connection = null;
        try {
            try {
                connection = DBConnection.getInstance().getConnection();
            } catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            connection.setAutoCommit(false);

            // Delete Borrow record
            boolean isReturned = borrowDAO.delete(id);
            if (isReturned) {
                // Update Book availability
                int newBookCount = dto.getAvailabilityStatus() + 1;
                Book newBooks = dto.toEntity();
                newBooks.setAvailabilityStatus(newBookCount);

                boolean isUpdateBookCount = bookDAO.update(newBooks);
                if (isUpdateBookCount) {
                    connection.commit();
                    return true;
                }
            }

            connection.rollback();
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            return false;
        } finally {
            if (connection != null) {
                try {
                    connection.setAutoCommit(true);
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
