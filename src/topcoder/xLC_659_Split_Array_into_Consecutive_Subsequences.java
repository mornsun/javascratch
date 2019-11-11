/**
 * 
 */
package topcoder;

import java.util.LinkedList;

/**
 * 	Given an array nums sorted in ascending order, return true if and only if you can split it into 1 or more subsequences such that each subsequence consists of consecutive integers and has length at least 3.
 *
 *
 *
 * Example 1:
 *
 * Input: [1,2,3,3,4,5]
 * Output: True
 * Explanation:
 * You can split them into two consecutive subsequences :
 * 1, 2, 3
 * 3, 4, 5
 *
 * Example 2:
 *
 * Input: [1,2,3,3,4,4,5,5]
 * Output: True
 * Explanation:
 * You can split them into two consecutive subsequences :
 * 1, 2, 3, 4, 5
 * 3, 4, 5
 *
 * Example 3:
 *
 * Input: [1,2,3,4,4,5]
 * Output: False
 *
 * Constraints:
 *
 * 1 <= nums.length <= 10000
 *
 * @author Chauncey
 * Runtime: 6 ms, faster than 18.15% of Java online submissions for Maximum Binary Tree.
 * Memory Usage: 39.7 MB, less than 58.70% of Java online submissions for Maximum Binary Tree.
 *
 * Runtime: 6 ms, faster than 18.15% of Java online submissions for Maximum Binary Tree.
 * Memory Usage: 38.9 MB, less than 84.78% of Java online submissions for Maximum Binary Tree.
 */
public class xLC_659_Split_Array_into_Consecutive_Subsequences
{
    public boolean isPossible(int[] nums) {
        int pre = Integer.MIN_VALUE;
        int p1 = 0;
        int p2 = 0;
        int p3 = 0;

        int cur = 0;
        int cnt = 0;
        int c1 = 0;
        int c2 = 0;
        int c3 = 0;

        for (int i = 0; i < nums.length; pre = cur, p1 = c1, p2 = c2, p3 = c3) {
            for (cur = nums[i], cnt = 0; i < nums.length && cur == nums[i]; i++) {
                cnt++;
            }

            //System.out.println(cur + ":" + pre + ":" + p1 + ":" + p2 + ":" + p3);
            if (cur != pre + 1) {
                if (p1 != 0 || p2 != 0) {
                    return false;
                }

                c1 = cnt;
                c2 = 0;
                c3 = 0;

            } else {
                if (cnt < p1 + p2) {
                    return false;
                }

                c1 = Math.max(0, cnt - (p1 + p2 + p3));
                c2 = p1;
                c3 = p2 + Math.min(p3, cnt - (p1 + p2));
            }
        }

        return (p1 == 0 && p2 == 0);
    }

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();

		xLC_659_Split_Array_into_Consecutive_Subsequences solution = new xLC_659_Split_Array_into_Consecutive_Subsequences();
        System.out.println(solution.isPossible(new int[]{1,2,3,4,5})); // true
		System.out.println(solution.isPossible(new int[]{1,2,3,3,4,5})); // true
        System.out.println(solution.isPossible(new int[]{1,2,3,3,4,4,5,5})); // true
        System.out.println(solution.isPossible(new int[]{1,2,3,4,4,5})); // false
		System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
	}

}
