package topcoder;

import java.util.*;

/**
 * Given a set of distinct integers, nums, return all possible subsets.

Note:
Elements in a subset must be in non-descending order.
The solution set must not contain duplicate subsets.
For example,
If nums = [1,2,3], a solution is:

[
  [3],
  [1],
  [2],
  [1,2,3],
  [1,3],
  [2,3],
  [1,2],
  []
]
Hide Tags Array Backtracking Bit Manipulation

 * @author Chauncey
 *
 */
public class Subsets
{
    public List<List<Integer>> subsets(int[] nums) {
    	if (nums == null) return new ArrayList<List<Integer>>();
    	int total = 1<<nums.length;
    	List<List<Integer>> res = new ArrayList<List<Integer>>(total);
    	res.add(new ArrayList<Integer>()); //[] set
    	Arrays.sort(nums);
    	while (--total != 0) {
			List<Integer> item = new ArrayList<Integer>();
    		for (int i=0; i<nums.length; ++i) {
    			int bit = 1<<i;
    			if ((total & bit) != 0) {
    				item.add(nums[i]);
    			}
    		}
			res.add(item);
    	}
    	return res;
    }
	    
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		Subsets solution = new Subsets();
		int[] nums = new int[]{1,2,3};
		System.out.println(solution.subsets(nums));
	}

}
