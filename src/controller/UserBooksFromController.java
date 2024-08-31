package controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import tm.BookTM;
import tm.BorrowedTM;

import java.time.LocalDate;
import java.util.List;

import bo.BOFactory;
import bo.custom.BorrowBO;
import dto.BookDTO;
import dto.BorrowDTO;
import dto.BranchDTO;
import dto.UserDTO;

public class UserBooksFromController {

    @FXML
    private TableView<BorrowedTM> tblBrrowed;

    @FXML
    private TableColumn<?, ?> columnBookId;

    @FXML
    private TableColumn<?, ?> columnBookName;

    @FXML
    private TableView<BookTM> tblBookList;
    @FXML
    private TableColumn<?, ?> columnId;

    @FXML
    private TableColumn<?, ?> columnTitle;

    @FXML
    private TableColumn<?, ?> columnAuthor;

    @FXML
    private TableColumn<?, ?> columnAStatus;

    @FXML
    private TableColumn<?, ?> columnGen;

    @FXML
    private TableColumn<?, ?> columnBranch;

    @FXML
    private TextField textSearch;

    @FXML
    private ComboBox<String> CombSearchValues;

    @FXML
    private Label lblBookTitle;

    @FXML
    private Label lblAthorName;

    @FXML
    private Label lblGen;

    @FXML
    private Label lblBookId;

    @FXML
    private Label lblStatus;

    @FXML
    private Label lblBranch;

    @FXML
    private Button btnReturn;

    @FXML
    private Label lblSearchError;
    @FXML
    private Button btnBorrow;
    @FXML
    private Button btnSearch;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblAvSt;
    private UserDTO user = UserSignInFromController.userDTOS;
    BorrowBO borrowBO = (BorrowBO) BOFactory.getBoFactory().getBO(BOFactory.BOType.BORROW);

    public void initialize() {

        resetAll();
        setCombSearchValues();
        loadAllBooks();
        setvaluesFactory();
        setDate();
        loadAllBorrow();
        textSearch.setOnAction((ActionEvent event) -> {
            btnSearch.fire();
        });
        tblBookOnAction();
        tblBorrowedOnAction();

    }

    public void tblBookOnAction() {
        tblBookList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                lblAvSt.setOpacity(100);
                btnBorrow.setDisable(false);
                lblBranch.setText(newValue.getBranch());
                lblBookTitle.setText(newValue.getTitle());
                lblAthorName.setText(newValue.getAuthor());
                lblGen.setText(newValue.getGeneration());
                lblBookId.setText(String.valueOf(newValue.getId()));
                lblStatus.setText(String.valueOf(newValue.getAvailabilityStatus()));
                btnReturn.setDisable(true);
            } else {
                resetAll();
            }
        });
    }

    public void tblBorrowedOnAction() {
        tblBrrowed.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue1) -> {
            if (newValue1 != null) {
                btnReturn.setDisable(false);
                btnBorrow.setDisable(true);
                long bookId = newValue1.getBid();
                BookDTO book = getBooksById(bookId);
                lblBookId.setText(String.valueOf(book.getId()));
                lblBranch.setText(book.getBranchDTO().getAddress());
                lblBookTitle.setText(book.getTitle());
                lblAthorName.setText(book.getAuthor());
                lblGen.setText(book.getGeneration());
                lblAvSt.setOpacity(0);
                lblStatus.setText("");

            }
        });
    }

    public void resetAll() {
        lblBranch.setText("");
        lblBookTitle.setText("");
        lblAthorName.setText("");
        lblGen.setText("");
        lblBookId.setText("");
        lblStatus.setText("");
        tblBookList.getSelectionModel().clearSelection();
        lblAvSt.setOpacity(100);
    }

    public void setCombSearchValues() {
        CombSearchValues.setItems(FXCollections.observableArrayList("Name", "Branch", "All"));
    }

    private void setDate() {
        lblDate.setText(String.valueOf(LocalDate.now()));
    }

    private void setvaluesFactory() {
        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        columnAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
        columnAStatus.setCellValueFactory(new PropertyValueFactory<>("availabilityStatus"));
        columnGen.setCellValueFactory(new PropertyValueFactory<>("generation"));
        columnBranch.setCellValueFactory(new PropertyValueFactory<>("branch"));

        columnBookId.setCellValueFactory(new PropertyValueFactory<>("Bid"));
        columnBookName.setCellValueFactory(new PropertyValueFactory<>("name"));

    }

    @FXML
    void btnBorrowOnAction(ActionEvent event) {
        long bookId = Long.parseLong(lblBookId.getText());
        BookDTO book = getBooksById(bookId);
        LocalDate borrowedDate = LocalDate.parse(lblDate.getText());
        LocalDate returnedDate = borrowedDate.plusDays(7);
        if (book.getAvailabilityStatus() > 0) {
            boolean isSaved = borrowBO.saveBorrow(new BorrowDTO(book, user, borrowedDate, returnedDate));
            if (isSaved) {
                new Alert(Alert.AlertType.INFORMATION, "SAVE SUCCESS !!!").show();
                loadAllBooks();
                loadAllBorrow();
                resetAll();
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "Out of stock !!!").show();
        }
    }

    public BookDTO getBooksById(long id) {
        List<BookDTO> bookDTOS = borrowBO.getAllBook();
        BookDTO bookDTO = new BookDTO();
        for (BookDTO dto : bookDTOS) {
            if (id == dto.getId()) {
                bookDTO.setId(dto.getId());
                bookDTO.setTitle(dto.getTitle());
                bookDTO.setAuthor(dto.getAuthor());
                bookDTO.setBranchDTO(dto.getBranchDTO());

                bookDTO.setGeneration(dto.getGeneration());
                bookDTO.setAvailabilityStatus(dto.getAvailabilityStatus());
            }
        }
        return bookDTO;
    }

    @FXML
    void btnReturnOnAction(ActionEvent event) {
        long bookId = Long.parseLong(lblBookId.getText());
        List<BorrowDTO> allBorrow = borrowBO.getAllBorrow();
        for (BorrowDTO dto : allBorrow) {
            if (bookId == dto.getBook().getId()) {
                boolean isReturned = borrowBO.returnBook(dto.getId(), dto.getBook());
                if (isReturned) {
                    new Alert(Alert.AlertType.INFORMATION, "RETURN SUCCESS !!!").show();
                    loadAllBooks();
                    loadAllBorrow();
                    resetAll();
                    break;
                }
            }
        }
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        String type = CombSearchValues.getValue();
        List<BookDTO> bookDTOS = borrowBO.getAllBook();
        if (type != null) {
            if (type.equals("Name")) {
                String name = textSearch.getText();
                tblBookList.getItems().clear();
                for (BookDTO dto : bookDTOS) {
                    if (name.equals(dto.getTitle())) {
                        BranchDTO branchDTO = dto.getBranchDTO();
                        tblBookList.getItems().add(new BookTM(dto.getId(), dto.getTitle(), dto.getAuthor(),
                                dto.getAvailabilityStatus(), dto.getGeneration(), branchDTO.getAddress()));
                        lblBranch.setText(branchDTO.getAddress());
                        lblBookTitle.setText(dto.getTitle());
                        lblAthorName.setText(dto.getAuthor());
                        lblGen.setText(dto.getGeneration());
                        lblBookId.setText(String.valueOf(dto.getId()));
                        lblStatus.setText(String.valueOf(dto.getAvailabilityStatus()));
                        lblSearchError.setText("");
                        btnBorrow.setDisable(false);
                        btnReturn.setDisable(true);
                    } else {
                        // new Alert(Alert.AlertType.ERROR,"Can't Find , Please try again !!").show();
                        if (lblBookId.getText() == null || lblBookId.getText().isEmpty()) {
                            lblSearchError.setText("Can't Find, Please try again!!!");
                        }
                    }
                }
                textSearch.setText("");
            } else if (type.equals("Branch")) {
                tblBookList.getItems().clear();
                lblSearchError.setOpacity(100);
                String address = textSearch.getText();
                for (BookDTO dto : bookDTOS) {
                    BranchDTO branchDTO = dto.getBranchDTO();
                    if (address.equals(branchDTO.getAddress())) {
                        tblBookList.getItems().add(new BookTM(dto.getId(), dto.getTitle(), dto.getAuthor(),
                                dto.getAvailabilityStatus(), dto.getGeneration(), branchDTO.getAddress()));
                        lblSearchError.setOpacity(0);
                        textSearch.setText("");
                    } else {

                        // new Alert(Alert.AlertType.ERROR,"Can't Find , Please try again !!").show();
                        lblSearchError.setText("Can't Find , Please try again !!!");
                    }
                }
                resetAll();
            } else if (type.equals("All")) {
                lblSearchError.setText("");
                textSearch.setText("");
                loadAllBooks();
                resetAll();
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "Please select search type").show();
        }

    }

    public void loadAllBooks() {
        tblBookList.getItems().clear();
        List<BookDTO> bookDTOS = borrowBO.getAllBook();
        for (BookDTO dto : bookDTOS) {
            BranchDTO branchDTO = dto.getBranchDTO();
            tblBookList.getItems().add(new BookTM(dto.getId(), dto.getTitle(), dto.getAuthor(),
                    dto.getAvailabilityStatus(), dto.getGeneration(), branchDTO.getAddress()));
        }
    }

    public void loadAllBorrow() {
        tblBrrowed.getItems().clear();
        List<BorrowDTO> allBorrow = borrowBO.getAllBorrow();
        for (BorrowDTO borrowDTO : allBorrow) {
            // System.out.println(borrowDTO.getUser().getName());
            if (user.getId() == borrowDTO.getUser().getId()) {
                tblBrrowed.getItems().add(new BorrowedTM(borrowDTO.getBook().getId(), borrowDTO.getBook().getTitle()));
            }

        }
    }

}
