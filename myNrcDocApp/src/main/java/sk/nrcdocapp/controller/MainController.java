package sk.nrcdocapp.controller;

import javafx.css.PseudoClass;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.animation.ScaleTransition;
import javafx.util.Duration;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** Prepína samostatné obrazovky zobrazené pod spoločným horným panelom. */
public class MainController {
    private static final PseudoClass SELECTED = PseudoClass.getPseudoClass("selected");
    private static final Map<String, String> SCREEN_PATHS = Map.of(
            "home", "/sk/nrcdocapp/views/home-view.fxml",
            "illness", "/sk/nrcdocapp/views/illness-view.fxml",
            "history", "/sk/nrcdocapp/views/history-view.fxml",
            "exam", "/sk/nrcdocapp/views/exam-view.fxml",
            "conclusion", "/sk/nrcdocapp/views/conclusion-view.fxml"
    );

    @FXML private Button homeButton;
    @FXML private Button illnessButton;
    @FXML private Button historyButton;
    @FXML private Button examButton;
    @FXML private Button conclusionButton;
    @FXML private StackPane contentPane;

    // Načítané obrazovky uchovávame, aby sa po prepnutí nestratil rozpracovaný obsah formulára.
    private final Map<String, Parent> loadedScreens = new HashMap<>();
    private List<Button> navigationButtons;
    private ScaleTransition pressAnimation;
    private Button animatingButton;

    /** Pripraví navigáciu a zobrazí úvodnú obrazovku. */
    @FXML
    private void initialize() throws IOException {
        navigationButtons = List.of(homeButton, illnessButton, historyButton, examButton, conclusionButton);
        showScreen("home", homeButton);
    }

    /** Po kliknutí načíta alebo znovu zobrazí príslušnú obrazovku. */
    @FXML
    private void onNavigationClick(javafx.event.ActionEvent event) throws IOException {
        Button button = (Button) event.getSource();
        animatePress(button);
        showScreen(button.getId(), button);
    }

    /** Na okamih zmenší tlačidlo a vráti ho do pôvodnej veľkosti. */
    private void animatePress(Button button) {
        // Pri rýchlom ďalšom kliknutí ukončíme predchádzajúcu animáciu v čistej polohe.
        if (pressAnimation != null) {
            pressAnimation.stop();
            animatingButton.setScaleX(1.0);
            animatingButton.setScaleY(1.0);
        }

        animatingButton = button;
        pressAnimation = new ScaleTransition(Duration.millis(90), button);
        pressAnimation.setToX(0.96);
        pressAnimation.setToY(0.96);
        pressAnimation.setAutoReverse(true);
        pressAnimation.setCycleCount(2);
        pressAnimation.setOnFinished(event -> {
            button.setScaleX(1.0);
            button.setScaleY(1.0);
        });
        pressAnimation.playFromStart();
    }

    private void showScreen(String screenId, Button selectedButton) throws IOException {
        Parent screen = loadedScreens.get(screenId);
        if (screen == null) {
            String resourcePath = SCREEN_PATHS.get(screenId);
            if (resourcePath == null) {
                throw new IllegalArgumentException("Neznáma obrazovka: " + screenId);
            }

            FXMLLoader loader = new FXMLLoader(getClass().getResource(resourcePath));
            screen = loader.load();
            loadedScreens.put(screenId, screen);
        }

        contentPane.getChildren().setAll(screen);
        for (Button button : navigationButtons) {
            button.pseudoClassStateChanged(SELECTED, button == selectedButton);
        }
    }
}
