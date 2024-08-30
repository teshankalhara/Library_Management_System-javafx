package bo.custom.impl;

import java.util.ArrayList;
import java.util.List;

import bo.custom.BookHistoryBO;
import dao.DAOFactory;
import dao.custom.BorrowDAO;
import dto.BorrowDTO;
import entity.Borrow;

public class BookHistoryBOImpl implements BookHistoryBO {
    BorrowDAO borrowDAO = (BorrowDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.BORROW);

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
}
