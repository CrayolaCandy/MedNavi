import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ZipCodeConverter {

    public static void main(String[] args) {
        try {
            String zipCode = "11355";
            double[] coordinates = getLatLong(zipCode);
            System.out.println("Latitude: " + coordinates[0]);
            System.out.println("Longitude: " + coordinates[1]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static double[] getLatLong(String zipCode) throws IOException {
        String apiKey = "AIzaSyAmNkaGHturLmgMk_KAxICazFBh38Zoov8";
        String apiUrl = "https://maps.googleapis.com/maps/api/geocode/json?address=" + zipCode + "&key=" + apiKey;

        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            String jsonResponse = response.toString();

            // Parse the JSON response to get latitude and longitude
            double[] coordinates = parseLatLongFromJson(jsonResponse);

            return coordinates;
        } finally {
            connection.disconnect();
        }
    }

    private static double[] parseLatLongFromJson(String jsonResponse) {
        // Parse the JSON response to extract latitude and longitude
        double latitude = 0.0;
        double longitude = 0.0;

        int latIndex = jsonResponse.indexOf("lat");
        int lngIndex = jsonResponse.indexOf("lng");

        if (latIndex != -1 && lngIndex != -1) {
            latitude = Double.parseDouble(jsonResponse.substring(latIndex + 6, latIndex + 15));
            longitude = Double.parseDouble(jsonResponse.substring(lngIndex + 6, lngIndex + 15));
        }

        return new double[]{latitude, longitude};
    }
}