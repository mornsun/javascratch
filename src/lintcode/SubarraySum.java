package lintcode;

import java.util.*;

/**
 * Given an integer array, find a subarray where the sum of numbers is zero. Your code should return the index of the first number and the index of the last number.

Have you met this question in a real interview? Yes
Example
Given [-3, 1, 2, -3, 4], return [0, 2] or [1, 3].

Note
There is at least one subarray that it's sum equals to zero.

Tags Expand 
Subarray Hash Table


Related Problems Expand 
Medium Submatrix Sum 18 %
Hard Subarray Sum II 28 %
Medium Minimum Size Subarray Sum 23 %
Medium Subarray Sum Closest 16 %

 * @author Chauncey
 *
 */
public class SubarraySum
{
    /**
     * @param nums: A list of integers
     * @return: A list of integers includes the index of the first number 
     *          and the index of the last number
     */
    public ArrayList<Integer> subarraySum(int[] nums) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        if (null == nums || nums.length==0) return res;
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        map.put(0, -1);
        int sum = 0;
        for (int i=0; i<nums.length; ++i) {
            sum += nums[i];
            Integer idx = map.get(sum);
            if (idx != null) {
                res.add(idx+1);
                res.add(i);
                return res;
            }
            map.put(sum, i);
        }
        return res;
    }
    
    /**
     * @param args
     */
    public static void main(String[] args)
    {
        SubarraySum solution = new SubarraySum();

        //[0,2] or [1,3]
        System.out.println(solution.subarraySum(new int[]{-3, 1, 2, -3, 4}));
    }

}
