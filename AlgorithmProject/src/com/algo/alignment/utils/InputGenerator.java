package com.algo.alignment.utils;

import java.util.List;

public class InputGenerator {

    public static String[] getInputSequence(String firstBaseString, String secondBaseString, List<Integer> firstBaseIndices, List<Integer> secondBaseIndices) {
        //TODO: Handle null if needed
        String[] result = new String[2];
        result[0] = generateSequence(firstBaseString, firstBaseIndices);
        result[1] = generateSequence(secondBaseString, secondBaseIndices);
        return result;
    }

    private static String generateSequence(String s, List<Integer> indices) {
        for (Integer index : indices) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int j = 0; j < s.length(); j++) {
                stringBuilder.append(s.charAt(j));
                if (j == index) {
                    stringBuilder.append(s);
                }
            }
            s = stringBuilder.toString();
        }
        return s;
    }
}


