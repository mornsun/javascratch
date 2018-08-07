package lintcode;

import java.util.*;

/**
 * Give you an integer array (index from 0 to n-1, where n is the size of this array)，find the longest increasing continuous subsequence in this array. (The definition of the longest increasing continuous subsequence here can be from right to left or from left to right)

Have you met this question in a real interview? Yes
Example
For [5, 4, 2, 1, 3], the LICS is [5, 4, 2, 1], return 4.

For [5, 1, 2, 3, 4], the LICS is [1, 2, 3, 4], return 4.

Note
O(n) time and O(1) extra space.

Tags Expand 
Dynamic Programming Array


Related Problems Expand 
Hard Longest Increasing Continuous subsequence II 21 %

 * @author Chauncey
 *
 */
public class LongestIncreasingContinuoussubsequence
{
    /**
     * @param A an array of Integer
     * @return  an integer
     */
    public int longestIncreasingContinuousSubsequence(int[] A) {
        if (A==null || A.length==0) return 0;
        int lo = 0;
        boolean asc = false;
        int max = 1;
        for (int i=1; i<A.length; ++i) {
            if (A[i] >= A[i-1] && asc == false) {
                if (i-lo > max) max = i-lo;
                asc = true;
                lo = i - 1;
            } else if (A[i] <= A[i-1] && asc == true) {
                if (i-lo > max) max = i-lo;
                asc = false;
                lo = i - 1;
            }
        }
        if (A.length-lo > max) max = A.length-lo;
        return max;
    }
    
    /**
     * @param args
     */
    public static void main(String[] args)
    {
        LongestIncreasingContinuoussubsequence solution = new LongestIncreasingContinuoussubsequence();

        //4
        System.out.println(solution.longestIncreasingContinuousSubsequence(new int[]{5, 4, 2, 1, 3}));
        //4
        System.out.println(solution.longestIncreasingContinuousSubsequence(new int[]{5, 1, 2, 3, 4}));
        //4
        System.out.println(solution.longestIncreasingContinuousSubsequence(new int[]{5, 5, 4, 2, 1, 3}));
    }

}
