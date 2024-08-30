package bo;

import bo.custom.impl.BookBOImpl;
import bo.custom.impl.BorrowBOImpl;
import bo.custom.impl.BranchBOImpl;
import bo.custom.impl.UserBOImpl;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory() {
    }

    public static BOFactory getBoFactory() {
        return boFactory == null ? boFactory = new BOFactory() : boFactory;
    }

    public enum BOType {
        BOOK, BRANCH, USER, BORROW
    }

    public SupperBO getBO(BOType boType) {
        switch (boType) {
            case BOOK:
                return new BookBOImpl();
            case USER:
                return new UserBOImpl();
            case BRANCH:
                return new BranchBOImpl();
            case BORROW:
                return new BorrowBOImpl();
            default:
                return null;

        }
    }

}
