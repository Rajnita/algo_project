package com.algo.alignment.core;

import com.algo.alignment.utils.UtilityFunctions;

public class OutputSequenceConstructor {

    public String[] reconstructOutputFromMemoizationTable(long[][] memoizationTable, String firstSequence, String secondSequence, int gapPenality) {

        int i = firstSequence.length(), j = secondSequence.length();
        StringBuilder tempStr1 = new StringBuilder();
        StringBuilder tempStr2 = new StringBuilder();
        while (i > 0 && j > 0) {
            if (memoizationTable[i][j] == memoizationTable[i - 1][j - 1] + UtilityFunctions.getMismatchPenalty(firstSequence.charAt(i - 1), secondSequence.charAt(j - 1))) {
                tempStr1.append(firstSequence.charAt(i - 1));
                tempStr2.append(secondSequence.charAt(j - 1));
                i--;
                j--;
            } else if (memoizationTable[i][j] == gapPenality + memoizationTable[i][j - 1]) {
                tempStr1.append("_");
                tempStr2.append(secondSequence.charAt(j - 1));
                j--;
            } else if (memoizationTable[i][j] == gapPenality + memoizationTable[i - 1][j]) {
                tempStr1.append(firstSequence.charAt(i - 1));
                tempStr2.append("_");
                i--;
            }
        }
        while (i > 0) {
            tempStr1.append(firstSequence.charAt(i - 1));
            tempStr2.append("_");
            i--;
        }
        while (j > 0) {
            tempStr1.append("_");
            tempStr2.append(secondSequence.charAt(j - 1));
            j--;
        }
        StringBuilder sb1 = new StringBuilder(tempStr1.toString());
        sb1.reverse();
        String firstOutputSeq = sb1.toString();
        StringBuilder sb2 = new StringBuilder(tempStr2.toString());
        sb2.reverse();
        String secondOutputSeq = sb2.toString();
        String[] outputSequences = new String[4];
        if (firstOutputSeq.length() >= 50) {
            outputSequences[0] = firstOutputSeq.substring(0, 49);
            outputSequences[1] = firstOutputSeq.substring(firstOutputSeq.length() - 51);
        } else {
            outputSequences[0] = firstOutputSeq;
            outputSequences[1] = firstOutputSeq;
        }
        if (secondOutputSeq.length() >= 50) {
            outputSequences[2] = secondOutputSeq.substring(0, 49);
            outputSequences[3] = secondOutputSeq.substring(secondOutputSeq.length() - 51);
        } else {
            outputSequences[2] = secondOutputSeq;
            outputSequences[3] = secondOutputSeq;
        }

        return outputSequences;
    }
}