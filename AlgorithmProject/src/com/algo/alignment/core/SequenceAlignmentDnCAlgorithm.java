package com.algo.alignment.core;

import com.algo.alignment.utils.UtilityFunctions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class SequenceAlignmentDnCAlgorithm {
    SequenceAlignmentBasicAlgorithm sequenceAlignmentBasicAlgorithm= new SequenceAlignmentBasicAlgorithm(30);
    int globalCost = 0;
    public List<Integer> arrowPath = new ArrayList<>();
    int[][]dpTable;
    String[] alignment;

    public String[] getAlignment(String input1, String input2) {
        int m = input1.length();
        int n = input2.length();
        int best = Integer.MAX_VALUE;
        int bestQ = 0;

        if (m <= 2 || n == 0) {
            dpTable = sequenceAlignmentBasicAlgorithm.alignSequencesMatrix(input1, input2);
            alignment = sequenceAlignmentBasicAlgorithm.reconstructOutputFromMemoizationTable(dpTable, input1, input2, 30);
            return alignment;
        } else {
            int[][] prefix = spaceEfficient(input1.substring(0, m/2+1), input2);
            int[][] suffix = spaceEfficient(new StringBuffer(input1.substring(m/2+1)).reverse().toString(), new StringBuffer(input2).reverse().toString());
            int minAlignCost = Integer.MAX_VALUE;
            int bestIndex = 0;
            int currAlignCost;
            for (int j = 1; j <= n; j++) {
                currAlignCost = prefix[1][j] + suffix[1][n-j];
                if (currAlignCost < minAlignCost) {
                    minAlignCost = currAlignCost;
                    bestIndex = j;
                }
            }

            int split = bestIndex - 1;
            char midChar = input1.charAt(m / 2);
            char matchChar = input2.charAt(split);
            int midCharCost = prefix[1][bestIndex];
            int gapCharCost = prefix[0][bestIndex];

            if (midCharCost == gapCharCost + 30) {
                String[] prefixAlignment = getAlignment(input1.substring(0, m/2), input2.substring(0, split + 1));
                String[] suffixAlignment = getAlignment(input1.substring(m/2+1), input2.substring(split + 1));
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
                String[] prefixAlignment = getAlignment(new StringBuffer(input1.substring(0, m / 2)).reverse().toString(), input2.substring(0, split));

                String[] suffixAlignment = getAlignment(input1.substring(m / 2), input2.substring(split + 1));
                StringBuffer sb = new StringBuffer();
                sb.append(prefixAlignment[0]);
                sb.append(midChar);
                sb.append(suffixAlignment[0]);
                String res1 = sb.toString();

                sb = new StringBuffer();
                sb.append(prefixAlignment[1]);
                sb.append(matchChar);
                sb.append(suffixAlignment[1]);
                String res2 = sb.toString();

                return new String[]{res1, res2};
            }
        }
    }

    public int[][] spaceEfficient(String input1, String input2) {
        int m = input1.length();
        int n = input2.length();

        int[][] dp = new int[2][n+1];

        for(int i = 0; i <= n; i++) {
            dp[0][i] = i * 30;
        }

        for(int i = 1; i <= m; i++) {
            dp[1][0] = i * 30;

            for(int j = 1; j <= n; j++) {
                dp[1][j] = Math.min(UtilityFunctions.getMismatchPenalty(input1.charAt(i-1), input2.charAt(j-1)) + dp[0][j-1], 30 + dp[1][j-1]);
                dp[1][j] = Math.min(dp[1][j], 30 + dp[0][j]);
            }
            if (i < input1.length()) {
                for(int j = 0; j <= n; j++) {
                    dp[0][j] = dp[1][j];
                }
            }
        }
        return dp;
    }

    public int minCost(String str1, String str2) {
        int cost = 0;
        for(int i = 0; i < str1.length(); i++) {
            if(str1.charAt(i) == '_' || str2.charAt(i) == '_') {
                cost += 30;
            }
            else {
                cost += UtilityFunctions.getMismatchPenalty(str1.charAt(i), str2.charAt(i));
            }
        }
        return cost;
    }
}
