package com.algo.alignment.utils;

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
}
