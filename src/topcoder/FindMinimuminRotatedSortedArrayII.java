/**
 * 
 */
package topcoder;

import java.util.*;

/**
 * Follow up for "Find Minimum in Rotated Sorted Array":
What if duplicates are allowed?

Would this affect the run-time complexity? How and why?
Suppose a sorted array is rotated at some pivot unknown to you beforehand.

(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

Find the minimum element.

The array may contain duplicates.

Hide Tags Array Binary Search
Hide Similar Problems (M) Find Minimum in Rotated Sorted Array

 * @author Chauncey
 *
 */
public class FindMinimuminRotatedSortedArrayII {
	public static int findMin(int[] nums) {
		if (null == nums || nums.length == 0)
			return 0;
		int l = 0, r = nums.length - 1;
		while (l < r) {
			int m = l + (r - l) / 2;
			if (nums[m] < nums[l]) {
				r = m;
			} else if (nums[m] > nums[l]) {
				if (nums[m] <= nums[r]) {
					break;
				}
				l = m + 1;
			} else if (nums[m] < nums[r]) {
				r = m;
			} else if (nums[m] > nums[r]) {
				l = m + 1;
			} else {
				++l;
			}
		}
		return nums[l];
	}

	int search(int A[], int n, int target) {
    	int first = 0, last = n;
    	while (first != last) {
    		int mid = (first + last) / 2;
    		if (A[mid] == target)
    			return mid;
    		if (A[first] <= A[mid]) {
    			if (A[first] <= target && target < A[mid])
    				last = mid;
    			else
    				first = mid + 1;
    		} else {
    			if (A[mid] < target && target <= A[last-1])
    				first = mid + 1;
    			else
    				last = mid;
    		}
    	}
    	return -1;
    }
	

    public int findMin0(int[] nums) {
    	if (null == nums || nums.length == 0) return 0;
        int l = 0, r = nums.length-1;
        while (l<r) {
        	int m = l + (r-l)/2;
        	if (nums[m] > nums[r]) {
        		l = m + 1;
        	} else {
        		r = m;
        	}
        }
        return nums[l];
    }

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] nums = new int[] { 3, 1, 3 };
		System.out.println(findMin(nums));
	}

}
