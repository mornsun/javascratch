package topcoder;

import java.util.*;

/**
 * You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security system connected and it will automatically contact the police if two adjacent houses were broken into on the same night.

Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.

Credits:
Special thanks to @ifanchu for adding this problem and creating all test cases. Also thanks to @ts for adding additional test cases.

Hide Tags Dynamic Programming
Hide Similar Problems (M) House Robber II (M) Paint House

 *
 */
public class HouseRobber
{
    public int rob(int[] nums) {
    	if (null == nums || nums.length == 0) return 0;
        int[] f = new int[3];
        f[0] = nums[0];
        if (nums.length==1) return f[0];
        f[1] = nums[1];
        if (nums.length==2) return Math.max(f[0], f[1]);
        f[2] = f[0] + nums[2];
        if (nums.length==3) return Math.max(f[1], f[2]);
        for (int i=3; i<nums.length; ++i) {
        	int cur = Math.max(f[0], f[1]) + nums[i];
        	f[0] = f[1];
        	f[1] = f[2];
        	f[2] = cur;
        }
        return Math.max(f[1], f[2]);
    }
    
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		HouseRobber solution = new HouseRobber();
		int[] nums = new int[]{1,0,2,0,3};
		System.out.println(solution.rob(nums));
	}

}
