/**
 * 
 */
package topcoder;

import java.util.*;

/**
 * 	Given a sorted array, two integers k and x, find the k closest elements to x in the array. The result should also be sorted in ascending order. If there is a tie, the smaller elements are always preferred.
 *
 * Example 1:
 * Input: [1,2,3,4,5], k=4, x=3
 * Output: [1,2,3,4]
 * Example 2:
 * Input: [1,2,3,4,5], k=4, x=-1
 * Output: [1,2,3,4]
 * Note:
 * The value k is positive and will always be smaller than the length of the sorted array.
 * Length of the given array is positive and will not exceed 104
 * Absolute value of elements in the array and x will not exceed 104
 *
 * Binary Search
 *
 * @author Chauncey
 * beats 99.70%
 */
public class xLC_658_Find_K_Closest_Elements
{
	public List<Integer> findClosestElements(int[] arr, int k, int x) {
		ArrayList<Integer> res = new ArrayList<>();
		if (arr==null || arr.length==0) return res;
		int n = arr.length;

		int lo = 0, hi = n - k;
		while (lo < hi) {
			int mid = (lo + hi) / 2;
			if (x - arr[mid] > arr[mid+k] - x)
				lo = mid + 1;
			else
				hi = mid;
		}

		hi = Math.min(lo+k, n);
		for (; lo<hi; ++lo) {
			res.add(arr[lo]);
		}
		return res;
	}

    /**
	 * @param args
	 */
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();

		xLC_658_Find_K_Closest_Elements solution = new xLC_658_Find_K_Closest_Elements();
		System.out.println(solution.findClosestElements(new int[]{0,0,1,2,3,3,4,7,7,8}, 3, 5)); //[3,3,4]
		System.out.println(solution.findClosestElements(new int[]{0,2,2,3,4,6,7,8,9,9}, 4, 5)); //[3,4,6,7]
		System.out.println(solution.findClosestElements(new int[]{0,1,2,2,2,3,6,8,8,9}, 5, 9)); //[3,6,8,8,9]
		System.out.println(solution.findClosestElements(new int[]{1,2,5,5,6,6,7,7,8,9}, 7, 7)); //[5,5,6,6,7,7,8]
		System.out.println(solution.findClosestElements(new int[]{0,1,2,3,4,4,4,5,5,5,6,7,9,9,10,10,11,11,12,13,14,14,15,17,19,19,22,24,24,25,25,27,27,29,30,32,32,33,33,35,36,38,39,41,42,43,44,44,46,47,48,49,52,53,53,54,54,57,57,58,59,59,59,60,60,60,61,61,62,64,66,68,68,70,72,72,74,74,74,75,76,76,77,77,80,80,82,83,85,86,87,87,92,93,94,96,96,97,98,99}, 25, 90)); //[72,74,74,74,75,76,76,77,77,80,80,82,83,85,86,87,87,92,93,94,96,96,97,98,99]
		System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
	}

}
