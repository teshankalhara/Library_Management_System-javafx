package controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminButtonBoardFromController {

    @FXML
    private AnchorPane rootMain;

    @FXML
    private AnchorPane root;

    @FXML
    private JFXButton btnBooks;

    @FXML
    private JFXButton btnBranch;

    @FXML
    private Label lblMemberCount;

    public void initialize() throws IOException {
        Parent rootNode = FXMLLoader.load(getClass().getResource("/view/admin_books_form.fxml"));
        root.getChildren().clear();
        root.getChildren().add(rootNode);
    }

    @FXML
    void btnBooksOnAction(ActionEvent event) throws IOException {
        Parent rootNode = FXMLLoader.load(getClass().getResource("/view/admin_books_form.fxml"));
        root.getChildren().clear();
        root.getChildren().add(rootNode);
    }

    @FXML
    void btnBranchOnAction(ActionEvent event) throws IOException {
        Parent rootNode = FXMLLoader.load(getClass().getResource("/view/admin_branch_form.fxml"));
        root.getChildren().clear();
        root.getChildren().add(rootNode);
    }

    @FXML
    void imgHomeNavi(MouseEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/main_form.fxml"));
        Scene scene = new Scene(anchorPane);

        Stage stage = (Stage) rootMain.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("User Sign_in page");
        stage.centerOnScreen();
    }

}