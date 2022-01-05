/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyectoestructurasp2;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class JuegoController {

    private static char figuraJugador;
    private static boolean empiezaJugador;

    @FXML
    private GridPane tablero;

    @FXML
    private void switchToMenu(ActionEvent event) throws IOException {
        //App.setRoot("Menu");
        App.switchScenes(event, "Menu", 600, 400);
    }

    @FXML
    private void initialize() {
        int i, j;
        figuraJugador = SeleccionController.getFiguraJugador();
        empiezaJugador = SeleccionController.isJugadorEmpieza();

        System.out.println("OYE: LA figura es: " + figuraJugador);
        for (i = 0; i < 3; i++) {
            for (j = 0; j < 3; j++) {

                VBox root = new VBox();
                //Image image = new Image("espol/imagenes/O.png",50,50,false,false);
                //ImageView iv = new ImageView(image);

                root.setOnMouseClicked(e -> {

                    Image image = new Image("imagenes/" + figuraJugador + ".png", 50, 50, false, false);
                    ImageView iv = new ImageView(image);
                    root.getChildren().add(iv);
                    root.setPrefSize(120, 120);
                    root.setAlignment(Pos.CENTER);
                });

                tablero.add(root, i, j);

            }
        }

    }
}
