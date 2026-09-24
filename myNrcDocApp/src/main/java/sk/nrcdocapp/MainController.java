package sk.nrcdocapp;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

/** Reaguje na udalosti prvkov definovaných v main-view.fxml. */
public class MainController {
    // FXMLLoader sem vloží Label, ktorý má vo FXML identifikátor statusText.
    @FXML
    private Label statusText;

    // Zavolá sa po kliknutí na tlačidlo, ktoré má túto metódu vo FXML nastavenú v onAction.
    @FXML
    protected void onVerifyButtonClick() {
        statusText.setText("JavaFX funguje.");
    }
}
