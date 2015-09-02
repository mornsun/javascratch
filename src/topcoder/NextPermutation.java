package topcoder;

import java.util.*;

/**
 * Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.

If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).

The replacement must be in-place, do not allocate extra memory.

Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
1,2,3 → 1,3,2
3,2,1 → 1,2,3
1,1,5 → 1,5,1
Hide Tags Array
Hide Similar Problems (M) Permutations (M) Permutations II (M) Permutation Sequence (M) Palindrome Permutation II

 * @author Chauncey
 *
 */
public class NextPermutation
{
    public static void nextPermutation(int[] nums) {
    	if (nums==null || nums.length<2) return;
    	int k = nums.length-2;
    	for (; k>=0; --k) {
    		if (nums[k] < nums[k+1]) break;
    	}//1,3,2,5,4
    	if (k>=0) {
	    	//binary search
	    	int lo=k+1;
	    	int hi=nums.length-1;
	    	while (lo<hi) {
	    		int m = lo + ((hi-lo+1)>>1);
	    		if (nums[m] > nums[k]) {
	    			lo = m;
	    		} else {
	    			hi = m - 1;
	    		}
	    	}
	    	int tmp = nums[k];
	    	nums[k] = nums[lo];
	    	nums[lo] = tmp;
    	}
    	for (int i=k+1,j=nums.length-1; i<j; ++i,--j) {
    		int tmp = nums[i];
    		nums[i] = nums[j];
    		nums[j] = tmp;
    	}
    }
    
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		//int[] nums = new int[]{2,3,1,3,3};
		//int[] nums = new int[]{1,2,3};
		//int[] nums = new int[]{1,3,2};
		//int[] nums = new int[]{3,2,1};
		//int[] nums = new int[]{1,1,5};
		int[] nums = new int[]{1,5,1};
		nextPermutation(nums);
		for (int num : nums) {
			System.out.print(num+",");
		}
	}

}
