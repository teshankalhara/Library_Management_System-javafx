package bo.custom;

import java.util.List;

import bo.SupperBO;
import dto.BookDTO;
import dto.BranchDTO;

public interface BookBO extends SupperBO {

    List<BranchDTO> getAllBranch();

    boolean saveBook(BookDTO dto);

    List<BookDTO> getAllBook();

    boolean updateBook(BookDTO dto);

    boolean deleteBook(long id);
}
