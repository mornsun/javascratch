package topcoder;

import java.util.*;

/**
 * Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.

For example, given nums = [0, 1, 0, 3, 12], after calling your function, nums should be [1, 3, 12, 0, 0].

Note:
You must do this in-place without making a copy of the array.
Minimize the total number of operations.
Credits:
Special thanks to @jianchao.li.fighter for adding this problem and creating all test cases.

Hide Tags Array Two Pointers
Hide Similar Problems (E) Remove Element

 * @author Chauncey
 *
 */
public class MoveZeroes
{
    public void moveZeroes(int[] nums) {
        if (null == nums || nums.length == 0) return;
        int curr = 0;
    	while (curr < nums.length && nums[curr] != 0)
    		++curr;
    	int prev = curr;
    	++curr;
        while (curr < nums.length) {
        	if (nums[curr] == 0) {
        		++curr;
        	} else {
        		if (prev != -1) {
        			int tmp = nums[curr];
        			nums[curr] = nums[prev];
        			nums[prev] = tmp;
        		}
        		++prev;
        		++curr;
        	}
        }
    }
	    
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		MoveZeroes solution = new MoveZeroes();
		int[] nums = new int[]{0,1,0,3,12};
		nums = new int[]{1,3,12,0,0};
		solution.moveZeroes(nums);
		for (int n : nums) {
			System.out.println(n);
		}
	}

}
