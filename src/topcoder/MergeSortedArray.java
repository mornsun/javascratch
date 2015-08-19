/**
 * 
 */
package topcoder;

import java.util.*;

/**
 * Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.

Note:
You may assume that nums1 has enough space (size that is greater or equal to m + n) to hold additional elements from nums2. The number of elements initialized in nums1 and nums2 are m and n respectively.

Hide Tags Array Two Pointers
Hide Similar Problems (E) Merge Two Sorted Lists

 * @author Chauncey
 *
 */
public class MergeSortedArray
{
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
    	int i1 = m-1, i2 = n-1, i = m+n-1;
    	while (i1>=0 && i2>=0) {
    		nums1[i--] = (nums1[i1] > nums2[i2]) ? nums1[i1--] : nums2[i2--];
    	}
    	while (i2>=0) {
    		nums1[i--] = nums2[i2--];
    	}
    }
	    
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		int[] nums1 = new int[]{1,1,6,7,9,10,15};
		int[] nums2 = new int[]{2,3,5,8};
		merge(nums1, 3, nums2, 4);
		for (int n:nums1)
			System.out.println(n);
	}

}
