package bo.custom;

import java.util.List;

import bo.SupperBO;
import dto.BorrowDTO;

public interface BookHistoryBO extends SupperBO {
    List<BorrowDTO> getAllBorrow();
}
