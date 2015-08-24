package topcoder;

import java.util.*;

/**
 * Given an array of numbers nums, in which exactly two elements appear only once and all the other elements appear exactly twice. Find the two elements that appear only once.

For example:

Given nums = [1, 2, 1, 3, 2, 5], return [3, 5].

Note:
The order of the result is not important. So in the above example, [5, 3] is also correct.
Your algorithm should run in linear runtime complexity. Could you implement it using only constant space complexity?
Credits:
Special thanks to @jianchao.li.fighter for adding this problem and creating all test cases.

Hide Tags Bit Manipulation
Hide Similar Problems (M) Single Number (M) Single Number II

 * @author Chauncey
 *
 */
public class SingleNumberIII
{
    public int[] singleNumber(int[] nums) {
        if (nums == null || nums.length == 0) return null;
        int val = nums[0];
        for (int i=1; i<nums.length; ++i) {
        	val ^= nums[i];
        }
        if (val == 0) return null; // two identical numbers without conforming the question
        int btest = 1;
        while ((val & btest) == 0) {
        	btest <<= 1;
        }
        int v1 = 0, v2 = 0;
        for (int num : nums) {
        	if ((num&btest) == 0) {
        		v1 ^= num;
        	} else {
        		v2 ^= num;
        	}
        }
        return v1 < v2 ? new int[]{v1, v2} : new int[]{v2, v1};
    }
	    
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		SingleNumberIII solution = new SingleNumberIII();
		
		for (int num : solution.singleNumber(new int[]{1, 2, 1, 3, 2, 5})) {
			System.out.println(num);
		}
	}

}
