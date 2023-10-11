public class Main {
    public static void main(String[] args) {
        // Obtener una palabra aleatoria en español de la API
        String palabra = ObtenerPalabra.obtenerPalabraAleatoria();

        if (palabra != null) {
            System.out.println("¡Bienvenido al Juego del Ahorcado!");
            JuegoAhorcado juego = new JuegoAhorcado(palabra);
            juego.jugar();
        } else {
            System.out.println("No se pudo obtener una palabra en español de la API.");
        }
    }
}
