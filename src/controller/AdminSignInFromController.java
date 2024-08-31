package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminSignInFromController {

    @FXML
    private AnchorPane root;

    @FXML
    private TextField textUserName;

    @FXML
    private PasswordField textPassword;

    @FXML
    private Button btnBack;

    @FXML
    private ToggleButton btnToggle;

    @FXML
    private Label lableUserNameWarning;

    @FXML
    private Label lablePasswordWarning;
    private String name = "chathura";
    private String pw = "1234";

    public void initialize() {
        String imagePath = "/assest/image/Hide_Password.png";
        btnToggle.setStyle("-fx-background-image: url('" + imagePath
                + "'); -fx-background-size: 50% 50%; -fx-background-position: center; -fx-background-repeat: no-repeat;");
    }

    @FXML
    void backBtnOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/main_form.fxml"));
        Scene scene = new Scene(anchorPane);

        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("User Sign_in page");
        stage.centerOnScreen();
    }

    @FXML
    void btnToggleOnAction(ActionEvent event) {
        if (btnToggle.isSelected()) {
            textPassword.setPromptText(textPassword.getText());
            textPassword.setText("");
            String imagePath = "/assest/image/Show_password.png";
            btnToggle.setStyle("-fx-background-image: url('" + imagePath
                    + "'); -fx-background-size: 50% 50%; -fx-background-position: center; -fx-background-repeat: no-repeat;");
        } else {
            textPassword.setText(textPassword.getPromptText());
            textPassword.setPromptText("");
            String imagePath = "/assest/image/Hide_Password.png";
            btnToggle.setStyle("-fx-background-image: url('" + imagePath
                    + "'); -fx-background-size: 50% 50%; -fx-background-position: center; -fx-background-repeat: no-repeat;");
        }
    }

    @FXML
    void signInBtnOnAction(ActionEvent event) throws IOException {
        String userName = textUserName.getText();
        String password = textPassword.getText();
        if (userName.equals(name)) {
            lableUserNameWarning.setText("");
            if (password.equals(pw)) {
                AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/admin_buttonboard_form.fxml"));
                Scene scene = new Scene(anchorPane);
                Stage stage = (Stage) root.getScene().getWindow();
                stage.setScene(scene);
                stage.setTitle("Admin page");
                stage.centerOnScreen();
            } else {
                lablePasswordWarning.setText("Wrong Password !! , Please try again !!!");
            }
        } else {
            lableUserNameWarning.setText("Wrong user name !! , Please try again !!!");
        }
    }

    @FXML
    void backBtnMouseEnterOnAction(MouseEvent event) {
        btnBack.setStyle("-fx-background-color: white;-fx-border-color: black; -fx-text-fill: black;");
    }

    @FXML
    void backBtnMouseExitOnAction(MouseEvent event) {
        btnBack.setStyle("-fx-background-color: #1e90ff; -fx-border-color: white; -fx-text-fill: white;");
    }

}