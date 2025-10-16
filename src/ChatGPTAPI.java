import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

//Using
public class ChatGPTAPI {

    public static void main(String[] args) {
        String symptom = "Throat pain";
        String userInput = symptom;

        // Prints out a response to the question.
        System.out.println(chatGPT(userInput));
    }
   
    public static String chatGPT(String message) {
        String url = "https://api.openai.com/v1/chat/completions";
        // API key
        String apiKey = "sk-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";
        // Model that is using
        String model = "gpt-3.5-turbo";

        try {
            // Create the HTTP POST request
            URL API = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) API.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Authorization", "Bearer " + apiKey);
            conn.setRequestProperty("Content-Type", "application/json");

            // Build the request body
            String body = "{\"model\": \"" + model + "\", \"messages\": ["
                    + "{\"role\":\"system\", \"content\":\"You are a medical assistant providing information about diseases and symptoms.\"},"
                    + "{\"role\":\"user\", \"content\":\"" + message + "\"},"
                    + "{\"role\":\"assistant\", \"content\":\"Provide a list of potential diseases or conditions when given symptoms and recommend which type of doctor they should visit for their symptoms.\"}"
                    + "]}";
            conn.setDoOutput(true);
            // Convert characters to bytes
            OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
            writer.write(body);
            writer.flush();
            writer.close();

            // Get the response
            // Convert bytes to characters
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // returns the extracted contents of the response.
            return extractContentFromResponse(response.toString());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // This method extracts the response expected from chatgpt and returns it.
    public static String extractContentFromResponse(String response) {
        // Marker for where the content starts.
        int startMarker = response.indexOf("content") + 11;
        // Marker for where the content ends.
        int endMarker = response.indexOf("\"", startMarker);
        // Returns the substring containing only the response.
        return response.substring(startMarker, endMarker);
    }
}
