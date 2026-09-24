// Modul deklaruje závislosti aplikácie a pravidlá prístupu medzi modulmi.
module sk.nrcdocapp {
    // Štandardné JavaFX ovládacie prvky a grafické jadro.
    requires javafx.controls;
    // Podpora načítania obrazovky zo súboru FXML.
    requires javafx.fxml;

    // FXMLLoader potrebuje reflexiou vytvárať controllery v hlavnom balíku.
    opens sk.nrcdocapp to javafx.fxml;
    // Sprístupní JavaFX controller triedy pre samostatné obrazovky.
    opens sk.nrcdocapp.controller to javafx.fxml;
    // Verejné triedy balíka sprístupní ostatným modulom, napríklad JavaFX spúšťaču.
    exports sk.nrcdocapp;
}
