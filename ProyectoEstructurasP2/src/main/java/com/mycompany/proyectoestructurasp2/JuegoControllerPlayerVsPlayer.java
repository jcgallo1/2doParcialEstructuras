package com.mycompany.proyectoestructurasp2;

import Clases.Tablero;
import Clases.fichas;
import TDA.Tree;
import TDA.TreeComparator;

import java.io.IOException;
import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class JuegoControllerPlayerVsPlayer<T>{

    private static char figuraJugador1;
    private static char figuraJugador2;
    private boolean empiezaJugador1;
    
    int[][] espacios_ocupados;
    
    
    @FXML
    private GridPane tablero;
    
    @FXML
    private Label buena;
    
    @FXML
    private Label suerte;

    @FXML
    private void switchToMenu(ActionEvent event) throws IOException {
        //App.setRoot("Menu");
        App.switchScenes(event, "Menu", 600, 400);
    }

    
    @FXML
    private void initialize() {
    	
        int i = 0;
        int j = 0;
        int numero = 1;
        Tablero tableroJuego = new Tablero();
        
        figuraJugador1 = SeleccionControllerPlayerVsPlayer.getFiguraJugador1();
        figuraJugador2 = SeleccionControllerPlayerVsPlayer.getFiguraJugador2();
        empiezaJugador1 = SeleccionControllerPlayerVsPlayer.isJugador1Empieza();
        espacios_ocupados = new int[3][3];
        fichas fichaJ1 = null;
        fichas fichaJ2 = null;
        
        if(figuraJugador1 == 'X') {
            fichaJ1 = fichas.X;
            fichaJ2 = fichas.O;
        }
        
        if(figuraJugador1 == 'O') {
            fichaJ1 = fichas.O;
            fichaJ2 = fichas.X;
        }
        
        System.out.println("OYE: LA figura del J1 es: " + figuraJugador1);
        System.out.println("OYE: LA figura del J2 es: " + figuraJugador2);
        
        tableroJuego.mostrarTablero();
        TreeComparator<Tablero> cmp = Tablero::compareTo;
                
    }
                                   
    //Metodo que muestra los espacios ocupados en la consola representado por 1.
    public void mostrarEspacios() {
    	
    	for(int i = 0; i < 3; i++) {
            
            System.out.println(espacios_ocupados[i][0]+"  "+espacios_ocupados[i][1]+ "  "+ espacios_ocupados[i][2]);    		
    	}    	
    	System.out.println("                  ");
    }
    
    //Metodo que crea un arbol del tablero dado de las posibles posibilidades de colocar ficha del siguiente turno 
    public void arbolOponente(Tree<Tablero> tableroActual, fichas fichaOponente, fichas fichaJugador, Comparator<Tree<Tablero>> cmp) {
    	
    	Tablero tableroA =  tableroActual.getRoot().getContent();
    	
    	int contador = 0;
    	Tablero tableroCopia= new Tablero();
    	
    	
    	PriorityQueue<Tree<Tablero>> hijos = new PriorityQueue<Tree<Tablero>>(cmp);
    	tableroActual.getRoot().setChildren(hijos);
    	for(int i=0;i<3;i++) {    		
            for(int j=0;j<3;j++) {
                
    		tableroCopia = tableroA;
    			
    		if(!(tableroCopia.isCeldafichas(i, j))) {
    				
                    tableroCopia.setFichaT(i, j, fichaOponente);
                    int utilidad = tableroCopia.utilidadTablero(fichaJugador, fichaOponente);
                    tableroCopia.setUtilidad(utilidad);
                    int movx = i;
                    int movy = j;
    				    			
                    Tablero t = new Tablero();
                    tableroCopia.copiarTablero(t);
                    t.setFichaT(i, j, fichaOponente);
                    tableroCopia.copiarUtilidad(t);
                    t.setPosibleX(movx);
                    t.setPosibleY(movy);
					                        
                    tableroActual.getRoot().getChildren().add(new Tree<Tablero>(t));
                    System.out.println("Tablero hijo del segundo turno: ");
                    tableroCopia.mostrarTablero();
    		
                    System.out.println("En x: "+t.getPosibleX()+","+"En y: "+t.getPosibleY());
                    tableroCopia.setFichaT(i, j, fichas.VACIO);
                    contador++;    				    				
    		}
            }
    	}
    	
    	if(contador!=0) {
    	Tree<Tablero> tableroMenor = hijos.peek();
    	Tablero tablerito = tableroMenor.getRoot().getContent();
    	int utilidadMinima = tablerito.getUtilidad();
    	tableroActual.getRoot().getContent().setUtilidad(utilidadMinima);
    	System.out.println("Tablero con menor utilidad de este grupo: "+"con utilidad: "+tablerito.getUtilidad() );
    	tablerito.mostrarTablero();
    	
    	}    	    	    	        	    	
    }
    
    public Tree<Tablero> arbolJugador(Tablero tableroJugador,fichas jugador, fichas oponente,Comparator<Tree<Tablero>> cmp) {
    	
    	Tree<Tablero> arbolPrincipal = new Tree<Tablero>(tableroJugador);
    	
    	Tablero copiaTablero = tableroJugador;
    	
    	
    	System.out.println("Raiz: ");
    	copiaTablero.mostrarTablero();
    	Tablero tableroCopia= new Tablero();
    	PriorityQueue<Tree<Tablero>> hijos2 = new PriorityQueue<Tree<Tablero>>(cmp.reversed());
    	arbolPrincipal.getRoot().setChildren(hijos2);
    	for(int i=0;i<3;i++) {    
            for(int j=0;j<3;j++) {
                
                tableroCopia = copiaTablero;
                if(!(tableroCopia.isCeldafichas(i, j))) {
    					    					
                    tableroCopia.setFichaT(i, j, jugador);
                    System.out.println("Tablero de posible movimiento: ");
                    tableroCopia.mostrarTablero();
                    Tablero t = new Tablero();
                    int movx = i;
                    int movy = j;
        				
                    tableroCopia.copiarMov(t);
                    tableroCopia.copiarTablero(t);
                    t.setPosibleX(movx);
                    t.setPosibleY(movy);
    					
                    t.setFichaT(i, j, jugador);
                    System.out.println("En x: "+t.getPosibleX()+","+"En y: "+t.getPosibleY());
    					
                    arbolOponente(new Tree<Tablero>(t),oponente,jugador,cmp);
                    arbolPrincipal.getRoot().getChildren().add(new Tree<Tablero>(t));
                    tableroCopia.setFichaT(i, j, fichas.VACIO);    					    				    			
                }
            }
        }    	    	    	    	    
    	return arbolPrincipal;    	
    }
    	      
    private void volverMenu() {
    	try {    	
            Parent root = App.loadFXML("Menu");
            Stage gameStage = (Stage) buena.getScene().getWindow();
            gameStage.close();
            Stage stage  = new Stage();
            Scene scene = new Scene(root, 593, 395);
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();
        } catch (IOException e){
            System.out.println("File not found, Error al cargar pantalla");
        }
    }
    
    
    private void mostrarAlerta(Alert.AlertType tipo, String mensaje) {
        Alert alert = new Alert(tipo);

        alert.setTitle("Resultado de operacion");
        alert.setHeaderText("Notificacion");
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
    
   
    
    }