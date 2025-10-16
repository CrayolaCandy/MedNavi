import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class MedicalWordFilter {

    public static void main(String[] args) {
        String str = "I have throat pain";
        String result = filterMedicalWords(str);
        System.out.println("Filtered Result: " + result);

        String str2 = "I have pain";
        String result2 = filterMedicalWords(str2);
        System.out.println("Filtered Result: " + result2);

        String str3 = "I have apples in my closet";
        String result3 = filterMedicalWords(str3);
        System.out.println("Filtered Result: " + result3);
        
        String searchTerm = "i have wrist pain";
        boolean isInFile = isTermInFile(searchTerm.toLowerCase());
        if (isInFile) {
            System.out.println("The term \"" + searchTerm + "\" exists in the file.");
        } else {
            System.out.println("The term \"" + searchTerm + "\" does not exist in the file.");
        }
        
        String userInput = "my wrist is pain";
        boolean containsMedicalTerm = containsMedicalTerm(userInput.toLowerCase());
        if (containsMedicalTerm) {
            System.out.println("The user input contains at least one medical term from the file.");
        } else {
            System.out.println("The user input does not contain any medical term from the file.");
        }
    }

    /*
     * This method checks whether a given string input contains medical terms
     * from the wordlist text file. If the string contains such information, it 
     * outputs an array that limits its length to two; otherwise, it outputs the 
     * first words of the string.
     * 
     * */
    public static String filterMedicalWords(String input) {
    	String path = "/wordlist.txt";
    	String[] medicalKeywords = readFromFile(path);

        String[] words = input.split("\\s+");

        StringBuilder filteredResult = new StringBuilder();
        int medicalKeywordCount = 0;
        for (String word : words) {
            if (Arrays.asList(medicalKeywords).contains(word.toLowerCase())) {
                filteredResult.append(word).append(" ");
                medicalKeywordCount++;
                if (medicalKeywordCount == 2) {
                    return filteredResult.toString().trim();
                }
            }
        }

        if (medicalKeywordCount == 1) {
            return filteredResult.toString().trim();
        }
        
        return Arrays.stream(words).limit(2).collect(StringBuilder::new, (sb, w) -> sb.append(w).append(" "), StringBuilder::append).toString().trim();
    }

    private static String[] readFromFile(String filePath) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(MedicalWordFilter.class.getResourceAsStream(filePath)))) {
            return reader.lines().toArray(String[]::new);
        } catch (IOException e) {
            e.printStackTrace();
            return new String[0]; 
        }
    }
    
    /* For student search interface
     * 
     */
    public static boolean isTermInFile(String term) {
        String path = "/wordlist.txt";
        String[] medicalKeywords = readFromFile(path);

        for (String keyword : medicalKeywords) {
            if (keyword.trim().equalsIgnoreCase(term.trim())) {
                return true;
            }
        }
        
        return false;
    }
    
    /*For patient search interface
     * 
     */
    public static boolean containsMedicalTerm(String userInput) {
        String path = "/wordlist.txt";
        String[] medicalKeywords = readFromFile(path);

        String[] words = userInput.split("\\s+");

        for (String word : words) {
            for (String keyword : medicalKeywords) {
                if (keyword.trim().equalsIgnoreCase(word.trim())) {
                    return true;
                }
            }
        }

        return false;
    }
}
