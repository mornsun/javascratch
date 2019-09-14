/**
 * 
 */
package topcoder;

/**
 * 	Given two arrays of integers with equal lengths, return the maximum value of:
 * |arr1[i] - arr1[j]| + |arr2[i] - arr2[j]| + |i - j|
 * where the maximum is taken over all 0 <= i, j < arr1.length.
 *
 * Example 1:
 *
 * Input: arr1 = [1,2,3,4], arr2 = [-1,4,5,6]
 * Output: 13
 * Example 2:
 *
 * Input: arr1 = [1,-2,-5,0,10], arr2 = [0,-2,-1,-7,-4]
 * Output: 20
 *
 * Constraints:
 *
 * 2 <= arr1.length == arr2.length <= 40000
 * -10^6 <= arr1[i], arr2[i] <= 10^6
 *
 * @author Chauncey
 * Runtime: 4 ms, faster than 81.79% of Java online submissions for Maximum of Absolute Value Expression.
 * Memory Usage: 45.1 MB, less than 100.00% of Java online submissions for Maximum of Absolute Value Expression.
 */
public class xLC_1131_Maximum_of_Absolute_Value_Expression
{
	public int maxAbsValExpr(int[] arr1, int[] arr2) {
		if (arr1==null || arr2==null || arr1.length==0 || arr1.length!=arr2.length)
			return 0;

		int res = 0, n = arr1.length;
		int[] q = {-1, 1};
		for (int a : q) {
			for (int b : q) {
				int min = a*arr1[0] + b*arr2[0];
				for (int i=1; i<n; ++i) {
					int v = a*arr1[i] + b*arr2[i] + i;
					res = Math.max(res, v-min);
					min = Math.min(min, v);
				}
			}
		}
		return res;
	}

    /**
	 * @param args
	 */
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();

		xLC_1131_Maximum_of_Absolute_Value_Expression solution = new xLC_1131_Maximum_of_Absolute_Value_Expression();
        System.out.println(solution.maxAbsValExpr(new int[]{1,2,3,4}, new int[]{-1,4,5,6})); //13
		System.out.println(solution.maxAbsValExpr(new int[]{1,-2,-5,0,10}, new int[]{0,-2,-1,-7,-4})); //20
		System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
	}

}
