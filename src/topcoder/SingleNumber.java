package topcoder;

import java.util.*;

/**
 * Given an array of integers, every element appears twice except for one. Find that single one.

Note:
Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?

Hide Tags Hash Table Bit Manipulation
Hide Similar Problems (M) Single Number II (M) Single Number III (M) Missing Number

 * @author Chauncey
 *
 */
public class SingleNumber
{
    public int singleNumber(int[] nums) {
        if (nums == null || nums.length == 0) return -1;
        int val = nums[0];
        for (int i=1; i<nums.length; ++i) {
        	val ^= nums[i];
        }
        return val;
    }
	    
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		SingleNumber solution = new SingleNumber();
		
		System.out.println(solution.singleNumber(new int[]{1, 2, 1, 2, 5}));
	}

}
