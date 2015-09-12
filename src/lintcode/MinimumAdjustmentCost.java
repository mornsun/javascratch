package lintcode;

import java.util.*;

/**
 * Given an integer array, adjust each integers so that the difference of every adjacent integers are not greater than a given number target.

If the array before adjustment is A, the array after adjustment is B, you should minimize the sum of |A[i]-B[i]|

Have you met this question in a real interview? Yes
Example
Given [1,4,2,3] and target = 1, one of the solutions is [2,3,2,3], the adjustment cost is 2 and it's minimal.

Return 2.

Note
You can assume each number in the array is a positive integer and not greater than 100.

Tags Expand 
LintCode Copyright Dynamic Programming Backpack

 * @author Chauncey
 *
 */
public class MinimumAdjustmentCost
{
    /**
     * @param A: An integer array.
     * @param target: An integer.
     */
    public int MinAdjustmentCost(ArrayList<Integer> A, int target) {
    	if (null == A || A.size()==0 || target<0) return 0;
    	int sz = A.size();
    	int[][] f = new int[sz][101];
		int num = A.get(0);
    	for (int j=1; j<=100; ++j) {
    		f[0][j] = Math.abs(j-num);
    		//System.out.print(f[0][j]+"("+0+":"+j+"),");
    	}
    	//System.out.println();
    	for (int i=1; i<sz; ++i) {
    		num = A.get(i);
    		for (int j=1; j<=100; ++j) {
        		int min = Integer.MAX_VALUE;
        		int start = Math.max(j-target, 1);
        		int end = Math.min(j+target,100);
	    		for (int k=start; k<=end; ++k) {
	    			min = Math.min(min, f[i-1][k]);
	    		}
	    		f[i][j] = min + Math.abs(num-j);
	    		//System.out.print(f[i][j]+"("+i+":"+j+"),");
    		}
        	//System.out.println(num);
    	}
    	int min = f[sz-1][1];
    	for (int j=2; j<=100; ++j) {
    		min = Math.min(f[sz-1][j], min);
    	}
    	return min;
    }
    
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		MinimumAdjustmentCost solution = new MinimumAdjustmentCost();

		//2
		System.out.println(solution.MinAdjustmentCost(new ArrayList<Integer>(Arrays.asList(new Integer[]{1,4,2,3})), 1));
	}

}
