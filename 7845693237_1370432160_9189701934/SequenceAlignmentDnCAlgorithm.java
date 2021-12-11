
public class SequenceAlignmentDnCAlgorithm {
    SequenceAlignmentBasicAlgorithm sequenceAlignmentBasicAlgorithm = new SequenceAlignmentBasicAlgorithm(30);


    private String[] getAlignment(String input1, String input2) {
        int m = input1.length();
        int n = input2.length();

        if (m <= 2 || n == 0) {
            return sequenceAlignmentBasicAlgorithm.getAlignment(input1, input2);
        } else {
            int[][] prefix = spaceEfficient(input1.substring(0, m / 2 + 1), input2);
            int[][] suffix = spaceEfficient(new StringBuffer(input1.substring(m / 2 + 1)).reverse().toString(), new StringBuffer(input2).reverse().toString());
            int minAlignCost = Integer.MAX_VALUE;
            int bestIndex = 0;
            int currAlignCost;
            for (int j = 0; j <= n; j++) {
                currAlignCost = prefix[1][j] + suffix[1][n - j];
                if (currAlignCost < minAlignCost) {
                    minAlignCost = currAlignCost;
                    bestIndex = j;
                }
            }

            char matchChar;
            int split = bestIndex - 1;
            char midChar = input1.charAt(m / 2);
            if (split == -1) {
                matchChar = input2.charAt(input2.length() - 1);
            } else {
                matchChar = input2.charAt(split);
            }

            int midCharCost = prefix[1][bestIndex];
            int gapCharCost = prefix[0][bestIndex];

            if (midCharCost == (gapCharCost + 30)) {
                String[] prefixAlignment = getAlignment(input1.substring(0, m / 2), input2.substring(0, split + 1));
                String[] suffixAlignment = getAlignment(input1.substring(m / 2 + 1), input2.substring(split + 1));
                StringBuffer sb = new StringBuffer();
                sb.append(prefixAlignment[0]);
                sb.append(midChar);
                sb.append(suffixAlignment[0]);
                String res1 = sb.toString();

                sb = new StringBuffer();
                sb.append(prefixAlignment[1]);
                sb.append('_');
                sb.append(suffixAlignment[1]);
                String res2 = sb.toString();

                return new String[]{res1, res2};
            } else {
                String[] prefixAlignment = getAlignment(input1.substring(0, m / 2), input2.substring(0, split));

                String[] suffixAlignment = getAlignment(input1.substring(m / 2 + 1), input2.substring(split + 1));
                StringBuilder sb = new StringBuilder();
                sb.append(prefixAlignment[0]);
                sb.append(midChar);
                sb.append(suffixAlignment[0]);
                String res1 = sb.toString();

                sb = new StringBuilder();
                sb.append(prefixAlignment[1]);
                sb.append(matchChar);
                sb.append(suffixAlignment[1]);
                String res2 = sb.toString();

                return new String[]{res1, res2};
            }
        }
    }


    public Output alignSequences(String input1, String input2) {
        String[] completeResultStrings = getAlignment(input1, input2);
        String[] trimmedResultStrings = UtilityFunctions.getOutputSequences(completeResultStrings);
        Output output = new Output();
        output.minCost = minCost(completeResultStrings[0], completeResultStrings[1]);
        output.firstSequenceResult = trimmedResultStrings[0] + " " + trimmedResultStrings[1];
        output.secondSequenceResult = trimmedResultStrings[2] + " " + trimmedResultStrings[3];
        return output;
    }

    public int[][] spaceEfficient(String input1, String input2) {
        int m = input1.length();
        int n = input2.length();

        int[][] dp = new int[2][n + 1];

        for (int i = 0; i <= n; i++) {
            dp[0][i] = i * 30;
        }

        for (int i = 1; i <= m; i++) {
            dp[1][0] = i * 30;

            for (int j = 1; j <= n; j++) {
                dp[1][j] = Math.min(UtilityFunctions.getMismatchPenalty(input1.charAt(i - 1), input2.charAt(j - 1)) + dp[0][j - 1], 30 + dp[1][j - 1]);
                dp[1][j] = Math.min(dp[1][j], 30 + dp[0][j]);
            }
            if (i < input1.length()) {
                System.arraycopy(dp[1], 0, dp[0], 0, n + 1);
            }
        }
        return dp;
    }

    public int minCost(String str1, String str2) {
        int cost = 0;
        for (int i = 0; i < str1.length(); i++) {
            if (str1.charAt(i) == '_' || str2.charAt(i) == '_') {
                cost += 30;
            } else {
                cost += UtilityFunctions.getMismatchPenalty(str1.charAt(i), str2.charAt(i));
            }
        }
        return cost;
    }

}
