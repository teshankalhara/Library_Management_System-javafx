package dao.custom;

import dao.CrudDAO;
import entity.Borrow;

import java.util.List;

public interface BorrowDAO extends CrudDAO<Borrow> {
    boolean save(Borrow entity);

    List<Borrow> getAll();

    boolean delete(long id);
}
