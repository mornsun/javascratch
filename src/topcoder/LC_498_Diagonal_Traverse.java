/**
 * 
 */
package topcoder;

import java.util.*;

/**
 * Given an integer array, your task is to find all the different possible increasing subsequences of the given array, and the length of an increasing subsequence should be at least 2 .

Example:
Input: [4, 6, 7, 7]
Output: [[4, 6], [4, 7], [4, 6, 7], [4, 6, 7, 7], [6, 7], [6, 7, 7], [7,7], [4,7,7]]
Note:
The length of the given array will not exceed 15.
The range of integer in the given array is [-100,100].
The given array may contain duplicates, and two equal integers should also be considered as a special case of increasing sequence.

 * @author Chauncey
 *
 */
public class LC_498_Diagonal_Traverse
{
    public List<List<Integer>> findSubsequences(int[] nums) {
    	Set<List<Integer>> res = new HashSet<>();
        if (nums == null || nums.length == 0) {
        	return new ArrayList<>(res);
        }
        
        findSubsequencesHelper(res, new LinkedList<>(), nums, 0);
        return new ArrayList<>(res);
    }
    
    private void findSubsequencesHelper(Set<List<Integer>> res, LinkedList<Integer> curr, int[] nums, int index) {
    	if (index == nums.length) {
    		return;
    	}
    	for (int i=index; i<nums.length; ++i) {
        	if (curr.isEmpty() || nums[i] >= curr.getLast()) {
        		curr.addLast(nums[i]);
        		if (curr.size()>1) {
        			res.add(new LinkedList<>(curr));
        		}
        		findSubsequencesHelper(res, curr, nums, i+1);
        		curr.removeLast();
        	}
    	}
		++index;
    }
	    
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();

		LC_498_Diagonal_Traverse solution = new LC_498_Diagonal_Traverse();
		System.out.println(solution.findSubsequences(new int[] {4, 6, 7, 7}));
		
		
		System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
	}

}
