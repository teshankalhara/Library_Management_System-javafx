package dao;

import dao.custom.impl.BookDAOImpl;
import dao.custom.impl.BorrowDAOImpl;
import dao.custom.impl.BranchDAOImpl;
import dao.custom.impl.UserDAOImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {
    }

    public static DAOFactory getDaoFactory() {
        return daoFactory == null ? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DAOType {
        BOOK, BRANCH, USER, BORROW
    }

    public SupperDAO getDAO(DAOType daoType) {
        switch (daoType) {
            case BOOK:
                return new BookDAOImpl();
            case USER:
                return new UserDAOImpl();
            case BRANCH:
                return new BranchDAOImpl();
            case BORROW:
                return new BorrowDAOImpl();
            default:
                return null;
        }
    }
}
