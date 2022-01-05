/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

/**
 *
 * @author User
 */

public enum fichas {
    X('X'),
    O('O'),
    VACIO(' ');

    private final char ficha;

    fichas(char ficha) {
        this.ficha = ficha;
    }

    public boolean isFicha() {
        return this != VACIO;
    }

    public char getFicha() {
        return this.ficha;
    }

    @Override
    public String toString() {
        return String.valueOf(ficha);
    }
}

