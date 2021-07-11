package topcoder;

import java.util.*;

/**
 * Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.

Note:
Elements in a triplet (a,b,c) must be in non-descending order. (ie, a ≤ b ≤ c)
The solution set must not contain duplicate triplets.
    For example, given array S = {-1 0 1 2 -1 -4},

    A solution set is:
    (-1, 0, 1)
    (-1, -1, 2)
Hide Tags Array Two Pointers
Hide Similar Problems (M) Two Sum (M) 3Sum Closest (M) 4Sum (M) 3Sum Smaller
 * @author Chauncey
 *
 */
public class A3Sum
{
    public List<List<Integer>> threeSum(int[] nums) {
    	List<List<Integer>> result = new LinkedList<>();
    	if (nums.length < 3)
    		return result;
    	Arrays.sort(nums);
    	int target = 0;
    	for (int i=0; i < nums.length-2; ++i) {
    		if (i>0 && nums[i]==nums[i-1]) {
    			continue;
    		}
	    	int j = i+1;
	    	int k = nums.length-1;
	    	while (j < k) {
	    		if (j != i+1 && nums[j]==nums[j-1]) {
	    			++j;
	    			continue;
	    		}
	    		if (k != nums.length-1 && nums[k]==nums[k+1]) {
	    			--k;
	    			continue;
	    		}
		    	if (nums[i] + nums[j] + nums[k] < target) {
		    		++j;
		    	} else if (nums[i] + nums[j] + nums[k] > target) {
		    		--k;
		    	} else {
		    		result.add(Arrays.asList(nums[i], nums[j++], nums[k--]));
		    	}
	    	}
    	}
    	return result;
    }
	    
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		A3Sum solution = new A3Sum();
		int[] nums = new int[]{-2,0,0,2,2};
		List<List<Integer>> list = solution.threeSum(nums);
		for (List<Integer> l : list) {
			System.out.println(l);
		}
	}

}
