import java.util.ArrayList;
import java.util.List;

public class SequenceAlignmentDnCAlgorithm {
    SequenceAlignmentBasicAlgorithm sequenceAlignmentBasicAlgorithm= new SequenceAlignmentBasicAlgorithm(30);
    int globalCost = 0;
    public List<Integer> arrowPath = new ArrayList<>();


    public void getAlignment(String input1, String input2) {
        int m = input1.length();
        int n = input2.length();
        int best = Integer.MAX_VALUE;
        int bestQ = 0;

        if(m <= 2 || n <= 2) {
            globalCost += sequenceAlignmentBasicAlgorithm.alignSequences(input1, input2).minCost;
            System.out.println(globalCost);
        }
        else {
            int v1, v2;
            for(int q = 1; q < n; q++) {
                v1 = spaceEfficientAlignment(input1.substring(0, m/2), input2.substring(0, q));
                v2 = spaceEfficientAlignment(input1.substring(m/2), input2.substring(q));
                //System.out.println("sum " + (v1+v2));
                //System.out.println("q " + q);
                if((v1 + v2) < best) {
                    bestQ = q;
                    best = v1 + v2;
                }
            }

            arrowPath.add(m/2);
            arrowPath.add(bestQ);

            //System.out.println("q: " + bestQ);
            getAlignment(input1.substring(0, m/2), input2.substring(0, bestQ));
            getAlignment(input1.substring(m/2), input2.substring(bestQ));
        }
    }

    public int spaceEfficientAlignment(String input1, String input2) {

        int m = input1.length();
        int n = input2.length();

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
            for(int i = 0; i <= m; i++) {
                dp[i][0] = dp[i][1];
            }
        }
        return dp[m][1];
    }
}
