package topcoder;

import java.util.*;

/**
 * Given an array containing n distinct numbers taken from 0, 1, 2, ..., n, find the one that is missing from the array.

For example,
Given nums = [0, 1, 3] return 2.

Note:
Your algorithm should run in linear runtime complexity. Could you implement it using only constant extra space complexity?

Credits:
Special thanks to @jianchao.li.fighter for adding this problem and creating all test cases.

Hide Tags Array Math Bit Manipulation
Hide Similar Problems (H) First Missing Positive (M) Single Number

 * @author Chauncey
 *
 */
public class MissingNumber
{
    public int missingNumber(int[] nums) {
    	if (null==nums || nums.length==0) return 0;
    	int sum = (nums.length * (1+nums.length)) >> 1;
        for (int num : nums) {
        	sum -= num;
        }
        return sum;
    }
	    
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		MissingNumber solution = new MissingNumber();
		
		System.out.println(solution.missingNumber(new int[]{1,2,3}));
	}

}
