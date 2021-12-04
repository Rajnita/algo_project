package com.algo.alignment.core;

public class SequenceAlignmentBasicAlgorithm {

    private final int gapPenalty;
    // private int[][] mismatchCostMatrix;
    private int[][] memoizationTable;

    public SequenceAlignmentBasicAlgorithm(int gapPenalty) {
        //TODO test with costmatrix, using if else to save memory
        this.gapPenalty = gapPenalty;
        // this.mismatchCostMatrix = mismatchCostMatrix;
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
                int costWhenMatched = getMismatchPenalty(firstSequence.charAt(i - 1), secondSequence.charAt(j - 1)) + memoizationTable[i - 1][j - 1];
                int costWithGapAtFirstSequence = this.gapPenalty + memoizationTable[i - 1][j];
                int costWithGapAtSecondSequence = this.gapPenalty + memoizationTable[i][j - 1];
                //TODO: use long for memoization table if higher bounds are expected
                //finding the minimum value out of the three variables
                memoizationTable[i][j] = Math.min(Math.min(costWhenMatched, costWithGapAtFirstSequence), costWithGapAtSecondSequence);

            }
        }
        //TODO: remove SYS OUTS later and printtable later before submitting
        System.out.println("The minimum cost penalty is " + memoizationTable[firstSequence.length()][secondSequence.length()]);

        printTable(memoizationTable);
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

    private int getMismatchPenalty(char m, char n) {
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

    public String[] getAlignment() {
        //Backtrack memoization table 2 d array
        return null;
    }
}
