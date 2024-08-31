package controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class UserButtonBoardFromController {

    @FXML
    private AnchorPane rootMain;

    @FXML
    private AnchorPane root;

    @FXML
    private JFXButton btnBook;

    @FXML
    private JFXButton btnBookHis;

    public void initialize() throws IOException {
        Parent rootNode = FXMLLoader.load(getClass().getResource("/view/user_books_form.fxml"));
        root.getChildren().clear();
        root.getChildren().add(rootNode);
    }

    @FXML
    void btnBookHisOnAction(ActionEvent event) throws IOException {
        Parent rootNode = FXMLLoader.load(getClass().getResource("/view/books_histrory_form.fxml"));
        root.getChildren().clear();
        root.getChildren().add(rootNode);
    }

    @FXML
    void btnBookOnAction(ActionEvent event) throws IOException {
        Parent rootNode = FXMLLoader.load(getClass().getResource("/view/user_books_form.fxml"));
        root.getChildren().clear();
        root.getChildren().add(rootNode);
    }

    @FXML
    void imgEditProfilOnAction(MouseEvent event) throws IOException {
        Parent rootNode = FXMLLoader.load(getClass().getResource("/view/user_profile_form.fxml"));
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
