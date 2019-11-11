/**
 * 
 */
package topcoder;

/**
 * 	There is a room with n lights which are turned on initially and 4 buttons on the wall. After performing exactly m unknown operations towards buttons, you need to return how many different kinds of status of the n lights could be.
 *
 * Suppose n lights are labeled as number [1, 2, 3 ..., n], function of these 4 buttons are given below:
 *
 * Flip all the lights.
 * Flip lights with even numbers.
 * Flip lights with odd numbers.
 * Flip lights with (3k + 1) numbers, k = 0, 1, 2, ...
 *
 *
 * Example 1:
 *
 * Input: n = 1, m = 1.
 * Output: 2
 * Explanation: Status can be: [on], [off]
 *
 *
 * Example 2:
 *
 * Input: n = 2, m = 1.
 * Output: 3
 * Explanation: Status can be: [on, off], [off, on], [off, off]
 *
 *
 * Example 3:
 *
 * Input: n = 3, m = 1.
 * Output: 4
 * Explanation: Status can be: [off, on, off], [on, off, on], [off, off, off], [off, on, on].
 *
 *
 * Note: n and m both fit in range [0, 1000].
 *
 * Math
 *
 * @author Chauncey
 * Runtime: 0 ms, faster than 100.00% of Java online submissions for Bulb Switcher II.
 * Memory Usage: 33.1 MB, less than 33.33% of Java online submissions for Bulb Switcher II.
 */
public class LC_672_Bulb_Switcher_II
{
    public int flipLights(int n, int m) {

        if (n==0 || m==0) return 1;
        if (n==1) return 2;
        if (n==2) {
            if (m==1) return 3;
            else return 4;
        }
        if (n>=3) {
            if (m==1) return 4;
            else if (m==2) return 7;
            else return 8;
        }
        return 0;
    }

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();

		LC_672_Bulb_Switcher_II solution = new LC_672_Bulb_Switcher_II();
        System.out.println(solution.flipLights(1, 1)); //
        System.out.println(solution.flipLights(2, 1)); //
        System.out.println(solution.flipLights(3, 1)); //
		System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
	}

}
