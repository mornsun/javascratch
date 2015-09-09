package lintcode;

import java.util.*;

/**
 * There are n coins in a line. Two players take turns to take a coin from one of the ends of the line until there are no more coins left. The player with the larger amount of money wins.

Could you please decide the first player will win or lose?

Have you met this question in a real interview? Yes
Example
Given array A = [3,2,2], return true.

Given array A = [1,2,4], return true.

Given array A = [1,20,4], return false.

Challenge
Follow Up Question:

If n is even. Is there any hacky algorithm that can decide whether first player will win or lose in O(1) memory and O(n) time?

Tags Expand 
Dynamic Programming Array Game Theory


Related Problems Expand 
Medium Coins in a Line II 30 %
Medium Coins in a Line 41 %

 * @author Chauncey
 *
 */
public class CoinsinaLineIII
{
    /**
     * @param values: an array of integers
     * @return: a boolean which equals to true if the first player will win
     */
    public boolean firstWillWin(int[] values) {
    	if (values==null || values.length<3) return true;
    	int f[] = new int [values.length];
    	int f_prev[] = new int [values.length];
    	int sum[] = new int [values.length];
    	int sum_prev[] = new int [values.length];
    	for (int i=values.length-1; i>=0; --i) {
    		sum[i] = values[i];
    		f[i] = values[i];
    		for (int j=i+1; j<values.length; ++j) {
    			sum[j] = sum[j-1]+values[j];
        		f[j] = Math.max(sum_prev[j]-f_prev[j]+values[i], sum[j-1]-f[j-1]+values[j]);
    		}
    		int tmp[] = f;
    		f = f_prev;
    		f_prev = tmp;
    		tmp = sum;
    		sum = sum_prev;
    		sum_prev = tmp;
    	}
    	return f_prev[values.length-1]>f[values.length-1] ||
    			f_prev[values.length-1] > f_prev[values.length-2];
    }
    
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		CoinsinaLineIII solution = new CoinsinaLineIII();

		//true
		System.out.println(solution.firstWillWin(new int[]{3,2,2}));
		//true
		System.out.println(solution.firstWillWin(new int[]{1,2,4}));
		//false
		System.out.println(solution.firstWillWin(new int[]{1,20,4}));
	}

}
