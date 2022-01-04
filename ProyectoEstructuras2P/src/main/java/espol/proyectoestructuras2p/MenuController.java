package espol.proyectoestructuras2p;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;


public class MenuController {
    
    
    
    @FXML
    private void switchToJugar(ActionEvent event) throws IOException {
        //App.setRoot("secondary");
        App.switchScenes(event, "Juego", 600, 400);
    }
    
    
}
    