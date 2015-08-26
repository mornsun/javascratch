package topcoder;

import java.util.*;

/**
 * Note: This is an extension of House Robber.

After robbing those houses on that street, the thief has found himself a new place for his thievery so that he will not get too much attention. This time, all houses at this place are arranged in a circle. That means the first house is the neighbor of the last one. Meanwhile, the security system for these houses remain the same as for those in the previous street.

Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.

Credits:
Special thanks to @Freezen for adding this problem and creating all test cases.

Hide Tags Dynamic Programming
Hide Similar Problems (E) House Robber (M) Paint House

 *
 */
public class HouseRobberII
{
    public int rob(int[] nums) {
    	if (null == nums || nums.length == 0) return 0;
        int[][] f = new int[2][3]; //0 represent robbing 1st house but 1 not
        f[0][0] = nums[0];
        f[1][0] = 0;
        if (nums.length==1) return nums[0];
        f[0][1] = 0;
        f[1][1] = nums[1];
        if (nums.length==2) return Math.max(nums[0], nums[1]);
        f[0][2] = f[0][0] + nums[2];
        f[1][2] = nums[2];
        if (nums.length==3) return Math.max(f[0][0], Math.max(f[1][1], f[1][2]));
        for (int i=3; i<nums.length; ++i) {
        	int cur = Math.max(f[0][0], f[0][1]) + nums[i];
        	f[0][0] = f[0][1];
        	f[0][1] = f[0][2];
        	f[0][2] = cur;
        	cur = Math.max(f[1][0], f[1][1]) + nums[i];
        	f[1][0] = f[1][1];
        	f[1][1] = f[1][2];
        	f[1][2] = cur;
        }
        return Math.max(Math.max(f[0][0], f[0][1]), Math.max(f[1][1], f[1][2]));
    }
    
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		HouseRobberII solution = new HouseRobberII();
		int[] nums = new int[]{1,0,2,0,3};
		System.out.println(solution.rob(nums));
	}

}
