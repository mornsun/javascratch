/**
 * 
 */
package topcoder;

import java.lang.reflect.Array;
import java.util.*;

/**
 * Given an array nums, we call (i, j) an important reverse pair if i < j and nums[i] > 2*nums[j].

You need to return the number of important reverse pairs in the given array.

Example1:

Input: [1,3,2,3,1]
Output: 2
Example2:

Input: [2,4,3,5,1]
Output: 3
Note:
The length of the given array will not exceed 50,000.
All the numbers in the input array are in the range of 32-bit integer.

 Related Topic
 Divide and Conquer, Binary Indexed Tree, Segment Tree, Binary Search Tree

 * @author Chauncey
 * beat 46.09%
 */
public class LC_493_Reverse_Pairs
{
	public int reversePairs(int[] nums) {
		if (nums==null || nums.length==0) {
			return 0;
		}
		return mergeSortAndCount(nums, 0, nums.length-1);
	}

	private static int mergeSortAndCount(int[] nums, int start, int end) {
		if (nums.length==0 || start>=end)
			return 0;

		int mid = start+(end-start>>1);
		int ans = mergeSortAndCount(nums, start, mid) + mergeSortAndCount(nums, mid+1, end);
		int i = start, j = mid+1;
		for (; i<=mid; ++i) {
			while (j<=end && nums[i]>2L*nums[j])
				j++;
			ans += j - (mid+1);
		}
		merge(nums, start, mid, end);

		return ans;
	}

	private static void merge(int[] A, int start, int mid, int end) {
		int[] L = new int[mid-start+1];
		for (int i=0; i<=mid-start; ++i) {
			L[i] = A[start+i];
		}
		int[] R = new int[end-mid];
		for (int i=0; i<end-mid; ++i) {
			R[i] = A[mid+1+i];
		}
		int l=0, r=0, i=start;
		while (l<L.length && r<R.length) {
			if (L[l] <= R[r]) {
				A[i++] = L[l++];
			} else {
				A[i++] = R[r++];
			}
		}
		while (l<L.length)
			A[i++] = L[l++];
		while (r<R.length)
			A[i++] = R[r++];
	}

    public int reversePairs1(int[] nums) {
    	if (nums==null || nums.length==0) {
    		return 0;
		}

    	ArrayList<Long> list = new ArrayList<>(nums.length);

    	int ans = 0;
		list.add(2L*nums[nums.length-1]);

    	for (int i=nums.length-2; i>=0; --i) {
    		int n = nums[i];
    		if (n%2==0) {
    			ans += binarySearch(list, n);
			} else {
				ans += binarySearch(list, n);
			}
			int idx = binarySearch(list, 2L*n);
			list.add(idx, 2L*n);
			System.out.println(list);
		}

    	return ans;
    }

    private static int binarySearch(ArrayList<Long> list, long target)
	{
		int lo = 0;
		int hi = list.size()-1;
		while (lo<hi) {
			int m = lo + (hi-lo+1>>1);
			if (list.get(m) >= target)
				hi = m-1;
			else
				lo = m;
		}
		return list.get(lo) < target ? lo+1 : lo;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();

		LC_493_Reverse_Pairs solution = new LC_493_Reverse_Pairs();
		System.out.println(solution.reversePairs(new int[]{1,3,2,3,1})); //2
		System.out.println(solution.reversePairs(new int[]{2,4,3,5,1})); //3
		System.out.println(solution.reversePairs(new int[]{-5, -5})); //1
		System.out.println(solution.reversePairs(new int[]{2147483647,2147483647,-2147483647,-2147483647,-2147483647,2147483647})); //9
		System.out.println(solution.reversePairs(new int[]{-4, -2, -1})); //0
		System.out.println(solution.reversePairs(new int[]{-3, -1, -1})); //1

		
		System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
	}

}
