package sk.nrcdocapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/** Hlavná JavaFX trieda, ktorá zostaví a zobrazí okno aplikácie. */
public class NrcDocApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // Načíta rozloženie okna z FXML súboru v rovnakom balíku.
        FXMLLoader fxmlLoader = new FXMLLoader(NrcDocApp.class.getResource("main-view.fxml"));
        // Scene obsahuje vizuálny obsah, ktorý sa zobrazí v okne.
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        // Stage predstavuje hlavné okno aplikácie.
        stage.setTitle("NrcDocApp");
        stage.setScene(scene);
        stage.show();
    }
}
