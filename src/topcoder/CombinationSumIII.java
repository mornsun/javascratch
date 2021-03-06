/**
 * 
 */
package topcoder;

import java.util.*;

/**
 * Find all possible combinations of k numbers that add up to a number n, given that only numbers from 1 to 9 can be used and each combination should be a unique set of numbers.

Ensure that numbers within the set are sorted in ascending order.


Example 1:

Input: k = 3, n = 7

Output:

[[1,2,4]]

Example 2:

Input: k = 3, n = 9

Output:

[[1,2,6], [1,3,5], [2,3,4]]
Credits:
Special thanks to @mithmatt for adding this problem and creating all test cases.

Hide Tags Array Backtracking
Hide Similar Problems (M) Combination Sum

 * @author Chauncey
 *
 */
public class CombinationSumIII
{
	private final static void dfs(int[] candidates, int gap, int k, int start, LinkedList<Integer> one_res, List<List<Integer>> res)
	{
		for (int i = start; i<candidates.length; ++i) {
			int ngap = gap - candidates[i];
			if (ngap == 0) {
				if (k==1) {
					one_res.addLast(candidates[i]);
					LinkedList<Integer> new_res = new LinkedList<Integer>(one_res);
					res.add(new_res);
					one_res.pollLast();
				}
				continue;
			} else if (ngap <= 0) {
				continue;
			} else {
				if (k>1) {
					one_res.addLast(candidates[i]);
					dfs(candidates, ngap, k-1, i+1, one_res, res);
					one_res.pollLast();
				}
			}
		}
	}
    public static List<List<Integer>> combinationSum3(int k, int n) {
    	List<List<Integer>> res = new LinkedList<List<Integer>>();
    	LinkedList<Integer> one_res = new LinkedList<Integer>();
    	int[] candidates = new int[]{1,2,3,4,5,6,7,8,9};
    	//Arrays.sort(candidates);
    	dfs(candidates, n, k, 0, one_res, res);
    	return res;
    }
	    
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		int[] nums = new int[]{10,1,2,7,6,1,5};
		List<List<Integer>> list = combinationSum3(3, 9);
		System.out.println(list);
		
	}

}
