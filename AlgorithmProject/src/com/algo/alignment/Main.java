package com.algo.alignment;

//TODO: to be removed 
//golden source of truth
public class Main {

    public static int cost(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();
        int[][] edits = new int[n + 1][m + 1];
        edits[0][0] = 0;
        for (int i = 1; i <= n; i++)
            edits[i][0] = i;
        for (int j = 1; j <= m; j++)
            edits[0][j] = j;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                // For adding a letter or deleting a letter we put a _ whose cost is 30
                int delta = 30;
                int min_val = Math.min(edits[i - 1][j] + delta, edits[i][j - 1] + delta);
                // For replacing a letter with other we have the alpha costs in matrix
                int alpha = 0;
                if (s1.charAt(i - 1) != s2.charAt(j - 1)) {
                    if ((s1.charAt(i - 1) == 'A' && s2.charAt(i - 1) == 'C')
                            || (s1.charAt(i - 1) == 'C' && s2.charAt(i - 1) == 'A'))
                        alpha = 110;
                    if ((s1.charAt(i - 1) == 'A' && s2.charAt(i - 1) == 'G')
                            || (s1.charAt(i - 1) == 'G' && s2.charAt(i - 1) == 'A'))
                        alpha = 48;
                    if ((s1.charAt(i - 1) == 'A' && s2.charAt(i - 1) == 'T')
                            || (s1.charAt(i - 1) == 'T' && s2.charAt(i - 1) == 'A'))
                        alpha = 94;
                    if ((s1.charAt(i - 1) == 'C' && s2.charAt(i - 1) == 'G')
                            || (s1.charAt(i - 1) == 'G' && s2.charAt(i - 1) == 'C'))
                        alpha = 118;
                    if ((s1.charAt(i - 1) == 'C' && s2.charAt(i - 1) == 'T')
                            || (s1.charAt(i - 1) == 'T' && s2.charAt(i - 1) == 'C'))
                        alpha = 48;
                    if ((s1.charAt(i - 1) == 'G' && s2.charAt(i - 1) == 'T')
                            || (s1.charAt(i - 1) == 'T' && s2.charAt(i - 1) == 'G'))
                        alpha = 110;
                }
                edits[i][j] = Math.min(min_val, edits[i - 1][j - 1] + alpha);
            }
        }
        findAlignment(s1, s2, edits);
        return edits[n][m];
    }


    private static int mismatchPenalty(char char1, char char2) {
        int alpha=0;
        if (char1 !=char2) {
            if ((char1 == 'A' &&char2 == 'C')
                    || (char1 == 'C' &&char2 == 'A'))
                alpha = 110;
            if ((char1 == 'A' &&char2 == 'G')
                    || (char1 == 'G' &&char2 == 'A'))
                alpha = 48;
            if ((char1 == 'A' &&char2 == 'T')
                    || (char1 == 'T' &&char2 == 'A'))
                alpha = 94;
            if ((char1 == 'C' && char2 == 'G')
                    || (char1 == 'G' &&char2 == 'C'))
                alpha = 118;
            if ((char1 == 'C' &&char2 == 'T')
                    || (char1 == 'T' &&char2 == 'C'))
                alpha = 48;
            if ((char1 == 'G' &&char2 == 'T')
                    || (char1 == 'T' &&char2 == 'G'))
                alpha = 110;
        }


        return alpha;
    }



    private static void findAlignment(String seq1, String seq2, int[][] memoTable) {
        String seq1Aligned = ""; 	//Holds the actual sequence with gaps added
        String seq2Aligned = "";

        int i = seq1.length() - 1; //-1 since seq1 & seq2 have leading space
        int j = seq2.length() - 1;

        int gapPenalty=30;
        String GAP_CHAR="_";
        //Retrace the memoTable calculations. Stops when reaches the start of 1 sequence (so additional gaps may still need to be added to the other)
        while (i > 0 && j > 0) {
            if (memoTable[i][j] - mismatchPenalty(seq1.charAt(i), seq2.charAt(j)) == memoTable[i - 1][j - 1]) { //case1: both aligned
                seq1Aligned = seq1.charAt(i) + seq1Aligned;
                seq2Aligned = seq2.charAt(j) + seq2Aligned;
                i--;
                j--;
            }
            else if (memoTable[i][j] -gapPenalty == memoTable[i - 1][j]) { //case2: seq1 with gap
                seq1Aligned = seq1.charAt(i) + seq1Aligned;
                seq2Aligned = GAP_CHAR + seq2Aligned;
                i--;
            }
            else if (memoTable[i][j] - gapPenalty == memoTable[i][j - 1]) { //case3: seq2 with gap
                seq2Aligned = seq2.charAt(j) + seq2Aligned;
                seq1Aligned = GAP_CHAR + seq1Aligned;
                j--;
            }
        }
        //Now i==0 or j==0 or both. Finish by adding any additional leading gaps to the start of the sequence whose pointer ISN'T == 0
        while (i > 0) {		//Seq1 reached the beginning, print rest of seq2 & add gaps to seq2
            seq1Aligned = seq1.charAt(i) + seq1Aligned;
            seq2Aligned = GAP_CHAR + seq2Aligned;
            i--;
        }
        while (j > 0) {		//Seq2 reached the beginning, print rest of seq1 & add gaps to seq2
            seq2Aligned = seq2.charAt(j) + seq2Aligned;
            seq1Aligned = GAP_CHAR + seq1Aligned;
            j--;
        }

        System.out.println("\nOptimal Alignment:\n" + seq1Aligned + "\n" + seq2Aligned + "\n\n");
    }


    public static void main(String[] args) {
        // TODO Auto-generated method stub
        String s1 = "ACACTGACTACTGACTGGTGACTACTGACTGG";
        String s2 = "TATTATACGCTATTATACGCGACGCGGACGCG";
        System.out.println("Min cost to make s1 to s2 = " + cost(s1, s2));

    }

}
