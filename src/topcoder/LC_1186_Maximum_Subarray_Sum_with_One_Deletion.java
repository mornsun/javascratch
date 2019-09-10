/**
 * 
 */
package topcoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 	Given an array of integers, return the maximum sum for a non-empty subarray (contiguous elements) with at most one element deletion. In other words, you want to choose a subarray and optionally delete one element from it so that there is still at least one element left and the sum of the remaining elements is maximum possible.
 * Note that the subarray needs to be non-empty after deleting one element.
 *
 * Example 1:
 *
 * Input: arr = [1,-2,0,3]
 * Output: 4
 * Explanation: Because we can choose [1, -2, 0, 3] and drop -2, thus the subarray [1, 0, 3] becomes the maximum value.
 * Example 2:
 *
 * Input: arr = [1,-2,-2,3]
 * Output: 3
 * Explanation: We just choose [3] and it's the maximum sum.
 * Example 3:
 *
 * Input: arr = [-1,-1,-1,-1]
 * Output: -1
 * Explanation: The final subarray needs to be non-empty. You can't choose [-1] and delete -1 from it, then get an empty subarray to make the sum equals to 0.
 *
 * Constraints:
 *
 * 1 <= arr.length <= 10^5
 * -10^4 <= arr[i] <= 10^4
 *
 * @author Chauncey
 * Runtime: 3 ms, faster than 100.00% of Java online submissions for Maximum Subarray Sum with One Deletion.
 * Memory Usage: 48.9 MB, less than 100.00% of Java online submissions for Maximum Subarray Sum with One Deletion.
 */
public class LC_1186_Maximum_Subarray_Sum_with_One_Deletion
{
    public int maximumSum(int[] arr) {
        if (arr==null || arr.length==0)
            return 0;

        int sum = 0;
        int max = Integer.MIN_VALUE;
        int max1 = Integer.MIN_VALUE;
        for (int i=0; i<arr.length; ++i) {
            if (i==0)
                max1 = arr[0];
            else
                max1 = sum==0 ? max1+arr[i] : Math.max(sum, max1+arr[i]);

            sum += arr[i];

            if (max1 > max)
                max = max1;
            if (sum > max)
                max = sum;
            if (sum < 0)
                sum = 0;
        }

        return max;
    }

    /**
	 * @param args
	 */
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();

		LC_1186_Maximum_Subarray_Sum_with_One_Deletion solution = new LC_1186_Maximum_Subarray_Sum_with_One_Deletion();
        System.out.println(solution.maximumSum(new int[]{1,-2,0,3})); //4
        System.out.println(solution.maximumSum(new int[]{1,-2,-2,3})); //3
        System.out.println(solution.maximumSum(new int[]{-1,-1,-1,-1})); //-1
		System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
	}

}
