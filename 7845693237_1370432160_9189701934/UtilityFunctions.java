public class UtilityFunctions {
    public static int getMismatchPenalty(char m, char n) {
        int alpha = 0;
        if (m != n) {
            if ((m == 'A' && n == 'C')
                    || (m == 'C' && n == 'A'))
                alpha = 110;
            if ((m == 'A' && n == 'G')
                    || (m == 'G' && n == 'A'))
                alpha = 48;
            if ((m == 'A' && n == 'T')
                    || (m == 'T' && n == 'A'))
                alpha = 94;
            if ((m == 'C' && n == 'G')
                    || (m == 'G' && n == 'C'))
                alpha = 118;
            if ((m == 'C' && n == 'T')
                    || (m == 'T' && n == 'C'))
                alpha = 48;
            if ((m == 'G' && n == 'T')
                    || (m == 'T' && n == 'G'))
                alpha = 110;

        }
        return alpha;
    }


    public static String[] getOutputSequences(String[] completeOutputString) {
        String[] outputSequences = new String[4];
        if (completeOutputString[0].length() >= 50) {
            outputSequences[0] = completeOutputString[0].substring(0, 49);
            outputSequences[1] = completeOutputString[0].substring(completeOutputString[0].length() - 51);
        } else {
            outputSequences[0] = completeOutputString[0];
            outputSequences[1] = completeOutputString[0];
        }
        if (completeOutputString[1].length() >= 50) {
            outputSequences[2] = completeOutputString[1].substring(0, 49);
            outputSequences[3] = completeOutputString[1].substring(completeOutputString[1].length() - 51);
        } else {
            outputSequences[2] = completeOutputString[1];
            outputSequences[3] = completeOutputString[1];
        }
        return outputSequences;
    }


}
