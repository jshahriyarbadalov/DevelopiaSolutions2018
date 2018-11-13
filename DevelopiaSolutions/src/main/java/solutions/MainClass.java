package solutions;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainClass extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("view/Login.fxml"));

        Parent root = loader.load();
        stage.setTitle("Login Pəncərəsi");
        stage.setScene(new Scene(root));
        stage.setResizable(false);

       stage.show();
    }
}
