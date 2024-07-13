package dto;

import java.time.LocalDate;

import entity.BorrowingTransaction;

public class BorrowingTransactionDto {
    private int id;
    private int bookId;
    private int memberId;
    private LocalDate borrowDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
    private double fineAmount;

    public BorrowingTransactionDto(int id, int bookId, int memberId, LocalDate borrowDate, LocalDate dueDate,
            LocalDate returnDate, double fineAmount) {
        this.id = id;
        this.bookId = bookId;
        this.memberId = memberId;
        this.borrowDate = borrowDate;
        this.dueDate = dueDate;
        this.returnDate = returnDate;
        this.fineAmount = fineAmount;
    }

    public BorrowingTransaction toEntity() {
        return new BorrowingTransaction(id, bookId, memberId, borrowDate, dueDate, returnDate,
                fineAmount);
    }

}
