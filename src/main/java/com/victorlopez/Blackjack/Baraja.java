package com.victorlopez.Blackjack;


public abstract class Baraja {
    private Carta[] cartas;
    private String nombre;
    private int contador;//Contador de número de elementos en el array
    public Baraja(String nombre, Carta... cartas) {
        this.cartas = cartas;
        this.nombre = nombre;
        contador = (cartas.length - 1) +1;
    }
    /**
     * Método para rellenar las bolas del bombo
     */
    public void barajar(){
        contador = (cartas.length - 1) + 1;
    }

    public String getNombre() {
        return nombre;
    }

    public int getNCartasActual(){
        return contador;
    }

    /**
     * Método para sacar bola, cambiándola la posición de la bola por la última y decrementando el contador con el que recorremos
     * @return devuelve la bola
     */
    public Carta sacarCarta(boolean oculta){
        int pos = Lib.aleatorio(0, contador -1);
        Carta aux;
        aux = cartas[pos];
        cartas[pos] = cartas[contador -1];
        cartas[contador-1] = aux;
        contador--;
        aux.setOculta(oculta);
        return aux;
    }

}
