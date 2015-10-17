package topcoder;

import java.util.*;

/**
 * Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive), prove that at least one duplicate number must exist. Assume that there is only one duplicate number, find the duplicate one.

Note:
You must not modify the array (assume the array is read only).
You must use only constant, O(1) extra space.
Your runtime complexity should be less than O(n2).
There is only one duplicate number in the array, but it could be repeated more than once.
Credits:
Special thanks to @jianchao.li.fighter for adding this problem and creating all test cases.

Hide Tags Array Two Pointers Binary Search
Hide Similar Problems (H) First Missing Positive (M) Single Number (M) Linked List Cycle II (M) Missing Number

 * @author Chauncey
 *
 */
public class FindtheDuplicateNumber
{
    public int findDuplicate(int[] nums) {
        if (null == nums || nums.length == 0) return 0;
        int lo = 1, hi = nums.length-1;
        while (lo < hi) { //1,2,3,4,4,5
        	int m = lo + (hi-lo>>1);
        	int cnt = 0;
        	for (int num : nums) {
        		if (num <= m) ++cnt;
        	}
        	if (cnt > m) {
        		hi = m;
        	} else {
        		lo = m+1;
        	}
        }
        return lo;
    }
	    
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		FindtheDuplicateNumber solution = new FindtheDuplicateNumber();
		System.out.println(solution.findDuplicate(new int[]{1,2,3,4,1}));
	}

}
