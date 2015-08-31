package topcoder;

import java.util.*;

/**
 * Given a collection of integers that might contain duplicates, nums, return all possible subsets.

Note:
Elements in a subset must be in non-descending order.
The solution set must not contain duplicate subsets.
For example,
If nums = [1,2,2], a solution is:

[
  [2],
  [1],
  [1,2,2],
  [2,2],
  [1,2],
  []
]
Hide Tags Array Backtracking

*This solution explore a non-recursive (iterative) backtracking approach*

 * @author Chauncey
 *
 */
public class SubsetsII
{
    public List<List<Integer>> subsetsWithDup(int[] nums) {
    	if (nums == null) return new ArrayList<List<Integer>>();
    	List<List<Integer>> res = new ArrayList<List<Integer>>();
    	res.add(new ArrayList<Integer>()); //[] set
    	Arrays.sort(nums);
    	LinkedList<Integer> backtracking = new LinkedList<Integer>();
    	LinkedList<Integer> path = new LinkedList<Integer>();
    	int total = nums.length;
    	while (total != 0) {
        	Integer nlast = null;
    		int i = 0;
    		while (true) {
    			if (path.size()==total) {
    				res.add(new ArrayList<Integer>(path));
    				i = backtracking.pop();
    				nlast = path.removeLast();
    			} else if (i==nums.length) {
    				if (backtracking.isEmpty()) {
    					break;
    				}
        			i = backtracking.pop();
        			nlast = path.removeLast();
    			} else {
    				if (nlast==null || nlast!=nums[i]) {
    					path.add(nums[i]);
    					backtracking.push(i);
    				}
    			}
    			++i;
    		}
    		--total;
    	}
    	return res;
    }
	    
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		SubsetsII solution = new SubsetsII();
		int[] nums = new int[]{1,2,2,3,3};
		System.out.println(solution.subsetsWithDup(nums));
	}

}
