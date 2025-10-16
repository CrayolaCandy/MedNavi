import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class DoctorFilter {

    public static void main(String[] args) {
    	 String input0 = "Question: \r\n"
         		+ "I have heart murmur\r\n"
         		+ "\r\n"
         		+ "Outcome: \r\n"
         		+ "Heart murmurs can be caused by various underlying conditions. It's important to consult with a healthcare professional for a proper diagnosis. Here are some possible causes of a heart murmur:\r\n"
         		+ "\r\n"
         		+ "1. Valvular heart disease: This includes conditions such as mitral valve prolapse, mitral regurgitation, aortic stenosis, and aortic regurgitation. \r\n"
         		+ "   - Visit a cardiologist for evaluation and management.\r\n"
         		+ "\r\n"
         		+ "2. Congenital heart defects: These are heart abnormalities present at birth, such as ventricular septal defect (VSD) or atrial septal defect (ASD).\r\n"
         		+ "   - Consult with a pediatric cardiologist if you are a child or with an adult congenital heart disease specialist if you are an adult.\r\n"
         		+ "\r\n"
         		+ "3. Cardiomyopathy: This is a disease of the heart muscle that can cause murmurs.\r\n"
         		+ "   - Seek the expertise of a cardiologist for further evaluation.\r\n"
         		+ "\r\n"
         		+ "4. Infection: Certain infections like endocarditis, an infection of the heart valves, can lead to heart murmurs.\r\n"
         		+ "   - See a cardiologist or infectious disease specialist for diagnosis and treatment.\r\n"
         		+ "\r\n"
         		+ "5. Hyperthyroidism: Overactive thyroid function can sometimes be associated with a heart murmur.\r\n"
         		+ "   - Consult with an endocrinologist for further evaluation.\r\n"
         		+ "\r\n"
         		+ "6. Anemia: Severe anemia can cause changes in heart sounds.\r\n"
         		+ "   - Consider seeing a hematologist or primary care physician for evaluation.\r\n"
         		+ "\r\n"
         		+ "Remember, this list is not exhaustive, and it is important to seek professional medical advice to determine the cause of your specific heart murmur.";
    	 
        String input1 = "I am a cardiologist and have worked as a neurologist for 15 years and cardiologist";
        String[] result1 = filterDoctor(input1);
        System.out.println("Filtered Result: " + Arrays.toString(result1));

        String input2 = "neurologist for 15 years and radiologist";
        String[] result2 = filterDoctor(input2);
        System.out.println("Filtered Result: " + Arrays.toString(result2));

        String input3 = "A physician studied for seven years and been working as a radiologist";
        String[] result3 = filterDoctor(input3);
        System.out.println("Filtered Result: " + Arrays.toString(result3));
    }
    
    /*
     * This method checks whether a given string input contains a type of doctor 
     * from the doctor text file. If the string contains such information, it 
     * outputs an array that limits its length to two; otherwise, it outputs nothing.
     *
     * */
    public static String[] filterDoctor(String input) {
        String path = "/doctors.txt";
    	Set<String> doctorTypes = new HashSet<>(Arrays.asList(readFromFile(path)));

        String[] words = input.split("\\s+");

        Set<String> filteredResult = new HashSet<>();
        for (String word : words) {
            String cleanedWord = word.toLowerCase().replaceAll("[^a-zA-Z]", ""); // Remove non-alphabetic characters
            if (doctorTypes.contains(cleanedWord)) {
                filteredResult.add(cleanedWord);
                if (filteredResult.size() == 2) {
                    break;
                }
            }
        }

        return filteredResult.toArray(new String[0]);
    }

    private static String[] readFromFile(String filePath) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(DoctorFilter.class.getResourceAsStream(filePath)))) {
            return reader.lines().map(String::toLowerCase).toArray(String[]::new);
        } catch (IOException e) {
            e.printStackTrace();
            return new String[0]; 
        }
    }
}
