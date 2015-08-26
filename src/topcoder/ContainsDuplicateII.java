package topcoder;

import java.util.*;

/**
 * Given an array of integers and an integer k, find out whether there are two distinct indices i and j in the array such that nums[i] = nums[j] and the difference between i and j is at most k.

Hide Tags Array Hash Table
Hide Similar Problems (E) Contains Duplicate (M) Contains Duplicate III

 * @author Chauncey
 *
 */
public class ContainsDuplicateII
{
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        if (null == nums || nums.length==0 || k==0) return false;
        HashSet<Integer> set = new HashSet<Integer>();
        for (int i=0; i<k && i<nums.length; ++i) {
        	if (set.contains(nums[i])) return true;
        	else set.add(nums[i]);
        }
        for (int i=k, j=0; i<nums.length; ++i, ++j) {
        	if (set.contains(nums[i])) return true;
        	else set.add(nums[i]);
        	set.remove(nums[j]);
        }
        return false;
    }
    
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		ContainsDuplicateII solution = new ContainsDuplicateII();
		int[] nums = new int[]{-2,0,2,0,3};
		System.out.println(solution.containsNearbyDuplicate(nums, 2));
	}

}
