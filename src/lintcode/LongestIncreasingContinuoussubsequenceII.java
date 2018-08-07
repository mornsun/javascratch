package lintcode;

import java.util.*;

/**
 * Give you an integer matrix (with row size n, column size m)，find the longest increasing continuous subsequence in this matrix. (The definition of the longest increasing continuous subsequence here can start at any row or column and go up/down/right/left any direction).

Have you met this question in a real interview? Yes
Example
Given a matrix:

[
  [1 ,2 ,3 ,4 ,5],
  [16,17,24,23,6],
  [15,18,25,22,7],
  [14,19,20,21,8],
  [13,12,11,10,9]
]
return 25

Challenge
O(nm) time and memory.

Tags Expand 
Dynamic Programming


Related Problems Expand 
Easy Longest Increasing Continuous subsequence 26 %

 * @author Chauncey
 *
 */
public class LongestIncreasingContinuoussubsequenceII
{
    /**
     * @param A an integer matrix
     * @return  an integer
     */
    public int longestIncreasingContinuousSubsequenceII(int[][] A) {
        if (A==null || A.length==0 || A[0].length==0) return 0;
        int m = A.length;
        int n = A[0].length;
        int max = 0;
        int[][][] step = new int[m][n][4]; //up, down, left, right
        for (int i=0; i<m; ++i) {
            for (int j=0; j<n; ++j) {
                max = Math.max(max, dfs(A, step, i, j));
            }
        }
        return max+1;
    }
    
    private final int dfs(int[][] A, int[][][] step, int i, int j) {
        int max = 0;
        if (i > 0 &&  A[i][j]<A[i-1][j]) { //up
            if (step[i][j][0] == 0) {
                step[i][j][0] = dfs(A, step, i-1, j) + 1;
            }
            max = step[i][j][0];
        }
        if (i < A.length-1 && A[i][j]<A[i+1][j]) { //down
            if (step[i][j][1] == 0) {
                step[i][j][1] = dfs(A, step, i+1, j) + 1;
            }
            max = Math.max(max, step[i][j][1]);
        }
        if (j > 0 && A[i][j]<A[i][j-1]) { //left
            if (step[i][j][2] == 0) {
                step[i][j][2] = dfs(A, step, i, j-1) + 1;
            }
            max = Math.max(max, step[i][j][2]);
        }
        if (j < A[0].length-1 && A[i][j]<A[i][j+1]) { //down
            if (step[i][j][3] == 0) {
                step[i][j][3] = dfs(A, step, i, j+1) + 1;
            }
            max = Math.max(max, step[i][j][3]);
        }
        return max;
    }
    
    /**
     * @param args
     */
    public static void main(String[] args)
    {
        LongestIncreasingContinuoussubsequenceII solution = new LongestIncreasingContinuoussubsequenceII();

        int[][] matrix = new int[][]{
                  {1 ,2 ,3 ,4 ,5},
                  {16,17,24,23,6},
                  {15,18,25,22,7},
                  {14,19,20,21,8},
                  {13,12,11,10,9}};
        //25
        System.out.println(solution.longestIncreasingContinuousSubsequenceII(matrix));
        matrix = new int[][]{
                  {13,12,11,10,9},
                  {14,19,20,21,8},
                  {15,18,25,22,7},
                  {16,17,24,23,6},
                  {1 ,2 ,3 ,4 ,5}};
        //25
        System.out.println(solution.longestIncreasingContinuousSubsequenceII(matrix));
        matrix = new int[][]{
                  {1,1,1},
                  {1,1,1},
                  {1,1,1}};
        //25
        System.out.println(solution.longestIncreasingContinuousSubsequenceII(matrix));
    }

}
