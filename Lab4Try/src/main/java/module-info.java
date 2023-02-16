module com.example.lab4try {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens com.example.lab4try to javafx.fxml;
    opens com.example.lab4try.controller to javafx.fxml;
    exports com.example.lab4try;
    exports com.example.lab4try.controller;
}