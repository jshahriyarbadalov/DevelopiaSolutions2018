package solutions.main;



import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import solutions.connection.DB;
import solutions.util.Methods;

import javax.swing.*;
import java.io.IOException;

// bu sinif proqramin esas giris sinifidir, burada main metod yerlesir
// bu sinifin daxilinde olan start metodu lanch metodunun cagirilmasindan sonra cagirilir
public class MainClass extends Application {
    // Burada baza ilə işləyən əsas sinif olan DB sinifindən obyekt yaradılır. (Yaranmış mövcud obyektlə əlaqə qurulur)
    private static DB db = DB.getInstance();

    static int module = 0;

    // main metod başlayır.
    public static void main(String[] args) {



        boolean isSuccessfulConnection = false;
        isSuccessfulConnection = db.isSuccessfulConnection();

        if (isSuccessfulConnection) {
            launch(args);
        } else {
            // Əgər bazaya qoşulmaqda problem olubsa, onda bu mesajı versin.
            JOptionPane.showMessageDialog(null, "Bazaya qoşulmaqda problem oldu");
            System.out.println("Bazaya qoşulmaqda problem oldu");
        }
    }


    // Bu metod main metoddan sonra ilk başlayan metoddur.
    @Override
    public void start(Stage stage) throws Exception {

            openLoginWindow(stage);


    }


    public void openLoginWindow(Stage stage) throws IOException {

// Burada fxml fayldan nümunə yüklənir.
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/login/Login.fxml"));
        // Yüklənmiş olan fxml fayldan əsas komponentin obyekti qəbul edilir.
        Parent root = loader.load();
        stage.setTitle("İştifadəçi girişi");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        // Yüklənən pəncərənin uyğun kontroller sinifinin obyekti əldə edilir.


        // Pəncərənin ikonu təyin edilir.


// Login pəncərənin bağlanmasına cəhd göstərilən zaman
        // icra olunan kodlar aşağıda verilmişdir
        stage.setOnCloseRequest(event -> {


                boolean close = Methods.confirmDialog("Bağlamağa əminsiniz?");
                if (close) {
                    System.out.println("Login pəncərəsi bağlandı...");

                    try {
                        db.disconnect();


                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                } else {
                    event.consume();
                }


        });
        stage.show();

    }



}
