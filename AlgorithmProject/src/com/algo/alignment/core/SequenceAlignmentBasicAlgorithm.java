package com.algo.alignment.core;

import com.algo.alignment.utils.UtilityFunctions;

public class SequenceAlignmentBasicAlgorithm {

    private final int gapPenalty;
    // private int[][] mismatchCostMatrix;
    private int[][] memoizationTable;

    public SequenceAlignmentBasicAlgorithm(int gapPenalty) {
        //TODO test with costmatrix, using if else to save memory
        this.gapPenalty = gapPenalty;
        // this.mismatchCostMatrix = mismatchCostMatrix;
    }


    public int[][] alignSequencesMatrix(String firstSequence, String secondSequence) {
        memoizationTable = new int[firstSequence.length() + 1][secondSequence.length() + 1];
        for (int i = 0; i <= firstSequence.length(); i++) {
            memoizationTable[i][0] = i * this.gapPenalty;
        }
        for (int j = 0; j <= secondSequence.length(); j++) {
            memoizationTable[0][j] = j * this.gapPenalty;
        }
        for (int j = 1; j <= secondSequence.length(); j++) {
            for (int i = 1; i <= firstSequence.length(); i++) {
                int costWhenMatched = UtilityFunctions.getMismatchPenalty(firstSequence.charAt(i - 1), secondSequence.charAt(j - 1)) + memoizationTable[i - 1][j - 1];
                int costWithGapAtFirstSequence = this.gapPenalty + memoizationTable[i - 1][j];
                int costWithGapAtSecondSequence = this.gapPenalty + memoizationTable[i][j - 1];
                //TODO: use long for memoization table if higher bounds are expected
                //finding the minimum value out of the three variables
                memoizationTable[i][j] = Math.min(Math.min(costWhenMatched, costWithGapAtFirstSequence), costWithGapAtSecondSequence);

            }
        }
        //TODO: remove SYS OUTS later and printtable later before submitting
        //System.out.println("The minimum cost penalty is " + memoizationTable[firstSequence.length()][secondSequence.length()]);

        //printTable(memoizationTable);
        reconstructOutputFromMemoizationTable(memoizationTable, firstSequence, secondSequence, gapPenalty);
        return memoizationTable;
    }
    public int alignSequences(String firstSequence, String secondSequence) {
        memoizationTable = new int[firstSequence.length() + 1][secondSequence.length() + 1];
        for (int i = 0; i <= firstSequence.length(); i++) {
            memoizationTable[i][0] = i * this.gapPenalty;
        }
        for (int j = 0; j <= secondSequence.length(); j++) {
            memoizationTable[0][j] = j * this.gapPenalty;
        }
        for (int j = 1; j <= secondSequence.length(); j++) {
            for (int i = 1; i <= firstSequence.length(); i++) {
                int costWhenMatched = UtilityFunctions.getMismatchPenalty(firstSequence.charAt(i - 1), secondSequence.charAt(j - 1)) + memoizationTable[i - 1][j - 1];
                int costWithGapAtFirstSequence = this.gapPenalty + memoizationTable[i - 1][j];
                int costWithGapAtSecondSequence = this.gapPenalty + memoizationTable[i][j - 1];
                //TODO: use long for memoization table if higher bounds are expected
                //finding the minimum value out of the three variables
                memoizationTable[i][j] = Math.min(Math.min(costWhenMatched, costWithGapAtFirstSequence), costWithGapAtSecondSequence);

            }
        }
        //TODO: remove SYS OUTS later and printtable later before submitting
        System.out.println("The minimum cost penalty is " + memoizationTable[firstSequence.length()][secondSequence.length()]);

        //printTable(memoizationTable);
        reconstructOutputFromMemoizationTable(memoizationTable, firstSequence, secondSequence, gapPenalty);
        return memoizationTable[firstSequence.length()][secondSequence.length()];
    }

    private void printTable(int[][] table) {
        for (int[] row : table) {
            for (int value : row) {
                System.out.print(value + "\t");
            }
            System.out.println();
        }
    }

    public String[] getAlignment() {
        //Backtrack memoization table 2 d array
        return null;
    }

    public String[] reconstructOutputFromMemoizationTable(int[][] memoizationTable, String firstSequence, String secondSequence, int gapPenality) {
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
