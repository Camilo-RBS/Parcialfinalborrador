module org.poo.borradorparacialpoo {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.poo.borradorparacialpoo to javafx.fxml;
    exports org.poo.borradorparacialpoo;
}