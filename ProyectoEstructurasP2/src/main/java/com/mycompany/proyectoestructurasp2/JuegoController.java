/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyectoestructurasp2;

import Clases.Tablero;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
public class JuegoController {

    private static char figuraJugador;
    private boolean empiezaJugador;
    private static Tablero tabla;
    
    
    
    @FXML
    private GridPane tablero;

    @FXML
    private void switchToMenu(ActionEvent event) throws IOException {
        //App.setRoot("Menu");
        App.switchScenes(event, "Menu", 600, 400);
    }

    
    @FXML
    private void initialize() {
    	
        int i = 0;
        int j = 0;
        figuraJugador = SeleccionController.getFiguraJugador();
        empiezaJugador = SeleccionController.isJugadorEmpieza();
        int[][] espacios_ocupados = new int[3][3];
            		
       
        System.out.println("OYE: LA figura es: " + figuraJugador);
        for (i = 0; i < 3; i++) {
        	
            for (j = 0; j < 3; j++) {
            		
            		final int x;
            		final int y;
            		x= i;
            		y = j;
            		StackPane root = new StackPane();
            	
            		
                //Image image = new Image("espol/imagenes/O.png",50,50,false,false);
                //ImageView iv = new ImageView(image);

            		root.setOnMouseClicked(e -> {
            		if(espacios_ocupados[x][y] != 1) {
                	root.getChildren().clear();
                    Image image = new Image("imagenes/" + figuraJugador + ".png", 50, 50, false, false);
                    ImageView iv = new ImageView(image);
                    root.getChildren().add(iv);
                    root.setPrefSize(120, 120);
                    root.setAlignment(Pos.CENTER);
                    espacios_ocupados[x][y] = 1;
            		}
            		else {
            			
            			Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText("Error");
                        alert.setContentText("Celda ya ocupada por figura");
                        alert.showAndWait();
            			
            			
            		}
                });

                tablero.add(root, i, j);
                
            	
            	
            	
            	
            	
            }
        }

    }

        
    /*    
    public static VBox ingresarImagen(char figura){
        VBox root = new VBox();
        //Image image = new Image("espol/imagenes/O.png",50,50,false,false);
        //ImageView iv = new ImageView(image);
        
        root.setOnMouseClicked(e -> {
            Image image = new Image("imagenes/" + figura + ".png", 50, 50, false, false);
            ImageView iv = new ImageView(image);
            root.getChildren().add(iv);
            root.setPrefSize(120, 120);
            root.setAlignment(Pos.CENTER);
        });
        
        return root;
    }
    */
    
    }
