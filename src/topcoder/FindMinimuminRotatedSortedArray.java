/**
 * 
 */
package topcoder;

import java.util.*;

/**
 * @author Chauncey
 *
 */
public class FindMinimuminRotatedSortedArray {
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
