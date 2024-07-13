package dao.custom;

import java.util.List;

import dao.CrudDao;
import entity.BorrowingTransaction;

public interface BorrowingTransactionDao extends CrudDao<BorrowingTransaction, Integer> {
    List<BorrowingTransaction> findByMemberId(Integer memberId) throws Exception;

    List<BorrowingTransaction> findByBookId(Integer bookId) throws Exception;
}