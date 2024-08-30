package dao.custom;

import dao.CrudDAO;
import entity.Branch;

import java.util.List;

public interface BranchDAO extends CrudDAO<Branch> {
    boolean save(Branch entity);

    List<Branch> getAll();

    boolean update(Branch entity);

    boolean delete(long id);
}
