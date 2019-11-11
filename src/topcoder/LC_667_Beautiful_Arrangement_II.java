/**
 * 
 */
package topcoder;

/**
 * 	Given two integers n and k, you need to construct a list which contains n different positive integers ranging from 1 to n and obeys the following requirement:
 * Suppose this list is [a1, a2, a3, ... , an], then the list [|a1 - a2|, |a2 - a3|, |a3 - a4|, ... , |an-1 - an|] has exactly k distinct integers.
 *
 * If there are multiple answers, print any of them.
 *
 * Example 1:
 * Input: n = 3, k = 1
 * Output: [1, 2, 3]
 * Explanation: The [1, 2, 3] has three different positive integers ranging from 1 to 3, and the [1, 1] has exactly 1 distinct integer: 1.
 * Example 2:
 * Input: n = 3, k = 2
 * Output: [1, 3, 2]
 * Explanation: The [1, 3, 2] has three different positive integers ranging from 1 to 3, and the [2, 1] has exactly 2 distinct integers: 1 and 2.
 * Note:
 * The n and k are in the range 1 <= k < n <= 104.
 *
 * Array
 *
 * @author Chauncey
 * Runtime: 1 ms, faster than 88.76% of Java online submissions for Beautiful Arrangement II.
 * Memory Usage: 35.9 MB, less than 25.00% of Java online submissions for Beautiful Arrangement II.
 */
public class LC_667_Beautiful_Arrangement_II
{
    public int[] constructArray(int n, int k) {

        if (n==0 || k<1 || k>=n) return null;
        int[] res = new int[n];
        int i;
        for (i=0; i<k; ++i) {
            res[i] = i%2==0 ? 1+(i/2) : n-(i/2);
        }
        boolean dec = i%2==0;
        for (; i<n; ++i) {
            res[i] = dec ? res[i-1]-1 : res[i-1]+1;
        }

        return res;
    }

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();

		LC_667_Beautiful_Arrangement_II solution = new LC_667_Beautiful_Arrangement_II();
        System.out.println(solution.constructArray(3, 1)); //
        System.out.println(solution.constructArray(3, 2)); //
		System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
	}

}
