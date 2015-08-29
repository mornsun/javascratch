package topcoder;

import java.util.*;

/**
 * Given an array with n objects colored red, white or blue, sort them so that objects of the same color are adjacent, with the colors in the order red, white and blue.

Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.

Note:
You are not suppose to use the library's sort function for this problem.

click to show follow up.

Follow up:
A rather straight forward solution is a two-pass algorithm using counting sort.
First, iterate the array counting number of 0's, 1's, and 2's, then overwrite array with total number of 0's, then 1's and followed by 2's.

Could you come up with an one-pass algorithm using only constant space?
Hide Tags Array Two Pointers Sort
Hide Similar Problems (M) Sort List

 * @author Chauncey
 *
 */
public class SortColors
{
    public void sortColors(int[] nums) {
    	if (nums == null || nums.length==0) return;
    	int i0=0, i2=nums.length-1;
    	for (int i=0; i<=i2;) {
    		if (nums[i] == 0) {
    			nums[i++] = nums[i0];
    			nums[i0++] = 0;
    		} else if (nums[i] == 2) {
    			nums[i] = nums[i2];
    			nums[i2--] = 2;
    		} else {
    			++i;
    		}
    	}
    }

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		SortColors solution = new SortColors();
		int[] nums = new int[]{1,0,2,1,2,2};

		//matrix = new int[][]{{1}};
		solution.sortColors(nums);
		for (int i=0; i<nums.length; ++i) {
			System.out.println(nums[i]);
		}
	}

}
