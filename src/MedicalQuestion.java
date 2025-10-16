import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MedicalQuestion {

    public static void main(String[] args) {
    	System.out.println(chatGPTResponse("hear murmur"));
    }

    public static String chatGPTResponse(String  userInput) {
    	//System.out.println("Enter a medical term to learn more:");
        String term = userInput;
        String responds ="";
        
        try {
            // Get answers related to the medical term
            String[] answers = getAnswers(term);
           
            // Store and print answers
            for (int i = 0; i < answers.length; i++) {
            	responds = responds + "Answer " + (i + 1) + ": \n" + answers[i] + "\n\n";
            }
            return responds;

        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    } 
    
    public static String[] getAnswers(String term) {
        String[] questions = {
        	"What is the definition of" + term + "?",
            "Can you provide examples of how " + term + " is used in clinical practice?",
            "Are there any common abbreviations or acronyms associated with " + term + "?",
            "How does " + term + " relate to other disciplines within medicine, such as pharmacology or pathology?",
            "Are there any current research findings or developments related to " + term + "?",
            "How does " + term + " differ across medical specialties or subspecialties?",
            "Are there any specific guidelines or protocols related to the use of " + term + " in medical documentation or communication?",
            "Are there any cultural or linguistic considerations to keep in mind when using " + term + " in a diverse medical environment?",
            "Can you explain any common misconceptions or misunderstandings associated with " + term + "?",
            "How does " + term + " relate to patient education or communication?"
        };

        List<String> answers = new ArrayList<>();
        for (String question : questions) {
            answers.add(chatGPT(question));
        }
        return answers.toArray(new String[0]);
    }

    public static String chatGPT(String message) {
        String url = "https://api.openai.com/v1/chat/completions";
        // API key
        String apiKey = "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"; // Replace with your API key
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
            String body = "{\"model\": \"" + model + "\", \"messages\": [{\"role\": \"user\", \"content\": \"" + message + "\"}]}";
            conn.setDoOutput(true);
            OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
            writer.write(body);
            writer.flush();
            writer.close();

            // Get the response
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
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
        int startMarker = response.indexOf("content")+11; // Marker for where the content starts.
        int endMarker = response.indexOf("\"", startMarker); // Marker for where the content ends.
        return response.substring(startMarker, endMarker); // Returns the substring containing only the response.
    }
}
