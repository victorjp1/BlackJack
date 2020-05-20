package com.victorlopez.Blackjack;

public class Carta {
    private Palo palo;
    private NumeroCarta numero;
    private boolean oculta;

    public Carta(Palo palo, NumeroCarta numero) {
        this.palo = palo;
        this.numero = numero;
        oculta = false;
    }

    public Palo getPalo() {
        return palo;
    }

    public int getNumero() {
        return NumeroCarta.getNumero(this.numero);
    }

    public void setOculta(boolean oculta) {
        this.oculta = oculta;
    }

    public int getValor() {
        return NumeroCarta.getValor(this.numero);
    }
    /*
    Alt+3 = ♥
    Alt+4 = ♦
    Alt+5 = ♣
    Alt+6 = ♠
    */
    public String toImage(){
        String aux = "";
        if (oculta){
            aux += "-----\n";
            aux += "|" + "?" + "  |\n";
            aux += "| " + "?" + " |\n";
            aux +=  "|  " + "?" + "|\n";
            aux += "-----\n";
        }else{
            aux += "-----\n";
            aux += "|" + simboloCarta() + "  |\n";
            aux += "| " + getPaloAscii() + " |\n";
            aux +=  "|  " + simboloCarta() + "|\n";
            aux += "-----\n";
        }
        return aux;
    }
    private String simboloCarta(){
        int numero = getNumero();
        String aux = "";
        if (numero >= 2 && numero <= 10){
            aux += numero;
        }else if (numero == 1){
            aux += 'A';
        }else if (numero == 11){
            aux += 'J';
        }else if (numero == 12){
            aux += 'Q';
        }else{
            aux += 'K';
        }
        return aux;
    }
    private String getPaloAscii(){
        switch (getPalo()){
            case TREBOLES:
                return "♣";
            case PICAS:
                return "♠";
            case CORAZONES:
                return "♥";
            case DIAMANTES:
                return "♦";
        }
        return "";
    }
    @Override
    public String toString() {
        String aux = "";
        if (oculta){
            aux += "Carta Oculta";
        }else{
            int numero = getNumero();
            if (numero >= 2 && numero <= 10){
                aux+= String.format("%-2d de %-10s", numero, palo);
            }else if (numero == 1){
                aux+= String.format("%-2s de %-10s", "AS", palo);
            }else if (numero == 11){
                aux+= String.format("%-2s de %-10s", "J", palo);
            }else if (numero == 12){
                aux+= String.format("%-2s de %-10s", "Q", palo);
            }else{
                aux+= String.format("%-2s de %-10s", "K", palo);
            }
        }
        return aux;
    }
}
