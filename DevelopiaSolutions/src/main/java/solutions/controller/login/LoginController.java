package solutions.controller.login;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class LoginController implements Initializable {




    // bu komponent login pəncərəsində hər hansı məlumatın istifadəçiyə çatdırılması üçündür.
    @FXML
    private Label warningsLabel;

    @FXML
    private TextField usernameTF;

    @FXML
    private PasswordField passwordPF;

    @FXML
    private Label headerTextLabel;

    // ready
    private void writeMessage(String message) {
        warningsLabel.setText(message);

    }


    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("LoginController:initialize");
    }


    @FXML
    void usernameTFKeyPressed(KeyEvent event) {



    }


    // ready
    public void loginProcess() throws SQLException {




    }


    @FXML
    void passwordPFKeyPressed(KeyEvent event) {

        if (event.getCode().equals(KeyCode.ENTER)) {
            try {

            } catch ( Exception e) {
                e.printStackTrace();
            }
        }
    }

}
