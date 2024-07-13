package dao.custom;

import java.util.List;

import dao.CrudDao;
import entity.Category;

public interface CategoryDao extends CrudDao<Category, Integer> {
    List<Category> findByName(String name) throws Exception;
}