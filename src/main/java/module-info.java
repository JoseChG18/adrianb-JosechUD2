module com.example.proyectosw {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires com.fasterxml.jackson.dataformat.csv;
    requires com.fasterxml.jackson.dataformat.xml;
    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires com.almasb.fxgl.all;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.databind;
    requires jbcrypt;

    opens com.example.proyectosw to javafx.fxml;
    exports com.example.proyectosw;

    exports com.example.proyectosw.model;
    opens com.example.proyectosw.model;

    exports com.example.proyectosw.Controller;
    opens com.example.proyectosw.Controller to javafx.fxml;
}