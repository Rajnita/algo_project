package com.algo.alignment.core;

public class SequenceAlignmentBasicAlgorithm {

    private int gapPenalty;
    private int[][] mismatchCostMatrix;
    private int[][] memoizationTable;

    public SequenceAlignmentBasicAlgorithm(int gapPenalty, int[][] mismatchCostMatrix) {
        //TODO test without costmatrix , using if else and see if memory is saved
        this.gapPenalty = gapPenalty;
        this.mismatchCostMatrix = mismatchCostMatrix;
    }

    public int alignSequences(String firstSequence, String secondSequence) {
        //TODO
        return 0;
    }

    public String[] getAlignment() {
        //Backtrack memoization table 2 d array
        return null;
    }
}
