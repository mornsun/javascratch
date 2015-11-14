package topcoder;

import java.util.*;

/**
 * Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.

Example:
Given nums = [-2, 0, 3, -5, 2, -1]

sumRange(0, 2) -> 1
sumRange(2, 5) -> -1
sumRange(0, 5) -> -3
Note:
You may assume that the array does not change.
There are many calls to sumRange function.
Subscribe to see which companies asked this question

Hide Tags Dynamic Programming
Hide Similar Problems (M) Range Sum Query 2D - Immutable

 * @author Chauncey
 *
 */
public class RangeSumQueryImmutable
{
	private int[] _leftsum = null;
	
    public RangeSumQueryImmutable(int[] nums) {
        if (null == nums || nums.length == 0) return;
        _leftsum = new int[nums.length+1];
        int sum = 0;
        for (int i=0; i<nums.length; ++i) {
        	sum += nums[i];
        	_leftsum[i+1] = sum;
        }
    }

    public int sumRange(int i, int j) {
        if (i > j || _leftsum == null) return 0;
        if (i < 0) i=0;
        if (j > _leftsum.length-2) j = _leftsum.length-2;
        return _leftsum[j+1] - _leftsum[i];
    }
	    
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		RangeSumQueryImmutable solution = new RangeSumQueryImmutable(new int[]{-2, 0, 3, -5, 2, -1});
		//1
		System.out.println(solution.sumRange(0, 2));
		//-1
		System.out.println(solution.sumRange(2, 5));
		//-3
		System.out.println(solution.sumRange(0, 5));
	}

}
