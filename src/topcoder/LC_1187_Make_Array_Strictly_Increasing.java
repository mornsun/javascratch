/**
 * 
 */
package topcoder;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 	Given two integer arrays arr1 and arr2, return the minimum number of operations (possibly zero) needed to make arr1 strictly increasing.
 * In one operation, you can choose two indices 0 <= i < arr1.length and 0 <= j < arr2.length and do the assignment arr1[i] = arr2[j].
 * If there is no way to make arr1 strictly increasing, return -1.
 *
 * Example 1:
 *
 * Input: arr1 = [1,5,3,6,7], arr2 = [1,3,2,4]
 * Output: 1
 * Explanation: Replace 5 with 2, then arr1 = [1, 2, 3, 6, 7].
 *
 * Example 2:
 *
 * Input: arr1 = [1,5,3,6,7], arr2 = [4,3,1]
 * Output: 2
 * Explanation: Replace 5 with 3 and then replace 3 with 4. arr1 = [1, 3, 4, 6, 7].
 *
 * Example 3:
 *
 * Input: arr1 = [1,5,3,6,7], arr2 = [1,6,3,3]
 * Output: -1
 * Explanation: You can't make arr1 strictly increasing.
 *
 * Constraints:
 *
 * 1 <= arr1.length, arr2.length <= 2000
 * 0 <= arr1[i], arr2[i] <= 10^9
 *
 * @author Chauncey
 * Runtime: 161 ms, faster than 33.33% of Java online submissions for Make Array Strictly Increasing.
 * Memory Usage: 39.7 MB, less than 100.00% of Java online submissions for Make Array Strictly Increasing.
 */
public class LC_1187_Make_Array_Strictly_Increasing
{
	public int makeArrayIncreasing(int[] arr1, int[] arr2) {

		if (arr1 == null || arr2 == null || arr1.length == 0 || arr2.length == 0)
			return -1;
		Arrays.sort(arr2);

		HashMap<Integer, Integer> dp = new HashMap<>();
		dp.put(-1, 0);
		for (int n : arr1) {
			HashMap<Integer, Integer> prev = dp;
			dp = new HashMap<>();
			for (Map.Entry<Integer, Integer> entry : prev.entrySet()) {
				int key = entry.getKey();
				int value = entry.getValue();
				if (n > key) {
					Integer v = dp.get(n);
					if (v == null)
						dp.put(n, value);
					else
						dp.put(n, Math.min(v, value));
				}

				int lo = 0;
				int hi = arr2.length - 1;
				while (lo < hi) {
					int m = lo + (hi - lo >> 1);
					if (key >= arr2[m])
						lo = m + 1;
					else
						hi = m;
				}
				int pos = key >= arr2[arr2.length-1] ? lo + 1 : lo;
				if (pos < arr2.length) {
					Integer v = dp.get(arr2[pos]);
					if (v == null)
						dp.put(arr2[pos], value + 1);
					else
						dp.put(arr2[pos], Math.min(v, value + 1));
				}
			}
		}
		int res = Integer.MAX_VALUE;
		for (Map.Entry<Integer, Integer> entry : dp.entrySet()) {
			res = Math.min(res, entry.getValue());
		}
		return res == Integer.MAX_VALUE ? -1 : res;
	}

    /**
	 * @param args
	 */
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();

		LC_1187_Make_Array_Strictly_Increasing solution = new LC_1187_Make_Array_Strictly_Increasing();
        System.out.println(solution.makeArrayIncreasing(new int[]{1,5,3,6,7}, new int[]{1,3,2,4})); //1
        System.out.println(solution.makeArrayIncreasing(new int[]{1,5,3,6,7}, new int[]{4,3,1})); //2
        System.out.println(solution.makeArrayIncreasing(new int[]{1,5,3,6,7}, new int[]{1,6,3,3})); //-1
		System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
	}

}
