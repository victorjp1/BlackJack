package com.victorlopez.Blackjack;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Lib {
    /**
     * Método que llena de forma aleatoria un array list
     * @param a array list a llenar
     * @param nElementos numero de elementos a introducir
     */
    public static void llenarArrayListAleatorio(ArrayList<Double> a, int nElementos){
        for (int i = 0; i < nElementos; i++) {
            a.add((double) Lib.aleatorio(0,5));
        }
    }
    /**
     * Método para llenar un array con enteros aleatorios
     * @param n array a llenar
     */
    public static void llenarArrayAleatorio(int[] n){
        for (int i = 0; i < n.length; i++) {
            n[i] = Lib.aleatorio(0,50);
        }
    }
    /**
     * Método que genera un número aleatorio.
     * @param min Mínimo a generar
     * @param max Máximo a generar
     * @return aleatorio generado
     */
    public static double aleatorio(double min, double max){
        Random r = new Random();//Declaramos el objeto Random.
        return min + r.nextDouble() * (max - min);//Generamos el número aleatorio.
    }
    /**
     * Método que genera un número aleatorio.
     * @param min Mínimo a generar
     * @param max Máximo a generar
     * @return aleatorio generado
     */
    public static int aleatorio(int min, int max){
        Random r = new Random();//Declaramos el objeto Random.
        return r.nextInt(max - min + 1) + min;//Generamos el número aleatorio.
    }

    /**
     * Método para dar tiempo al user a leer datos, esperando un intro
     */
    public static void pausa(){
        Scanner lector = new Scanner(System.in);
        System.out.println("Pulsa intro para continuar.");
        lector.nextLine();
    }

    /**
     * Método para limpiar la pantalla y colocar el cursor arriba a la izquierda
     */
    public static void limpiarPantalla(){
        System.out.print("\u001B[H\u001B[2J");
        System.out.flush();
    }
}
