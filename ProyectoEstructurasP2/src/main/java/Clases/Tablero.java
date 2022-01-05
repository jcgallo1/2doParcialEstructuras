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
        EmpezarTablero();
    }

    private void EmpezarTablero() {
        for (int fila = 0; fila < ancho_t; fila++) {
            for (int col = 0; col < ancho_t; col++) {
                tabla[fila][col] = VACIO;
            }
        }
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

