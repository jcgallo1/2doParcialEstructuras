
package com.mycompany.proyectoestructurasp2;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.GridPane;

public class ModoController {
    @FXML
    private Label seleccionaLabel;

    @FXML
    private GridPane gridSeleccion; 

    @FXML
    private RadioButton PlayerVsPlayerRadio;

    @FXML
    private RadioButton PlayerVsCPURadio;

    @FXML
    private RadioButton CPUVsCPURadio;
    
    private static char ModoSelected;
    private static boolean jugadorEmpieza;
     
    @FXML
    private void switchToJugar(ActionEvent event) throws IOException {        
        if (!seleccionValida()) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Error");
            alert.setContentText("Selecciona al menos una opción de cada campo");
            alert.showAndWait();

        } else if(PlayerVsCPURadio.isSelected()){           
            App.switchScenes(event, "Seleccion", 280, 400);
            //App.setRoot("Selecccion");
            
        } else if(PlayerVsPlayerRadio.isSelected()){           
            App.switchScenes(event, "Seleccion", 280, 400);
            //App.setRoot("Selecccion");
            
        } else if(CPUVsCPURadio.isSelected()){           
            App.switchScenes(event, "Seleccion", 280, 400);
            //App.setRoot("Selecccion");
        }
        
    }
    @FXML
    private void switchToMenu(ActionEvent event) throws IOException {
        //App.setRoot("Menu");
        App.switchScenes(event, "Menu", 600, 400);
    }

    //Acciones que se encargan de deseleccionar la otra opción si selecciona la contraria
    @FXML
    protected void setPlayerVsCPURadio() {
        if (!PlayerVsCPURadio.isSelected()) {
            PlayerVsCPURadio.setSelected(true);
            PlayerVsPlayerRadio.setSelected(false);
            CPUVsCPURadio.setSelected(false);
        } else if (PlayerVsPlayerRadio.isSelected() || CPUVsCPURadio.isSelected()) {
            PlayerVsPlayerRadio.setSelected(false);
            CPUVsCPURadio.setSelected(false);
            PlayerVsCPURadio.setSelected(true);
        }        
    }

    @FXML
    protected void setPlayerVsPlayer() {
        if (!PlayerVsPlayerRadio.isSelected()) {
            PlayerVsCPURadio.setSelected(false);
            PlayerVsPlayerRadio.setSelected(true);
            CPUVsCPURadio.setSelected(false);
        } else if (PlayerVsCPURadio.isSelected() || CPUVsCPURadio.isSelected()) {
            PlayerVsPlayerRadio.setSelected(true);
            CPUVsCPURadio.setSelected(false);
            PlayerVsCPURadio.setSelected(false);
        }        
    }

    @FXML
    protected void setCPUVsCPURadio() {
        if (!CPUVsCPURadio.isSelected()) {
            PlayerVsCPURadio.setSelected(false);
            PlayerVsPlayerRadio.setSelected(false);
            CPUVsCPURadio.setSelected(true);
        } else if (PlayerVsCPURadio.isSelected() || PlayerVsPlayerRadio.isSelected()) {
            PlayerVsPlayerRadio.setSelected(false);
            CPUVsCPURadio.setSelected(true);
            PlayerVsCPURadio.setSelected(false);
        }       
    }
   

    //Permite ver que no haya campos sin seleccionar
    public boolean seleccionValida() {

        return (PlayerVsCPURadio.isSelected() || PlayerVsPlayerRadio.isSelected()  || CPUVsCPURadio.isSelected());

    }

    public static char getModoSelected() {
        return ModoSelected;
    }
       

}

          

