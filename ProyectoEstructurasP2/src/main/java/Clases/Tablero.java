/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import static Clases.fichas.*;

/**
 *
 * @author User
 */
public class Tablero {
    
    private final fichas[][] tabla;
    private fichas marcaG;
    private boolean cambiarTurno, gameOver;
    private final int ancho_t = 3;
    private int movimientos = ancho_t * ancho_t;

    public Tablero() {
        tabla = new fichas[ancho_t][ancho_t];
        cambiarTurno = true;
        gameOver = false;
        marcaG = VACIO;
        empezarTablero();
    }

    private void empezarTablero() {
        for (int fila = 0; fila < ancho_t; fila++) {
            for (int col = 0; col < ancho_t; col++) {
                tabla[fila][col] = VACIO;
            }
        }
    }
    
    public void mostrarTablero() {
    	
    	for(int i = 0; i < ancho_t; i++) {
    		System.out.println(this.getFichaT(i, 0).getFicha()+"  "+this.getFichaT(i, 1).getFicha()+"  "
    				+this.getFichaT(i, 2).getFicha());
    	}
    	
    	System.out.println("                  ");
    }

    public boolean ponerFicha(int fila, int col) {
        if (fila < 0 || fila >= ancho_t || col < 0 || col >= ancho_t
                || isCeldafichas(fila, col) || gameOver) {
            return false;
        }
        movimientos--;
        tabla[fila][col] = cambiarTurno ? X : O;
        cambiarJugador();
        winn(fila, col);
        return true;
    }

    
    public int utilidadTablero(fichas fAJugar, fichas FOponente) {
    	
    	int utilidadT;
    	int utilidadFichaAJugar = this.funcionUtilidadDe(fAJugar);
    	int utilidadFichaOponente = this.funcionUtilidadDe(FOponente);
    	
    	utilidadT = utilidadFichaAJugar - utilidadFichaOponente;
    	System.out.println("Utilidad de la figura: "+fAJugar.getFicha()+" en el tablero es: "+ utilidadT);
    	return utilidadT;
    	
    	
    	
    	
    }
    
    
    
    private int funcionUtilidadDe(fichas fACalcular) {
    	
    	char fichaUtil = fACalcular.getFicha();
    	int utilidadTotal;
    	//utilidad de las filas con la ficha a buscar
    	int utilidadFilas = 0;
    	
    	for(int i = 0; i < ancho_t ; i++) {
    		
    			
    			char fichaj0 = getFichaT(i,0).getFicha();
    			char fichaj1 = getFichaT(i,1).getFicha();
    			char fichaj2 = getFichaT(i,2).getFicha();
    			
    			boolean cd1 = (fichaj0 == fichaUtil) || (fichaj0 == '-');
    			boolean cd2 = (fichaj1 == fichaUtil) || (fichaj1 == '-');
    			boolean cd3 = (fichaj2 == fichaUtil) || (fichaj2 == '-');
    			
    			if(cd1 && cd2 && cd3) {
    				utilidadFilas++;
    			}
    			
    		
    	}
    	//utilidad de las columnas con la ficha a buscar
    	int utilidadColumnas = 0;
    	
    	for(int j = 0; j < ancho_t ; j++) {
    		
			
			char fichai0 = getFichaT(0,j).getFicha();
			char fichai1 = getFichaT(1,j).getFicha();
			char fichai2 = getFichaT(2,j).getFicha();
			
			boolean cd1 = (fichai0 == fichaUtil) || (fichai0 == '-');
			boolean cd2 = (fichai1 == fichaUtil) || (fichai1 == '-');
			boolean cd3 = (fichai2 == fichaUtil) || (fichai2 == '-');
			
			if(cd1 && cd2 && cd3) {
				utilidadColumnas++;
			}
			
		
    	}
    	
    	
    	//utilidad de las diagonales de  con la ficha a buscar
    	int utilidadDiagonales = 0;
    	
    	char ficha00 = getFichaT(0,0).getFicha();
		char ficha11 = getFichaT(1,1).getFicha();
		char ficha22 = getFichaT(2,2).getFicha();
		char ficha02 = getFichaT(0,2).getFicha();
		char ficha20 = getFichaT(2,0).getFicha();
		
		boolean cd1 = (ficha00 == fichaUtil) || (ficha00 == '-');
		boolean cd2 = (ficha11 == fichaUtil) || (ficha11 == '-');
		boolean cd3 = (ficha22 == fichaUtil) || (ficha22 == '-');
		boolean cd4 = (ficha02 == fichaUtil) || (ficha02 == '-');
		boolean cd5 = (ficha20 == fichaUtil) || (ficha20 == '-');
		
		
		if(cd1 && cd2 && cd3) {
			utilidadDiagonales++;
		}
		if(cd4 && cd2 && cd5) {
			utilidadDiagonales++;
		}
		
		
    	
    	utilidadTotal = utilidadFilas + utilidadColumnas + utilidadDiagonales;
    	return utilidadTotal;
    	
    	
    }
    
    
    
    
    
    
    
    
    private void winn(int fila, int col) {
        int filaSum = 0;
  
        for (int c = 0; c < ancho_t; c++) {
            filaSum += getFichaT(fila, c).getFicha();
        }
        if (calcularGanador(filaSum) != VACIO) {
            System.out.println(marcaG + " VICTORIA DE LA FILA " + fila);
            return;
        }

        // Check column for winner.
        filaSum = 0;
        for (int r = 0; r < ancho_t; r++) {
            filaSum += getFichaT(r, col).getFicha();
        }
        if (calcularGanador(filaSum) != VACIO) {
            System.out.println(marcaG + " VICTORIA DE LA COLUMNA" + col);
            return;
        }

        // Top-left to bottom-right diagonal.
        filaSum = 0;
        for (int i = 0; i < ancho_t; i++) {
            filaSum += getFichaT(i, i).getFicha();
        }
        if (calcularGanador(filaSum) != VACIO) {
            System.out.println(marcaG + " GANADOR DE ARRIBA-IZQUIERDA "
                    + "FONDO-DERECHA-DIAGONAL");
            return;
        }

        // Top-right to bottom-left diagonal.
        filaSum = 0;
        int indexMax = ancho_t - 1;
        for (int i = 0; i <= indexMax; i++) {
            filaSum += getFichaT(i, indexMax - i).getFicha();
        }
        if (calcularGanador(filaSum) != VACIO) {
            System.out.println(marcaG + " GANADOR DE  ARRIBA-DERECHA DE "
                    + "FONDO-IZQUIERDA DIAGONAL.");
            return;
        }

        if (!sinMovimientos()) {
            gameOver = true;
            System.out.println("SIN MOVIMIENTOS!");
        }
    }


    private fichas calcularGanador(int filaSum) {
        int Xgana = X.getFicha() * ancho_t;
        int Ogana = O.getFicha() * ancho_t;
        if (filaSum == Xgana) {
            gameOver = true;
            marcaG = X;
            return X;
        } else if (filaSum == Ogana) {
            gameOver = true;
            marcaG = O;
            return O;
        }
        return VACIO;
    }

    private void cambiarJugador() {
        cambiarTurno = !cambiarTurno;
    }

    
    public boolean sinMovimientos() {
        return movimientos > 0;
    }

    public fichas getFichaT(int fila, int col) {
        return tabla[fila][col];
    }

    public boolean isCeldafichas(int fila, int colum) {
        return tabla[fila][colum].isFicha();
    }

    public void setFichaT(int fila, int column, fichas newFicha) {
        tabla[fila][column] = newFicha;
    }

    @Override
    public String toString() {
        StringBuilder strBldr = new StringBuilder();
        for (fichas[] fila : tabla) {
            for (fichas tile : fila) {
                strBldr.append(tile).append(' ');
            }
            strBldr.append("\n");
        }
        return strBldr.toString();
    }

    public boolean isCambioTurno() {
        return cambiarTurno;
    }

    public int getAncho() {
        return ancho_t;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public fichas getMarcaG() {
        return marcaG;
    }
}

