/**
 * 
 */
package topcoder;

import java.util.*;

/**
 * Given a collection of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.

Each number in C may only be used once in the combination.

Note:
All numbers (including target) will be positive integers.
Elements in a combination (a1, a2, … , ak) must be in non-descending order. (ie, a1 ≤ a2 ≤ … ≤ ak).
The solution set must not contain duplicate combinations.
For example, given candidate set 10,1,2,7,6,1,5 and target 8, 
A solution set is: 
[1, 7] 
[1, 2, 5] 
[2, 6] 
[1, 1, 6] 
Hide Tags Array Backtracking
Hide Similar Problems (M) Combination Sum

 * @author Chauncey
 *
 */
public class CombinationSumII
{
	private final static void dfs(int[] candidates, int gap, int start, LinkedList<Integer> one_res, List<List<Integer>> res)
	{
		for (int i = start; i<candidates.length; ++i) {
			if (i>start && candidates[i]==candidates[i-1])
				continue;
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
				dfs(candidates, ngap, i+1, one_res, res);
				one_res.pollLast();
			}
		}
	}
    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
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
		List<List<Integer>> list = combinationSum2(nums, 8);
		System.out.println(list);
		
	}

}
