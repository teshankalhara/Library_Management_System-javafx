package dao;

import dao.custom.impl.CategoryDaoImpl;

public class DaoFactory {
    private static DaoFactory daoFactory;

    private DaoFactory() {

    }

    public static DaoFactory getInstance() {
        if (daoFactory == null) {
            daoFactory = new DaoFactory();
        }
        return daoFactory;
    }

    public SuperDAO getDao(DaoTypes type) {
        switch (type) {
            case Category:
                return new CategoryDaoImpl();
            case Book:
                return null;
            case Member:
                return null;
            case Borrow:
                return null;
            case Return:
                return null;
            default:
                return null;
        }
    }

    public enum DaoTypes {
        Category, Book, Member, Borrow, Return;
    }
}
