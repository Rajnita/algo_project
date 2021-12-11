public class SequenceAlignmentBasicAlgorithm {

    private final int gapPenalty;
    private long[][] memoizationTable;

    public SequenceAlignmentBasicAlgorithm(int gapPenalty) {
        this.gapPenalty = gapPenalty;
    }

    public String[] getAlignment(String firstSequence, String secondSequence) {
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
                //finding the minimum value out of the three variables
                memoizationTable[i][j] = Math.min(Math.min(costWhenMatched, costWithGapAtFirstSequence), costWithGapAtSecondSequence);

            }
        }
        OutputSequenceConstructor outputSequenceConstructor = new OutputSequenceConstructor();
        return outputSequenceConstructor.reconstructOutputFromMemoizationTable(memoizationTable, firstSequence, secondSequence, gapPenalty);
    }

    public Output alignSequences(String firstSequence, String secondSequence) {
        String[] resultSequenceTrimmed = UtilityFunctions.getOutputSequences(getAlignment(firstSequence, secondSequence));
        Output output = new Output();
        output.firstSequenceResult = resultSequenceTrimmed[0] + " " + resultSequenceTrimmed[1];
        output.secondSequenceResult = resultSequenceTrimmed[2] + " " + resultSequenceTrimmed[3];
        output.minCost = memoizationTable[firstSequence.length()][secondSequence.length()];
        return output;
    }
}
