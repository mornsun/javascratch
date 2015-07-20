/**
 * 
 */
package topcoder;

import java.util.*;

/**
 * @author cherry
 *
 */
public class FindMinimuminRotatedSortedArray
{
    public static int findMin(int[] nums) {
    	if (null == nums || nums.length == 0) return 0;
        int l = 0, r = nums.length-1;
        while (l<r) {
        	int m = l + (r-l)/2;
        	if (nums[m] < nums[l]) {
        		r = m;
        	} else if (nums[m] > nums[l]) {
        		if (nums[m] <= nums[r]) {
            		break;
        		}
        		l = m+1;
        	} else if (nums[m] < nums[r]) {
        		r = m;
        	} else if (nums[m] > nums[r]) {
        		l = m+1;
        	} else {
        		++l;
        	}
        }
        return nums[l];
    }
	    
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		int[] nums = new int[]{1,3,3};
		System.out.println(findMin(nums));
	}

}
