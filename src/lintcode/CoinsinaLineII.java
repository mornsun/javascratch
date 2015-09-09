package lintcode;

import java.util.*;

import topcoder.BinarySearchTreeIterator.TreeNode;
import topcoder.LinkedListCycle.ListNode;

/**
 * There are n coins with different value in a line. Two players take turns to take one or two coins from left side until there are no more coins left. The player who take the coins with the most value wins.

Could you please decide the first player will win or lose?

Have you met this question in a real interview? Yes
Example
Given values array A = [1,2,2], return true.

Given A = [1,2,4], return false.

Tags Expand 
Dynamic Programming Array Game Theory


Related Problems Expand 
Hard Coins in a Line III 30 %
Medium Coins in a Line 41 %

 * @author Chauncey
 *
 */
public class CoinsinaLineII
{
    /**
     * @param values: an array of integers
     * @return: a boolean which equals to true if the first player will win
     */
    public boolean firstWillWin(int[] values) {
    	if (values==null || values.length<3) return true;
    	int f3 = 0;
    	int f2 = values[values.length-1];
    	int f1 = values[values.length-1]+values[values.length-2];
    	int sum3 = 0;
    	int sum2 = f2;
    	int sum1 = f1;
    	for (int i=values.length-3; i>=0; --i) {
    		sum3 = sum2;
    		sum2 = sum1;
    		sum1 = sum2 + values[i];
    		f3 = f2;
    		f2 = f1;
    		f1 = Math.max(sum2-f2+values[i], sum3-f3+values[i]+values[i+1]);
    	}
    	return f1 > f2 || f1 > f3;
    }
    
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		CoinsinaLineII solution = new CoinsinaLineII();

		//true
		System.out.println(solution.firstWillWin(new int[]{1,2,2}));
		//false
		System.out.println(solution.firstWillWin(new int[]{1,2,4}));
	}

}
