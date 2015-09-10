package lintcode;

import java.util.*;

/**
 * Given an array of integers and a number k, find k non-overlapping subarrays which have the largest sum.

The number in each subarray should be contiguous.

Return the largest sum.

Have you met this question in a real interview? Yes
Example
Given [-1,4,-2,3,-2,3], k=2, return 8

Note
The subarray should contain at least one number

Tags Expand 
LintCode Copyright Dynamic Programming Subarray Array


Related Problems Expand 
Medium Best Time to Buy and Sell Stock III 26 %
Medium Best Time to Buy and Sell Stock II 51 %
Medium Best Time to Buy and Sell Stock 42 %
Medium Maximum Subarray Difference 24 %
Hard Maximum Subarray III 22 %
Medium Maximum Subarray II 24 %
Easy Maximum Subarray 37 %

 * @author Chauncey
 *
 */
public class MaximumSubarrayIII
{
    /**
     * @param nums: A list of integers
     * @param k: An integer denote to find k non-overlapping subarrays
     * @return: An integer denote the sum of max k non-overlapping subarrays
     */
    public int maxSubArray(ArrayList<Integer> nums, int k) {
    	if (null == nums || nums.size()==0 || k==0) return 0;
    	int len = nums.size();
    	if (k>=len) k=len;
    	int[][] global = new int[len][k];
    	int[][] local = new int[len][k];
    	local[0][0] = nums.get(0);
    	global[0][0] = nums.get(0);
    	int sum = nums.get(0);
    	for (int i=1; i<len; ++i) {
    		local[i][0] = Math.max(local[i-1][0], 0) + nums.get(i);
    		global[i][0] = Math.max(local[i][0], global[i-1][0]);
    		if (i<k) {
    			sum += nums.get(i);
    			local[i][i] = sum;
    			global[i][i] = sum;
    		}
    		for (int t=1; t<i && t<k; ++t) {
    			local[i][t] = Math.max(global[i-1][t-1]+nums.get(i), local[i-1][t]+nums.get(i));
    			global[i][t] = Math.max(local[i][t], global[i-1][t]);
    			//System.out.print(global[i][t]+"("+i+":"+t+"),");
    		}
    	}
    	return global[len-1][k-1];
    }
    public int maxSubArray1(ArrayList<Integer> nums, int k) {
    	if (null == nums || nums.size()==0 || k==0) return 0;
    	int len = nums.size();
    	if (k>=len) k=len;
    	int[][] between = new int[len][len];
    	int[][] f = new int[k][len];
    	for (int i=0; i<len; ++i) {
    		int sum = 0;
    		int max = Integer.MIN_VALUE;
    		for (int j=i; j<len; ++j) {
    			sum += nums.get(j);
    			if (sum > max) max = sum;
    			if (sum < 0) sum = 0;
    			between[i][j] = max;
    		}
    	}
    	for (int i=0; i<len; ++i) {
    		f[0][i] = between[0][i];
    	}
		for (int t=1; t<k; ++t) {
			f[t][t] = f[t-1][t-1] + between[t][t];
			for (int i=t+1; i<len; ++i) {
				//int left = 1;
				int max = Integer.MIN_VALUE;
				for (int left=t-1; left<i; ++left) {
					int sum = f[t-1][left] + between[left+1][i];
					if (sum >= max) {
						max = sum;
					}
					//System.out.print(sum+"("+left+"),");
				}
				//System.out.println(i+"-"+t);
				//System.out.println();
				f[t][i] = max;
    		}
    	}
		return f[k-1][len-1];
    }
    
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		MaximumSubarrayIII solution = new MaximumSubarrayIII();

		//8
		System.out.println(solution.maxSubArray(new ArrayList<Integer>(Arrays.asList(new Integer[]{-1,4,-2,3,-2,3})), 2));
		//9
		System.out.println(solution.maxSubArray(new ArrayList<Integer>(Arrays.asList(new Integer[]{5,4})), 2));
		//-2
		System.out.println(solution.maxSubArray(new ArrayList<Integer>(Arrays.asList(new Integer[]{-1,-2,-3,-100,-1,-50})), 2));
		//4114
		System.out.println(solution.maxSubArray(new ArrayList<Integer>(Arrays.asList(new Integer[]{-42,81,-43,97,-82,20,-33,49,-62,2,
		-43,18,-54,52,-29,31,-70,87,-75,47,
		-22,42,-56,97,-100,54,-33,14,-89,34,
		-81,60,-66,75,-99,91,-93,70,-10,30,
		-26,72,-95,66,-41,23,-23,31,-14,78,
		-74,92,-20,25,-57,41,-72,58,-46,44,
		-52,53,-85,73,-37,96,-91,85,-77,62,
		-9,73,-64,63,-12,18,-61,24,-75,95,
		-54,89,-61,63,-19,24,-46,87,-87,69,
		-98,26,-92,26,-70,40,-63,20,-10,18,
		-64,26,-23,84,-35,65,-81,26,-55,92,
		-72,15,-99,18,-84,95,-50,77,-44,20,
		-20,94,-98,62,-67,17,-23,23,-75,33,
		-90,1,-1,86,-31,96,-80,100,-65,93,
		-51,48,-47,81,-63,100,-84,3,-15,59,
		-53,99,-67,12,-94,24,-98,74,-24,4,
		-34,79,-19,35,-54,36,-42,60,-68,18,
		-62,12,-50,44,-22,61,-21,27,-14,48,
		0,78,-39,70,-46,1,-86,77,-98,55,-93,
		81,-70,48,-3,0,-46,71,-50,11})), 47));
	}

}
