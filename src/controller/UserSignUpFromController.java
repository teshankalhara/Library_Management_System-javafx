package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

import bo.BOFactory;
import bo.custom.UserBO;
import controller.util.ValidationController;
import dto.UserDTO;

public class UserSignUpFromController {
    @FXML
    private AnchorPane root;

    @FXML
    private TextField textName;

    @FXML
    private PasswordField textPassword;

    @FXML
    private Button btnSignIn;

    @FXML
    private TextField textEmail;
    @FXML
    private Label lblUserNameWarning;
    UserBO userBO = (UserBO) BOFactory.getBoFactory().getBO(BOFactory.BOType.USER);

    @FXML
    void btnSignInOnAction(ActionEvent event) throws IOException {

        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/user_sign_in.fxml"));
        Scene scene = new Scene(anchorPane);

        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("User Sign_in page");
        stage.centerOnScreen();
    }

    @FXML
    void btnSignUpOnAction(ActionEvent event) {
        String name = textName.getText();
        String email = textEmail.getText();
        String password = textPassword.getText();
        boolean isValidate = ValidationController.password(password);
        boolean isValidate1 = ValidationController.email(email);

        if (isValidate || isValidate1) {
            List<UserDTO> users = userBO.getAllUser();
            for (UserDTO user : users) {
                if (name.equals(user.getName())) {
                    textName.setStyle("-fx-border-color: red");
                    lblUserNameWarning.setText("This user name already taken !!! , please try again !!");
                    return;
                }
            }
            lblUserNameWarning.setText("");
            UserDTO userDTO = new UserDTO(name, email, password);
            boolean isSaved = userBO.saveUser(userDTO);
            if (isSaved) {
                new Alert(Alert.AlertType.INFORMATION, "Sign Up Success!! , please Sign IN now !!!").show();
                btnSignIn.fire();
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "Invalid Password OR EMAIL  !!!").show();
        }
    }

    @FXML
    void mouseEnterOnAction(MouseEvent event) {
        btnSignIn.setStyle("-fx-background-color: white;-fx-border-color: black; -fx-text-fill: black;");
    }

    @FXML
    void mouseExitOnAction(MouseEvent event) {
        btnSignIn.setStyle("-fx-background-color: #1e90ff; -fx-border-color: white; -fx-text-fill: white;");
    }

}