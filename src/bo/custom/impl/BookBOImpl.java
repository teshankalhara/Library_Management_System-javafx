package bo.custom.impl;

import bo.custom.BookBO;
import dao.DAOFactory;
import dao.custom.BookDAO;
import dao.custom.BranchDAO;
import dto.BookDTO;
import dto.BranchDTO;
import entity.Book;
import entity.Branch;

import java.util.ArrayList;
import java.util.List;

public class BookBOImpl implements BookBO {
    BranchDAO branchDAO = (BranchDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.BRANCH);
    BookDAO bookDAO = (BookDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.BOOK);

    @Override
    public List<BranchDTO> getAllBranch() {
        List<BranchDTO> branchDTOS = new ArrayList<>();
        List<Branch> branches = branchDAO.getAll();
        for (Branch branch : branches) {
            branchDTOS.add(new BranchDTO(branch.getId(), branch.getAddress(), branch.getOpenedDate()));
        }
        return branchDTOS;
    }

    @Override
    public boolean saveBook(BookDTO dto) {
        BranchDTO branchDTO = new BranchDTO();
        return bookDAO.save(new Book(dto.getTitle(), dto.getAuthor(), dto.getAvailabilityStatus(), dto.getGeneration(),
                branchDTO.toEntity(dto.getBranchDTO())));
    }

    @Override
    public List<BookDTO> getAllBook() {
        List<BookDTO> bookDTOS = new ArrayList<>();
        List<Book> books = bookDAO.getAll();
        for (Book book : books) {
            Branch branch = book.getBranch();
            bookDTOS.add(new BookDTO(book.getId(), book.getTitle(), book.getAuthor(), book.getAvailabilityStatus(),
                    book.getGeneration(),
                    (new BranchDTO(branch.getId(), branch.getAddress(), branch.getOpenedDate()))));
        }
        return bookDTOS;
    }

    @Override
    public boolean updateBook(BookDTO dto) {
        BranchDTO branchDTO = new BranchDTO();
        return bookDAO.update(new Book(dto.getId(), dto.getTitle(), dto.getAuthor(), dto.getAvailabilityStatus(),
                dto.getGeneration(), branchDTO.toEntity(dto.getBranchDTO())));
    }

    @Override
    public boolean deleteBook(long id) {
        return bookDAO.delete(id);
    }

}
