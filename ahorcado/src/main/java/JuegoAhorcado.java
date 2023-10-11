import java.util.Scanner;

public class JuegoAhorcado {
    private String palabra;
    private StringBuilder palabraOculta;
    private int intentos;

    public JuegoAhorcado(String palabra) {
        this.palabra = palabra.toLowerCase();
        this.palabraOculta = new StringBuilder("_".repeat(palabra.length()));
        this.intentos = 6;  // Inicializamos con 6 intentos
    }

    public void jugar() {
        Scanner scanner = new Scanner(System.in);

        while (intentos > 0 && palabraOculta.toString().contains("_")) {
            System.out.println("Palabra: " + palabraOculta);
            System.out.println("Vidas restantes: " + intentos);
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
                    intentos--;
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
            intentos--;
            System.out.println("Letra incorrecta. Intenta de nuevo.");
        }
    }
}
