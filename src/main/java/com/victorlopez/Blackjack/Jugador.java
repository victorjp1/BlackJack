package com.victorlopez.Blackjack;

import java.util.ArrayList;

public class Jugador {
    private String nombre;
    private double saldo;
    private double apostado;
    private ArrayList<Carta> cartas;
    private int nVictorias;
    private int nAses;

    public Jugador(String nombre, int saldo) {
        this.nombre = nombre;
        this.saldo = saldo;
        cartas = new ArrayList<>();
        nVictorias = 0;
        nAses = 0;
    }
    public void iniciarPartida(int s){
        nVictorias =0;
        nAses = 0;
        apostado = 0;
        saldo = s;
    }
    public ArrayList<Carta> getCartas() {
        return cartas;
    }
    public int sumaCartas(){
        int total = 0;
        for (int i = 0; i < cartas.size(); i++) {
            total += cartas.get(i).getValor();
        }
        return total - (nAses * 10);
    }
    public void resetMano(){
        nAses = 0;
        cartas.clear();
        apostado = 0;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public int getnVictorias() {
        return nVictorias;
    }

    public double getApuesta() {
        return apostado;
    }

    public String getNombre() {
        return nombre;
    }

    public double getSaldo() {
        return saldo;
    }
    public void addVictoria(){
        nVictorias++;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void ingresar(double cantidad){
        if (cantidad < 0){
            apostado = 0;
            saldo += cantidad;
        }
    }
    public boolean apostar(double cantidad){
        if (cantidad < 0)
            return false;
        if (puedeApostar(cantidad)){
            saldo -= cantidad;
            apostado = cantidad;
            return true;
        }
        return false;
    }
    public boolean puedeApostar(double cantidad){
        return cantidad < saldo;
    }
    public void addCarta(Carta c){
        if (c.getNumero() == 1){
            if (c.getValor() + sumaCartas() > 21){
                nAses++;
            }
        }
        cartas.add(c);
    }

}
