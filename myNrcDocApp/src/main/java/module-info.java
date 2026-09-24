// Modul deklaruje závislosti aplikácie a pravidlá prístupu medzi modulmi.
module sk.nrcdocapp {
    // Štandardné JavaFX ovládacie prvky a grafické jadro.
    requires javafx.controls;
    // Podpora načítania obrazovky zo súboru FXML.
    requires javafx.fxml;

    // FXMLLoader potrebuje reflexiou vytvoriť controller a napojiť jeho polia a metódy.
    opens sk.nrcdocapp to javafx.fxml;
    // Verejné triedy balíka sprístupní ostatným modulom, napríklad JavaFX spúšťaču.
    exports sk.nrcdocapp;
}
