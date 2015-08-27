package topcoder;

import java.util.*;

/**
 * Follow up for "Remove Duplicates":
What if duplicates are allowed at most twice?

For example,
Given sorted array nums = [1,1,1,2,2,3],

Your function should return length = 5, with the first five elements of nums being 1, 1, 2, 2 and 3. It doesn't matter what you leave beyond the new length.

Hide Tags Array Two Pointers

 *
 */
public class RemoveDuplicatesfromSortedArrayII
{
    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int k = 1;
        int nhit = 1;
        for (int i=1; i<nums.length; ++i) {
        	if (nums[i] == nums[i-1]) {
        		if (nhit != 2) {
        			nums[k++] = nums[i];
        			++nhit;
        		}
        	} else {
        		nums[k++] = nums[i];
        		nhit = 1;
        	}
        }
        return k;
    }
	
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		RemoveDuplicatesfromSortedArrayII solution = new RemoveDuplicatesfromSortedArrayII();
		System.out.println(solution.removeDuplicates(new int[]{1,1,1,2}));
	}

}
