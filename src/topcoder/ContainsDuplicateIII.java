package topcoder;

import java.util.*;

/**
 * Given an array of integers, find out whether there are two distinct indices i and j in the array such that the difference between nums[i] and nums[j] is at most t and the difference between i and j is at most k.

Hide Tags Binary Search Tree
Hide Similar Problems (E) Contains Duplicate (E) Contains Duplicate II

 * @author Chauncey
 *
 */
public class ContainsDuplicateIII
{
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (null == nums || nums.length==0 || k==0) return false;
        TreeSet<Integer> set = new TreeSet<Integer>();
        for (int i=0; i<k && i<nums.length; ++i) {
        	Integer ceiling = set.ceiling(nums[i]);
        	if (ceiling != null && (long)ceiling - nums[i] <= t) return true;
        	Integer floor = set.floor(nums[i]);
        	if (floor != null && nums[i] - (long)floor <= t) return true;
        	set.add(nums[i]);
        }
        for (int i=k, j=0; i<nums.length; ++i, ++j) {
        	Integer ceiling = set.ceiling(nums[i]);
        	if (ceiling != null && (long)ceiling - nums[i] <= t) return true;
        	Integer floor = set.floor(nums[i]);
        	if (floor != null && nums[i] - (long)floor <= t) return true;
        	set.add(nums[i]);
        	set.remove(nums[j]);
        }
        return false;
    }
    
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		ContainsDuplicateIII solution = new ContainsDuplicateIII();
		int[] nums = new int[]{-1,2147483647};
		System.out.println(solution.containsNearbyAlmostDuplicate(nums, 1, 2147483647));
	}

}
