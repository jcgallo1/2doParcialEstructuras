module espol.proyectoestructuras2p {
    requires javafx.controls;
    requires javafx.fxml;

    opens espol.proyectoestructuras2p to javafx.fxml;
    exports espol.proyectoestructuras2p;
}
