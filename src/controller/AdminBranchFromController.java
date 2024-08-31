package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import tm.BranchTM;

import java.time.LocalDate;
import java.util.List;

import bo.BOFactory;
import bo.custom.BranchBO;
import controller.util.ValidationController;
import dto.BranchDTO;

public class AdminBranchFromController {

    @FXML
    private TextField textId;

    @FXML
    private TextField textAddress;

    @FXML
    private DatePicker OpenedDayPiker;

    @FXML
    private TableView<BranchTM> tblView;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnAddNew;

    @FXML
    private TableColumn<BranchTM, Long> columnId;

    @FXML
    private TableColumn<BranchTM, String> columnAddress;

    @FXML
    private Button btnPluse;

    @FXML
    private TableColumn<BranchTM, LocalDate> columnOpenDate;

    private BranchBO branchBO = (BranchBO) BOFactory.getBoFactory().getBO(BOFactory.BOType.BRANCH);

    public void initialize() {
        setValuesFactory();
        loadAllBranch();
        resetAll();
        tblView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                btnAddNew.setOpacity(0);
                btnAddNew.setDisable(true);
                textId.setOpacity(100);
                btnDelete.setDisable(false);
                btnSave.setDisable(false);
                btnSave.setText("Update");
                textAddress.setDisable(false);
                OpenedDayPiker.setDisable(false);
                btnPluse.setDisable(false);
                btnPluse.setOpacity(100);

                textId.setText(String.valueOf(newValue.getId()));
                textAddress.setText(newValue.getAddress());
                OpenedDayPiker.setValue(newValue.getOpenedDate());

            } else {
                btnAddNew.setOpacity(100);
                btnAddNew.setDisable(false);
                btnSave.setText("Save");
                resetAll();
            }
        });
    }

    private void setValuesFactory() {
        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        columnOpenDate.setCellValueFactory(new PropertyValueFactory<>("openedDate"));
    }

    public void resetAll() {
        textId.setOpacity(0);
        textAddress.setDisable(true);
        OpenedDayPiker.setDisable(true);
        btnSave.setDisable(true);
        btnDelete.setDisable(true);
        textAddress.clear();
        OpenedDayPiker.setValue(null);
        textId.clear();
        btnPluse.setDisable(true);
        btnPluse.setOpacity(0);
        tblView.getSelectionModel().clearSelection();
    }

    @FXML
    void btnPlusOnAction(ActionEvent event) {
        btnAddNew.setOpacity(100);
        btnAddNew.setDisable(false);
        resetAll();
    }

    @FXML
    void btnAddNewOnAction(ActionEvent event) {
        textAddress.setDisable(false);
        OpenedDayPiker.setDisable(false);
        btnSave.setDisable(false);
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        boolean isDelete = branchBO.deleteBranch(Long.parseLong(textId.getText()));
        if (isDelete) {
            loadAllBranch();
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        LocalDate opDate = OpenedDayPiker.getValue();
        String address = textAddress.getText();
        if (textAddress.getText().isEmpty() || textId.getText().isEmpty()) {
            boolean validate = ValidationController.address(address);
            boolean validate1 = ValidationController.id(textId.getText());

            if (validate || validate1) {
                // save branch
                if ("Save".equals(btnSave.getText())) {
                    boolean isSaved = branchBO.saveBranch(new BranchDTO(address, opDate));
                    if (isSaved) {
                        new Alert(Alert.AlertType.INFORMATION, "SAVE SUCCESS !!!").show();
                        textAddress.clear();
                        OpenedDayPiker.setValue(null);
                        loadAllBranch();
                    }
                }
                // update branch
                else {
                    long id = Long.parseLong(textId.getText());
                    boolean isUpdate = branchBO.updateBranch(new BranchDTO(id, address, opDate));
                    if (isUpdate) {
                        new Alert(Alert.AlertType.INFORMATION, "UPDATE SUCCESS !!!").show();
                        textAddress.clear();
                        OpenedDayPiker.setValue(null);
                        loadAllBranch();
                    }
                }
            }
        }
    }

    public void loadAllBranch() {
        tblView.getItems().clear();
        List<BranchDTO> branches = branchBO.getAllBranch();
        for (BranchDTO branch : branches) {
            tblView.getItems().add(new BranchTM(branch.getId(), branch.getAddress(), branch.getOpenedDate()));
        }
    }
}
