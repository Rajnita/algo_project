import java.nio.charset.StandardCharsets;
import java.util.List;

public class InputGenerator {

    public static String[] getInputSequence(String firstBaseString, String secondBaseString, List<Integer> firstBaseIndices, List<Integer> secondBaseIndices) {
        String[] result = new String[2];
        result[0] = generateSequence(firstBaseString, firstBaseIndices);
        result[1] = generateSequence(secondBaseString, secondBaseIndices);
        System.out.println("Problem size" + result[0].length() + result[1].length());
        System.out.println("BytesA:" + result[0].getBytes(StandardCharsets.UTF_8).length + " BytesB" + result[1].getBytes(StandardCharsets.UTF_8).length);
        return result;
    }

    private static String generateSequence(String s, List<Integer> indices) {
        StringBuilder str = new StringBuilder();
        str.append(s);
        String st = String.valueOf(str);
        for (Integer index : indices) {
            if (index <= str.length())
                str.insert(index + 1, st);
            else {
                System.err.println("Please give valid inputs.");
                //str.insert(str.length(), st);
            }
            st = String.valueOf(str);
        }
        s = str.toString();
        return s;
    }
}


