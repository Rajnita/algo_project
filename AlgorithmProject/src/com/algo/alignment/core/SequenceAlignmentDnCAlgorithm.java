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


    public int getAlignment(String input1, String input2) {
        int m = input1.length();
        int n = input2.length();
        int best = Integer.MAX_VALUE;
        int bestQ = 0;

        if(m <= 2 || n <= 2) {
            globalCost += sequenceAlignmentBasicAlgorithm.alignSequences(input1, input2);
            //System.out.println(globalCost);
        }
        else {
            LinkedList<Integer> v1 = AllInput2PrefixCosts(input1.substring(0, m/2), input2);
            System.out.println("v1 " + v1);
            LinkedList<Integer> v2 = AllInput2SuffixCosts(input1.substring(m/2), input2);
            //System.out.println("sum " + (v1+v2));
            System.out.println("v2 " + v2);
            //System.out.println("q " + q);
            for(int q = 0; q < v1.size(); q++) {
                if((v1.get(q) + v2.get(q)) < best) {
                    bestQ = q+1;
                    best = v1.get(q) + v2.get(q);
                    System.out.println("sum " + best);
                }
            }

            arrowPath.add(m/2);
            arrowPath.add(bestQ);

            //System.out.println("q: " + bestQ);
            getAlignment(input1.substring(0, m/2), input2.substring(0, bestQ));
            getAlignment(input1.substring(m/2), input2.substring(bestQ));
        }
        return globalCost;
    }

    private LinkedList<Integer> AllInput2SuffixCosts(String input1, String input2) {
        input1 = new StringBuffer(input1).reverse().toString();
        int m = input1.length();
        int n = input2.length();

        LinkedList<Integer> result = new LinkedList<>();
        int[][] dp = new int[m+1][2];

        for(int i = 0; i <= m; i++) {
            dp[i][0] = i * 30;
        }

        for(int j = 1; j <= n; j++) {
            dp[0][1] = j * 30;

            for(int i = 1; i <= m; i++) {
                dp[i][1] = Math.min(UtilityFunctions.getMismatchPenalty(input1.charAt(i-1), input2.charAt(j-1)) + dp[i-1][0], 30 + dp[i-1][1]);
                dp[i][1] = Math.min(dp[i][1], 30 + dp[i][0]);
                //System.out.print(dp[i][1] + " ");
            }
            //System.out.println();
            System.out.print(Arrays.toString(dp[m]));
            for(int i = 0; i <= m; i++) {
                dp[i][0] = dp[i][1];
            }

            //result.addLast(dp[m][1]);
            //System.out.print(dp[m][1] + " ");

        }
//        for(int i = 0; i <= m; i++) {
//            result.addFirst(dp[i][1]);
//            //System.out.print(dp[i][1] + " ");
//        }
        return result;
    }

    public LinkedList<Integer> AllInput2PrefixCosts(String input1, String input2) {
        int m = input1.length();
        int n = input2.length();

        LinkedList<Integer> result = new LinkedList<>();
        int[][] dp = new int[m+1][2];

        for(int i = 0; i <= m; i++) {
            dp[i][0] = i * 30;
        }

        for(int j = 1; j <= n; j++) {
            dp[0][1] = j * 30;

            for(int i = 1; i <= m; i++) {
                dp[i][1] = Math.min(UtilityFunctions.getMismatchPenalty(input1.charAt(i-1), input2.charAt(j-1)) + dp[i-1][0], 30 + dp[i-1][1]);
                dp[i][1] = Math.min(dp[i][1], 30 + dp[i][0]);
                System.out.print(dp[i][1] + " ");
            }
            System.out.println();
            for(int i = 0; i <= m; i++) {
                dp[i][0] = dp[i][1];
            }
            result.addLast(dp[m][1]);
            //System.out.print(dp[m][1] + " ");

        }
//        for(int i = 0; i <= m; i++) {
//            result.addFirst(dp[i][1]);
//            //System.out.print(dp[i][1] + " ");
//        }
        return result;
    }
}
