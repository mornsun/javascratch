/**
 * 
 */
package topcoder;

import java.util.Arrays;

/**
 * 	Given an array consists of non-negative integers, your task is to count the number of triplets chosen from the array that can make triangles if we take them as side lengths of a triangle.
 * Example 1:
 * Input: [2,2,3,4]
 * Output: 3
 * Explanation:
 * Valid combinations are:
 * 2,3,4 (using the first 2)
 * 2,3,4 (using the second 2)
 * 2,2,3
 * Note:
 * The length of the given array won't exceed 1000.
 * The integers in the given array are in the range of [0, 1000].
 *
 * Array
 *
 * @author Chauncey
 * Runtime: 4 ms, faster than 99.46% of Java online submissions for Valid Triangle Number.
 * Memory Usage: 39.6 MB, less than 30.00% of Java online submissions for Valid Triangle Number.
 *
 * Runtime: 5 ms, faster than 56.80% of Java online submissions for Valid Triangle Number.
 * Memory Usage: 36.5 MB, less than 100.00% of Java online submissions for Valid Triangle Number.
 */
public class LC_611_Valid_Triangle_Number
{
	public int triangleNumber(int[] nums) {
		if (nums==null || nums.length==0) return 0;
		Arrays.sort(nums);
		int res = 0;
		for (int i=nums.length-1; i>=0; --i) {
			int l=0, r=i-1;
			while (l<r) {
				if (nums[l] + nums[r] > nums[i]) {
					res += r-l;
					--r;
				} else {
					++l;
				}
			}
		}
		return res;
	}

	public int triangleNumber1(int[] nums) {
		if (nums==null || nums.length==0) return 0;
		Arrays.sort(nums);
		int n=nums.length;
		int res = 0;
		for (int i=0; i<n-2; ++i) {
			int k = i+2;
			for (int j=i+1; j<n-1; ++j) {
				int a = nums[i] + nums[j];
				if (j>=k) k=j+1;
				while (k<n && nums[k] < a) ++k;
				if (j<k-1) res += k-j-1;
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

		LC_611_Valid_Triangle_Number solution = new LC_611_Valid_Triangle_Number();
		System.out.println(solution.triangleNumber(new int[]{2,2,3,4})); //6
		System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
	}

}
