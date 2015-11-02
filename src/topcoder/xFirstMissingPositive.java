package topcoder;

import java.util.Arrays;

/**
 * Given an unsorted integer array, find the first missing positive integer.

For example,
Given [1,2,0] return 3,
and [3,4,-1,1] return 2.

Your algorithm should run in O(n) time and uses constant space.

Hide Tags Array

bucket sort

 * @author Chauncey
 *
 */
public class xFirstMissingPositive {

	private final void bucket_sort(int[] nums) {
		for (int i=0; i<nums.length; ++i) {
			while (nums[i] != i + 1) {
				if (nums[i] <= 0 || nums[i] > nums.length || nums[i] == nums[nums[i]-1] )
					break;
				int idx = nums[i] - 1; // eg: set 1 to [0], 2 to [1]
				nums[i] = nums[idx];
				nums[idx] = idx + 1;
			}
		}
	}
    public int firstMissingPositive(int[] nums) {
    	bucket_sort(nums);
        for (int i=0; i<nums.length; ++i) {
        	if (nums[i] != i+1) {
        		return i+1;
        	}
        }
        return nums.length + 1;
    }
    
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		xFirstMissingPositive solution = new xFirstMissingPositive();
		int[] nums = new int[]{3,4,-1,1};
		System.out.println(solution.firstMissingPositive(nums));
    	for (int num : nums) {
    		System.out.print(num+", ");
    	}
    	System.out.println();
	}

}
