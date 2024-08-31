package dao.custom;

import dao.CrudDAO;
import entity.User;

import java.util.List;

public interface UserDAO extends CrudDAO<User> {
    boolean save(User entity);

    List<User> getAll();

    boolean update(User entity);

    boolean delete(long id);
}
