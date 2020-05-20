package com.victorlopez.Blackjack;

import java.util.Scanner;

public class Blackjack {
    private static final int SALDO_BASE_JUGADOR_DEFAULT = 100;
    private static final int APUESTA_MINIMA_DEFAULT = 20;
    private double apuestaMinima;
    private int saldoBase;
    private BarajaFrancesa baraja;
    private Jugador jugador;
    private Jugador croupier;
    private static Scanner lector;

    public Blackjack(String nombreJugador) {
        this(nombreJugador, SALDO_BASE_JUGADOR_DEFAULT);
    }
    public Blackjack(String nombreJugador,  int saldoBase) {
        lector = new Scanner(System.in);
        this.jugador = new Jugador(nombreJugador, saldoBase);
        this.croupier = new Jugador("Croupier", saldoBase);
        this.baraja = new BarajaFrancesa();
        this.saldoBase = saldoBase;
        this.apuestaMinima = APUESTA_MINIMA_DEFAULT;
    }
    public void nuevaPartida(int apuestaMin){
        this.apuestaMinima = apuestaMin;
        boolean salir = false;
        jugador.iniciarPartida(saldoBase);
        croupier.iniciarPartida(saldoBase);
        do {
            reiniciarMano();
            nuevaMano();
            if (!jugador.puedeApostar(apuestaMinima)){
                salir = true;
                System.out.println("Ya no puedes jugar más");
            }else if (!croupier.puedeApostar(apuestaMinima * 2.5)){
                System.out.println("La banca ya no puede apostar más");
                System.out.println("Ve a cobrar tus fichas");
            }
        }while (!salir);
        Lib.pausa();
        Lib.limpiarPantalla();
    }
    public void nuevaPartida(){
        jugador.iniciarPartida(saldoBase);
        croupier.iniciarPartida(saldoBase);
        boolean salir = false;
        while (!salir){
            reiniciarMano();
            nuevaMano();
            if (!jugador.puedeApostar(apuestaMinima)){
                salir = true;
                System.out.println("Ya no puedes jugar más");
            }else if (!croupier.puedeApostar(apuestaMinima * 2)){
                System.out.println("La banca ya no puede apostar más");
                System.out.println("Ve a cobrar tus fichas");
            }
        }
    }
    private void reiniciarMano(){
        baraja.barajar();
        jugador.resetMano();
        croupier.resetMano();
    }
    private boolean apuestaDoble(){
        boolean validado;
        int opcion = -1;
        do {
            try{
                System.out.println("La apuesta mínima es de " + getApuestaMinima());
                System.out.println("Quieres doblar la apuesta?");
                System.out.println("1. Apuesta mínima");
                System.out.println("2. Doblar apuesta");
                opcion = Integer.parseInt(lector.nextLine());
                if (opcion == 1 || opcion == 2){
                    validado = true;
                    System.out.println("Elegido correctamente");
                }else{
                    validado = false;
                    System.out.println("Opción incorrecta");
                }
            }catch (NumberFormatException nfe){
                validado = false;
                System.out.println("Formato incorrecto");
            }
        }while (!validado);
        if (opcion == 1)
            return false;
        return true;
    }
    public void nuevaMano(){
        System.out.println("Tu saldo es de: " + jugador.getSaldo() + " fichas");
        System.out.println("El saldo del croupier es de: " + croupier.getSaldo() + " fichas");
        Lib.pausa();
        Lib.limpiarPantalla();
        jugador.addCarta(baraja.sacarCarta(false));
        jugador.addCarta(baraja.sacarCarta(false));
        croupier.addCarta(baraja.sacarCarta(false));
        croupier.addCarta(baraja.sacarCarta(true));
        mostrarCartas(jugador);
        mostrarCartas(croupier);
        if (apuestaDoble()){
            jugador.apostar(apuestaMinima * 2);
            croupier.apostar(apuestaMinima * 2);
        }else{
            jugador.apostar(apuestaMinima);
            croupier.apostar(apuestaMinima);
        }
        if (jugador.sumaCartas() == 21){
            System.out.println("BLACKJACK, HAS GANADO!!!");
            System.out.println("Has ganado " + jugador.getApuesta() * 1.5 + " fichas");
            croupier.apostar(croupier.getApuesta() * 0.5);
            jugador.ingresar((jugador.getApuesta() * 2.5));
            jugador.addVictoria();
        }else if(jugador.sumaCartas() > 21){
            System.out.println("Te has pasado de 21");
            System.out.println("Has perdido!!");
            croupier.ingresar(croupier.getApuesta() * 2);
            croupier.addVictoria();
        }else{
            pedirCartas();
            if (jugador.sumaCartas() > 21){
                System.out.println("Te has pasado de 21");
                System.out.println("Has perdido!");
                croupier.addVictoria();
                croupier.ingresar(croupier.getApuesta() * 2);
            }else{
                juegaCroupier();
                System.out.println("La puntuación del croupier es de: " + croupier.sumaCartas());
                compararResultados();
            }
        }
        Lib.pausa();
        Lib.limpiarPantalla();
    }
    private void compararResultados(){
        if (croupier.sumaCartas() > 21){
            System.out.println("El croupier se ha pasado de 21");
            System.out.println("Has ganado!!");
            jugador.addVictoria();
            System.out.println("Has ganado " + jugador.getApuesta() + " fichas");
            jugador.ingresar(jugador.getApuesta() * 2);
        }else{
            if (jugador.sumaCartas() > croupier.sumaCartas()){
                System.out.println("Tu puntuación es superior!");
                System.out.println("Has ganado!!");
                jugador.addVictoria();
                System.out.println("Has ganado " + jugador.getApuesta() + " fichas");
                jugador.ingresar(jugador.getApuesta() * 2);
            }else{
                System.out.println("Tu puntuación es menor o igual!");
                System.out.println("La banca tiene ventaja :(");
                System.out.println("Has perdido!!");
                croupier.addVictoria();
                croupier.ingresar(croupier.getApuesta() * 2);
            }
        }
    }
    private void juegaCroupier(){
        croupier.getCartas().get(1).setOculta(false);
        boolean plantarse = croupier.sumaCartas() > 16;
        mostrarCartas(croupier);
        while (!plantarse){
            Carta c = baraja.sacarCarta(false);
            System.out.println(c.toImage());
            croupier.addCarta(c);
            plantarse = croupier.sumaCartas() > 16;
            try{
                Thread.sleep(1500);
            }catch (InterruptedException ie){
            }
        }
    }
    private void pedirCartas(){
        boolean salir = false;
        char eleccion;
        do {
            System.out.println("Tu puntuación es de: " + jugador.sumaCartas());
            if (jugador.sumaCartas() > 21){
                salir = true;
            }else{
                try{
                    System.out.println("Quieres otra carta? [S/N]");
                    eleccion = lector.nextLine().toLowerCase().charAt(0);
                    if (eleccion == 's'){
                        Carta c = baraja.sacarCarta(false);
                        jugador.addCarta(c);
                        System.out.println("Tu carta es: \n" + c.toImage());
                    }else if(eleccion == 'n'){
                        System.out.println("Has elegido plantarte");
                        salir = true;
                    }else {
                        System.out.println("Recuerda: S/N");
                    }
                }catch (NumberFormatException nfe){
                    System.out.println("Recuerda: [S/N]");
                }
            }
        }while (!salir);
        Lib.pausa();
        Lib.limpiarPantalla();
    }
    public String getEstadisticas(){
        String aux = "";
        aux += jugador.getNombre() + " ha conseguido ganar " + jugador.getnVictorias() + " manos \n";
        aux += croupier.getNombre() + " ha conseguido ganar " + croupier.getnVictorias() + " manos";
        return aux;
    }
    private void mostrarCartas(Jugador j){
        System.out.println("Cartas de " + j.getNombre());
        for (int i = 0; i < j.getCartas().size(); i++) {
            try{
                System.out.println(j.getCartas().get(i).toImage());
                Thread.sleep(2000);
            }catch (InterruptedException ie){

            }
        }
    }
    public double getApuestaMinima() {
        return apuestaMinima;
    }
}
