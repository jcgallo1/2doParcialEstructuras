module com.mycompany.proyectoestructuras2p {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.mycompany.proyectoestructuras2p to javafx.fxml;
    exports com.mycompany.proyectoestructuras2p;
}
