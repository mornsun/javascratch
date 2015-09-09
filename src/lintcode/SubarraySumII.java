package lintcode;

import java.util.*;

/**
 * Given an integer array, find a subarray where the sum of numbers is between two given interval. Your code should return the number of possible answer.

Have you met this question in a real interview? Yes
Example
Given [1,2,3,4] and interval = [1,3], return 4. The possible answers are:

[0, 0]
[0, 1]
[1, 1]
[2, 2]
Tags Expand 
Two Pointers Array


Related Problems Expand 
Medium Subarray Sum Closest 16 %
Easy Subarray Sum 25 %

 * @author Chauncey
 *
 */
public class SubarraySumII
{
    /**
     * @param A an integer array
     * @param start an integer
     * @param end an integer
     * @return the number of possible answer
     */
    public int subarraySumII(int[] A, int start, int end) {
    	if (A==null || A.length==0) return 0;
    	int res = 0;
    	TreeSet<Integer> map = new TreeSet<Integer>();
    	int sum = 0;
    	for (int i=0; i< A.length; ++i) {
    		map.add(sum);
    		sum += A[i];
    		res += map.subSet(sum-end, true, sum-start, true).size();
    	}
    	return res;
    }
    
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		SubarraySumII solution = new SubarraySumII();

		//4
		System.out.println(solution.subarraySumII(new int[]{1,2,3,4}, 1, 3));
	}

}
