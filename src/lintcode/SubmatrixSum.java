package lintcode;

import java.util.*;

/**
 * Given an integer matrix, find a submatrix where the sum of numbers is zero. Your code should return the coordinate of the left-up and right-down number.

Have you met this question in a real interview? Yes
Example
Given matrix

[
  [1 ,5 ,7],
  [3 ,7 ,-8],
  [4 ,-8 ,9],
]
return [(1,1), (2,2)]

Challenge
O(n3) time.

Tags Expand 
Enumeration Matrix


Related Problems Expand 
Medium Subarray Sum Closest 16 %
Easy Subarray Sum 25 %

 * @author Chauncey
 *
 */
public class SubmatrixSum
{
    /**
     * @param matrix an integer matrix
     * @return the coordinate of the left-up and right-down number
     */
    public int[][] submatrixSum(int[][] matrix) {
        if (null == matrix || matrix.length==0 || matrix[0].length==0) return matrix;
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] dp = new int[m+1][n+1];
        for (int i=1; i<=m; ++i) {
            int sum = 0;
            for (int j=1; j<=n; ++j) {
                sum += matrix[i-1][j-1];
                dp[i][j] = dp[i-1][j] + sum;
            }
        }
        for (int top=0; top<m; ++top) {
            for (int bottom=top+1; bottom<=m; ++bottom) {
                HashMap<Integer,Integer> map = new HashMap<Integer, Integer>();
                map.put(0, 0);
                for (int j=1; j<=n; ++j) {
                    int sum = dp[bottom][j] - dp[top][j];
                    Integer prev = map.get(sum);
                    if (prev == null) {
                        map.put(sum, j);
                    } else {
                        return new int[][]{{top, prev},{bottom-1, j-1}};
                    }
                }
            }
        }
        return null;
    }
    
    /**
     * @param args
     */
    public static void main(String[] args)
    {
        SubmatrixSum solution = new SubmatrixSum();
        int[][] matrix = new int[][]{
                  {1 ,5 ,7},
                  {3 ,7 ,-8},
                  {4 ,-8 ,9},
        };

        //int[][] matrix = new int[][]{{0}};
        int[][] res = solution.submatrixSum(matrix);
        for (int[] cord : res) {
            for (int x : cord) {
                System.out.print(x+",");
            }
            System.out.println();
        }
        matrix = new int[][]{{0}};
        res = solution.submatrixSum(matrix);
        for (int[] cord : res) {
            for (int x : cord) {
                System.out.print(x+",");
            }
            System.out.println();
        }
    }

}
