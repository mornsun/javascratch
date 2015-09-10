package topcoder;

import java.util.*;

/**
 * Write a function to convert the array into alternate increasing decreasing numbers:

a[0] <= a[1] >= a[2] <= a[3] >=...

Note: You should solve it in place and one pass.

 * @author Chauncey
 *
 */
public class WiggleSort
{
    public static void wiggle_sort(int[] arr) {
    	if (arr == null || arr.length == 0) return;
    	boolean asc = true;
    	for (int i=1; i<arr.length; ++i) {
    		if (asc && arr[i]>=arr[i-1] || !asc && arr[i]<=arr[i-1]) {
    			//prev = arr[i];
    		} else {
    			swap(arr, i-1, i);
    		}
    		asc = !asc;
    	}
    }

    private final static void swap(int[] arr, int i, int j) {
    	int tmp = arr[i];
    	arr[i] = arr[j];
    	arr[j] = tmp;
    }
	    
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		int[] nums = new int[]{-2,0,0,2,2};
		wiggle_sort(nums);
		for (int num : nums) {
			System.out.print(num+",");
		}
		System.out.println();
		nums = new int[]{1,2,3,4,5};
		wiggle_sort(nums);
		for (int num : nums) {
			System.out.print(num+",");
		}
		System.out.println();
		nums = new int[]{5,4,3,2,1};
		wiggle_sort(nums);
		for (int num : nums) {
			System.out.print(num+",");
		}
		System.out.println();
		nums = new int[]{5,6,3,7,1};
		wiggle_sort(nums);
		for (int num : nums) {
			System.out.print(num+",");
		}
		System.out.println();
	}

}
