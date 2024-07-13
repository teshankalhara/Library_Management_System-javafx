package dao.custom;

import java.util.List;

import dao.CrudDao;
import entity.Book;

public interface BookDao extends CrudDao<Book, Integer> {
    List<Book> findByTitle(String title) throws Exception;
}
