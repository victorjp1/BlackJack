package com.victorlopez.Blackjack;

import java.util.Scanner;

public class Main{
    private static boolean validado;
    private static Scanner lector = new Scanner(System.in);
    private static Blackjack blackjack;
    public static void main(String[] args) {
        boolean salir = false;
        do {
            int opcion = mostrarMenu();
            switch (opcion){
                case 1:
                    nuevaPartida();
                    break;
                case 2:
                    mostrarEstadisticas();
                    break;
                case 0:
                    salir = true;
                    System.out.println("Hasta pronto!");
                    break;
            }
        }while (!salir);


    }
    private static void mostrarEstadisticas(){
        try{
            System.out.println(blackjack.getEstadisticas());
        }catch (NullPointerException npe){
            System.out.println("No hay datos almacenados");
        }
    }
    private static void nuevaPartida(){
        String nombre = pedirNombre();
        blackjack = new Blackjack(nombre);
        blackjack.nuevaPartida();
    }

    /**
     * Método para mostrar el menú principal de la app
     * @return opción ya validada
     */
    private static int mostrarMenu(){
        int opcion = -1;
        do {
            try {
                System.out.println("*******************");
                System.out.println("**** BLACKJACK ****");
                System.out.println("*******************");
                System.out.println("1. Nueva partida");
                System.out.println("2. Mostrar estadísticas");
                System.out.println("-------------------------");
                System.out.println("0. Salir");
                opcion = Integer.parseInt(lector.nextLine());
                if (opcion >= 0 && opcion <= 2){
                    validado = true;
                }else {
                    validado = false;
                    System.out.println("Opción incorrecta");
                }
            }catch (NumberFormatException nfe){
                validado = false;
                System.out.println("Formato incorrecto");
            }
        }while (!validado);
        return opcion;
    }
    /**
     * Método para pedir el nombre del equipo
     * @return nombre ya validado
     */
    private static String pedirNombre(){
        String nombre;
        do {
            System.out.println("Introduce el nombre del jugador: ");
            nombre = lector.nextLine();
            if (nombre.length() > 2){
                validado = true;
            }else{
                validado = false;
                System.out.println("Demasiado corto");
                Lib.pausa();
            }
            Lib.limpiarPantalla();
        }while (!validado);
        return nombre;
    }
}

/*
        BarajaFrancesa b = new BarajaFrancesa();
        System.out.println(b.getNCartasActual());
        System.out.println(b.sacarCarta());
        System.out.println(b.sacarCarta());
        System.out.println(b.sacarCarta());
        System.out.println(b.sacarCarta());
        System.out.println(b.sacarCarta());
        System.out.println(b.sacarCarta());
        System.out.println(b.sacarCarta());
        System.out.println(b.sacarCarta());
        System.out.println(b.getNCartasActual());
        b.barajar();
        System.out.println("Barajando");
        Lib.pausa();
        System.out.println(b.sacarCarta());
        System.out.println(b.sacarCarta());
        System.out.println(b.sacarCarta());
        System.out.println(b.sacarCarta());
        System.out.println(b.sacarCarta());
        System.out.println(b.sacarCarta());
        System.out.println(b.sacarCarta());
        System.out.println(b.sacarCarta());
        */
