package dao;

import java.util.List;

public interface CrudDAO<T> extends SupperDAO {
    List<T> getAll();

    boolean save(T dto);

    boolean update(T dto);

    boolean delete(long id);
}
