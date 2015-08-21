/**
 * 
 */
package topcoder;

import java.util.*;

/**
 * Given a set of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.

The same repeated number may be chosen from C unlimited number of times.

Note:
All numbers (including target) will be positive integers.
Elements in a combination (a1, a2, … , ak) must be in non-descending order. (ie, a1 ≤ a2 ≤ … ≤ ak).
The solution set must not contain duplicate combinations.
For example, given candidate set 2,3,6,7 and target 7, 
A solution set is: 
[7] 
[2, 2, 3] 
Hide Tags Array Backtracking
Hide Similar Problems (M) Letter Combinations of a Phone Number (M) Combination Sum II (M) Combinations (M) Combination Sum III (M) Factor Combinations

 * @author Chauncey
 *
 */
public class CombinationSum
{
	private final static void dfs(int[] candidates, int gap, int start, LinkedList<Integer> one_res, List<List<Integer>> res)
	{
		for (int i = start; i<candidates.length; ++i) {
			int ngap = gap - candidates[i];
			if (ngap == 0) {
				one_res.addLast(candidates[i]);
				LinkedList<Integer> new_res = new LinkedList<Integer>(one_res);
				res.add(new_res);
				one_res.pollLast();
				continue;
			} else if (ngap < 0) {
				continue;
			} else {
				one_res.addLast(candidates[i]);
				dfs(candidates, ngap, i, one_res, res);
				one_res.pollLast();
			}
		}
	}
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
    	List<List<Integer>> res = new LinkedList<List<Integer>>();
    	LinkedList<Integer> one_res = new LinkedList<Integer>();
    	Arrays.sort(candidates);
    	dfs(candidates, target, 0, one_res, res);
    	return res;
    }
	    
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		int[] nums = new int[]{10,1,2,7,6,1,5};
		List<List<Integer>> list = combinationSum(nums, 8);
		System.out.println(list);
		
	}

}
