package com.victorlopez.Blackjack;

public enum NumeroCarta {A, DOS, TRES, CUATRO, CINCO, SEIS, SIETE, OCHO, NUEVE, DIEZ, J, Q, K;
    public static int getValor(NumeroCarta n){
        switch (n){
            case A:
                return 11;
            case DOS:
                return 2;
            case TRES:
                return 3;
            case CUATRO:
                return 4;
            case CINCO:
                return 5;
            case SEIS:
                return 6;
            case SIETE:
                return 7;
            case OCHO:
                return 8;
            case NUEVE:
                return 9;
            case DIEZ:
            case J:
            case Q:
            case K:
                return 10;
        }
        return -1;
    }
    public static int getNumero(NumeroCarta n){
        switch (n){
            case A:
                return 1;
            case DOS:
                return 2;
            case TRES:
                return 3;
            case CUATRO:
                return 4;
            case CINCO:
                return 5;
            case SEIS:
                return 6;
            case SIETE:
                return 7;
            case OCHO:
                return 8;
            case NUEVE:
                return 9;
            case DIEZ:
                return 10;
            case J:
                return 11;
            case Q:
                return 12;
            case K:
                return 13;
        }
        return -1;
    }

}
