package com.mycompany.ahorcado;

import java.util.Scanner;

public class Ahorcado {
    private String palabra;
    private StringBuilder palabraOculta;
    private int intentos;

    public Ahorcado(String palabra) {
        this.palabra = palabra.toLowerCase();
        this.palabraOculta = new StringBuilder("_".repeat(palabra.length()));
        this.intentos = 0;
    }

    public void jugar() {
        Scanner scanner = new Scanner(System.in);

        while (intentos < 6 && palabraOculta.toString().contains("_")) {
            System.out.println("Palabra: " + palabraOculta);
            System.out.println("Intentos restantes: " + (6 - intentos));
            System.out.print("Ingresa una letra o intenta adivinar la palabra completa: ");
            String entrada = scanner.nextLine().toLowerCase();

            if (entrada.length() == 1) {
                // El jugador intenta adivinar una letra
                char letra = entrada.charAt(0);
                if (Character.isLetter(letra)) {
                    actualizarPalabraOculta(letra);
                } else {
                    System.out.println("Ingresa una letra válida.");
                }
            } else if (entrada.length() == palabra.length()) {
                // El jugador intenta adivinar la palabra completa
                if (entrada.equals(palabra)) {
                    palabraOculta = new StringBuilder(palabra);
                } else {
                    intentos++;
                    System.out.println("Palabra incorrecta. Intenta de nuevo.");
                }
            } else {
                System.out.println("Ingresa una letra o intenta adivinar la palabra completa.");
            }
        }

        if (!palabraOculta.toString().contains("_")) {
            System.out.println("¡Felicidades! Has adivinado la palabra: " + palabra);
        } else {
            System.out.println("¡Has perdido! La palabra era: " + palabra);
        }
    }

    private void actualizarPalabraOculta(char letra) {
        boolean acierto = false;
        for (int i = 0; i < palabra.length(); i++) {
            if (palabra.charAt(i) == letra && palabraOculta.charAt(i) == '_') {
                palabraOculta.setCharAt(i, letra);
                acierto = true;
            }
        }

        if (!acierto) {
            intentos++;
            System.out.println("Letra incorrecta. Intenta de nuevo.");
        }
    }

    public static void main(String[] args) {
        // Obtener una palabra aleatoria en español de la API
        String palabra = WordAPI.obtenerPalabraAleatoria();

        if (palabra != null) {
            System.out.println("¡Bienvenido al Juego del Ahorcado!");
            Ahorcado juego = new Ahorcado(palabra);
            juego.jugar();
        } else {
            System.out.println("No se pudo obtener una palabra en español de la API.");
        }
    }
}
