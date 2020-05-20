package com.victorlopez.Blackjack;

public class BarajaFrancesa extends Baraja{
    private static int N_CARTAS = 52;
    private static final String nombre = "Baraja Francesa";
    public BarajaFrancesa() {
        super(nombre, createCartas());
    }

    private static Carta[] createCartas(){
        Carta[] cartas = new Carta[N_CARTAS];
        int i = 0;
        for (int j = 0; j < Palo.values().length; j++) {
            for (int k = 0; k < NumeroCarta.values().length; k++) {
                cartas[i] = new Carta(Palo.values()[j], NumeroCarta.values()[k]);
                i++;
            }
        }
        return cartas;
    }
}
