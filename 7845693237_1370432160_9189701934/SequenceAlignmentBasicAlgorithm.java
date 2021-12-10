public class SequenceAlignmentBasicAlgorithm {

    private final int gapPenalty;
    // private int[][] mismatchCostMatrix;
    private long[][] memoizationTable;

    public SequenceAlignmentBasicAlgorithm(int gapPenalty) {
        //TODO test with costmatrix, using if else to save memory
        this.gapPenalty = gapPenalty;
        // this.mismatchCostMatrix = mismatchCostMatrix;
    }

    public Output alignSequences(String firstSequence, String secondSequence) {
        //TODO remove this after testing
//        System.out.println(firstSequence.getBytes(StandardCharsets.UTF_8).length / 1024.0);
//        System.out.println(secondSequence.length());
        memoizationTable = new long[firstSequence.length() + 1][secondSequence.length() + 1];
        for (int i = 0; i <= firstSequence.length(); i++) {
            memoizationTable[i][0] = (long) i * this.gapPenalty;
        }
        for (int j = 0; j <= secondSequence.length(); j++) {
            memoizationTable[0][j] = (long) j * this.gapPenalty;
        }
        for (int j = 1; j <= secondSequence.length(); j++) {
            for (int i = 1; i <= firstSequence.length(); i++) {
                long costWhenMatched = UtilityFunctions.getMismatchPenalty(firstSequence.charAt(i - 1), secondSequence.charAt(j - 1)) + memoizationTable[i - 1][j - 1];
                long costWithGapAtFirstSequence = this.gapPenalty + memoizationTable[i - 1][j];
                long costWithGapAtSecondSequence = this.gapPenalty + memoizationTable[i][j - 1];
                //TODO: use long for memoization table if higher bounds are expected
                //finding the minimum value out of the three variables
                memoizationTable[i][j] = Math.min(Math.min(costWhenMatched, costWithGapAtFirstSequence), costWithGapAtSecondSequence);

            }
        }
        //TODO: remove SYS OUTS later and printtable later before submitting
        System.out.println("The minimum cost penalty is " + memoizationTable[firstSequence.length()][secondSequence.length()]);

        //printTable(memoizationTable);
        OutputSequenceConstructor outputSequenceConstructor = new OutputSequenceConstructor();
        String[] resultSequence = outputSequenceConstructor.reconstructOutputFromMemoizationTable(memoizationTable, firstSequence, secondSequence, gapPenalty);
        System.out.println(resultSequence[0] + " " + resultSequence[1]);
        System.out.println(resultSequence[2] + " " + resultSequence[3]);
        Output output = new Output();
        output.firstSequenceResult = resultSequence[0] + " " + resultSequence[1];
        output.secondSequenceResult = resultSequence[2] + " " + resultSequence[3];
        output.minCost = memoizationTable[firstSequence.length()][secondSequence.length()];
        return output;
    }

    //TODO remove this
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
}
