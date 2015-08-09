/**
 * 
 */
package topcoder;

import java.util.*;

/**
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
