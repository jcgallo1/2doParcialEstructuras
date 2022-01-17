package com.mycompany.proyectoestructurasp2;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.GridPane;

public class SeleccionControllerPlayerVsPlayer {

    @FXML
    private Label seleccionaLabel, empiezaLabel,seleccionaLabel1;

    @FXML
    private GridPane gridSeleccion;

    @FXML
    private GridPane gridEmpezar;

    @FXML
    private RadioButton xRadio;

    @FXML
    private RadioButton oRadio;

    @FXML
    private RadioButton jugador1Radio;
    
    @FXML
    private RadioButton jugador2Radio;

    
    private static char figuraJugador1;
    private static char figuraJugador2;

    private static boolean jugador1Empieza;   

    @FXML
    private void switchToJugar(ActionEvent event) throws IOException {
        //App.setRoot("secondary");
        if (!seleccionValida()) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Error");
            alert.setContentText("Selecciona al menos una opción de cada campo");
            alert.showAndWait();

        } else {
            System.out.println("Figura del jugador 1: " + figuraJugador1);
            System.out.println("Figura del jugador 2: " + figuraJugador2);
            System.out.println("Empieza el jugador 1: " + jugador1Empieza);
            App.switchScenes(event, "JuegoPlayerVsPlayer", 600, 400);
            //App.setRoot("JuegoPlayerVsPlayer");
        }
    }

    @FXML
    private void switchToMenu(ActionEvent event) throws IOException {
        //App.setRoot("Menu");
        App.switchScenes(event, "Modo", 280, 400);
    }

    //Acciones que se encargan de deseleccionar la otra opción si selecciona la contraria (ejem: si selecciona 'X' se deselecciona 'O')
    @FXML
    protected void setJ1Radio() {
        if (!jugador1Radio.isSelected()) {
            jugador1Radio.setSelected(true);
            jugador2Radio.setSelected(false);
        } else if (jugador2Radio.isSelected()) {
            jugador2Radio.setSelected(false);
            jugador1Radio.setSelected(true);
        }
        setJugador1Empieza(true);
    }

    @FXML
    protected void setJ2Radio() {
        if (!jugador2Radio.isSelected()) {
            jugador2Radio.setSelected(true);
            jugador1Radio.setSelected(false);
        } else if (jugador1Radio.isSelected()) {
            jugador1Radio.setSelected(false);
            jugador2Radio.setSelected(true);
        }
        setJugador1Empieza(false);
    }

    @FXML
    protected void setXRadio() {
        if (!xRadio.isSelected()) {
            xRadio.setSelected(true);
            oRadio.setSelected(false);
        } else if (oRadio.isSelected()) {
            oRadio.setSelected(false);
            xRadio.setSelected(true);
        }
        setFiguraJugador1('X');
        setFiguraJugador2('O');
        
    }

    @FXML
    protected void setORadio() {
        if (!oRadio.isSelected()) {
            oRadio.setSelected(true);
            xRadio.setSelected(false);
        } else if (xRadio.isSelected()) {
            xRadio.setSelected(false);
            oRadio.setSelected(true);
        }
        setFiguraJugador1('O');
        setFiguraJugador2('X');
    }

    //Permite ver que no haya campos sin seleccionar
    public boolean seleccionValida() {

        return ((jugador1Radio.isSelected() || jugador2Radio.isSelected()) && (xRadio.isSelected() || oRadio.isSelected()));

    }

    public static char getFiguraJugador1() {
        return figuraJugador1;
    }

    public static void setFiguraJugador1(char figuraJugador1) {
        SeleccionControllerPlayerVsPlayer.figuraJugador1 = figuraJugador1;
    }

    public static boolean isJugador1Empieza() {
        return jugador1Empieza;
    }

    public static void setJugador1Empieza(boolean jugador1Empieza) {
        SeleccionControllerPlayerVsPlayer.jugador1Empieza = jugador1Empieza;
    }

	public static char getFiguraJugador2() {
		return figuraJugador2;
	}

	public static void setFiguraJugador2(char figuraJugador2) {
		SeleccionControllerPlayerVsPlayer.figuraJugador2 = figuraJugador2;
	}
    
    
    

}
