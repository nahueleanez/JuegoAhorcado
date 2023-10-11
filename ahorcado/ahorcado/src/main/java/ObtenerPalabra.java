import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;

public class ObtenerPalabra {
    private static final String API_URL = "https://clientes.api.greenborn.com.ar/public-random-word?c=9&l=8";

    public static String obtenerPalabraAleatoria() {
        try {
            URL url = new URL(API_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            reader.close();

            // Obtener las palabras separadas por comas
            String[] palabras = response.toString().split(",");

            // Seleccionar una palabra al azar de la lista
            Random random = new Random();
            int indiceAleatorio = random.nextInt(palabras.length);
            return palabras[indiceAleatorio].trim();  // Eliminamos espacios en blanco
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
