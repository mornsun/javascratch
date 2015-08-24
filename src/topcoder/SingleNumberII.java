package topcoder;

import java.util.*;

/**
 * Given an array of integers, every element appears three times except for one. Find that single one.

Note:
Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?

Hide Tags Bit Manipulation
Hide Similar Problems (M) Single Number (M) Single Number III

 * @author Chauncey
 *
 */
public class SingleNumberII
{
    public int singleNumber(int[] nums) {
        if (nums == null || nums.length == 0) return -1;
        int one = 0;
        int two = 0;
        for (int num : nums) {
        	two |= (one & num);
        	one ^= num;
        	System.out.println(one+":"+two);
        	int three = ~(one & two);
        	one &= three;
        	two &= three;
        }
        return two;
    }
	    
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		SingleNumberII solution = new SingleNumberII();
		
		System.out.println(solution.singleNumber(new int[]{1, 2, 1, 2, 2, 1, 25, 25}));
	}

}
