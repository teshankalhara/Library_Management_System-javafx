package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import tm.BookHisTM;

import java.time.LocalDate;
import java.util.List;

import bo.BOFactory;
import bo.custom.BorrowBO;
import dto.BookDTO;
import dto.BorrowDTO;
import dto.UserDTO;

public class BooksHistoryFromController {

    @FXML
    private TableView<BookHisTM> tblView;

    @FXML
    private Label lblDate;
    @FXML
    private TableColumn<?, ?> columnBookId;

    @FXML
    private TableColumn<?, ?> columnTitle;

    @FXML
    private TableColumn<?, ?> columnAuthor;

    @FXML
    private TableColumn<?, ?> columnBorroweDate;

    @FXML
    private TableColumn<?, ?> columnExpDate;

    private UserDTO user = UserSignInFromController.userDTOS;
    BorrowBO borrowBO = (BorrowBO) BOFactory.getBoFactory().getBO(BOFactory.BOType.BORROW);

    public void initialize() {
        loadAllBooks();
        setValuesFactory();
        lblDate.setText(String.valueOf(LocalDate.now()));
    }

    private void setValuesFactory() {
        columnBookId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        columnAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
        columnBorroweDate.setCellValueFactory(new PropertyValueFactory<>("borrowedDate"));
        columnExpDate.setCellValueFactory(new PropertyValueFactory<>("expDate"));

    }

    public void loadAllBooks() {
        tblView.getItems().clear();
        List<BorrowDTO> allBorrow = borrowBO.getAllBorrow();
        for (BorrowDTO borrowDTO : allBorrow) {
            if (user.getId() == borrowDTO.getUser().getId()) {
                // tblView.setStyle("-fx-text-fill: red;");
                BookDTO dto = borrowDTO.getBook();
                tblView.getItems().add(new BookHisTM(dto.getId(), dto.getTitle(), dto.getAuthor(),
                        borrowDTO.getBorrowedDate(), borrowDTO.getReturningDate()));
            }

        }
    }
}
