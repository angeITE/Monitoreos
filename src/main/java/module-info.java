module com.example.monitoreos {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;

    opens com.example.monitoreos to javafx.fxml;
    exports com.example.monitoreos;
}