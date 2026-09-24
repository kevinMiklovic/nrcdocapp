package sk.nrcdocapp.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;

/** Spracúva vstupy pohlavia a veku a umožňuje upraviť a skopírovať výsledný text. */
public class IllnessController {
    @FXML private RadioButton maleRadio;
    @FXML private RadioButton femaleRadio;
    @FXML private TextField ageField;
    @FXML private Label ageSuffixLabel;
    @FXML private TextArea generatedTextArea;
    @FXML private Label statusLabel;

    /** Nastaví vzájomne sa vylučujúce pohlavie a povolí v poli veku iba číslice. */
    @FXML
    private void initialize() {
        ToggleGroup genderGroup = new ToggleGroup();
        maleRadio.setToggleGroup(genderGroup);
        femaleRadio.setToggleGroup(genderGroup);

        ageField.setTextFormatter(new TextFormatter<>(change ->
                change.getControlNewText().matches("\\d*") ? change : null));

        genderGroup.selectedToggleProperty().addListener((observable, oldSelection, newSelection) ->
                updateAgeSuffix());
    }

    /** Zostaví opis z vybraného pohlavia a zadaného veku. */
    @FXML
    private void onGenerateText() {
        String age = ageField.getText().trim();
        if (age.isEmpty() || (!maleRadio.isSelected() && !femaleRadio.isSelected())) {
            statusLabel.setText("Vyberte pohlavie a zadajte vek.");
            return;
        }

        String generatedText = maleRadio.isSelected()
                ? age + "-ročný muž."
                : age + "-ročná žena.";
        generatedTextArea.setText(generatedText);
        statusLabel.setText("Text bol vygenerovaný. Môžete ho upraviť.");
    }

    /** Skopíruje aktuálny obsah editovateľného textového poľa do schránky Windows. */
    @FXML
    private void onCopyText() {
        String text = generatedTextArea.getText();
        if (text == null || text.isBlank()) {
            statusLabel.setText("Zatiaľ nie je čo kopírovať.");
            return;
        }

        ClipboardContent content = new ClipboardContent();
        content.putString(text);
        Clipboard.getSystemClipboard().setContent(content);
        statusLabel.setText("Text bol skopírovaný do schránky.");
    }

    private void updateAgeSuffix() {
        if (maleRadio.isSelected()) {
            ageSuffixLabel.setText("-ročný");
        } else if (femaleRadio.isSelected()) {
            ageSuffixLabel.setText("-ročná");
        } else {
            ageSuffixLabel.setText("-ročný/-ročná");
        }
    }
}
