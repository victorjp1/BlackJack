package com.victorlopez.Blackjack;

import java.util.Random;
import java.util.Scanner;

public class Lib {
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
