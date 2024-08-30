package bo.custom;

import java.util.List;

import bo.SupperBO;
import dto.BookDTO;
import dto.BorrowDTO;

public interface BorrowBO extends SupperBO {
    List<BookDTO> getAllBook();

    boolean saveBorrow(BorrowDTO dto);

    List<BorrowDTO> getAllBorrow();

    boolean returnBook(long id, BookDTO dto);
}
