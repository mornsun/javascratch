/**
 * 
 */
package topcoder;

import java.util.*;

/**
 * @author cherry
 *
 */
public class A3Sum
{
    public static List<List<Integer>> threeSum(int[] nums) {
    	List<List<Integer>> result = new LinkedList<List<Integer>>();
    	if (nums.length < 3)
    		return result;
    	Arrays.sort(nums);
    	int target = 0;
    	for (int i=0; i < nums.length-2; ++i) {
    		if (i>0 && nums[i]==nums[i-1]) {
    			continue;
    		}
	    	int j = i+1;
	    	int k = nums.length-1;
	    	while (j < k) {
	    		//System.out.println(nums[i]+":"+ nums[j] +":"+ nums[k]);
	    		if (j != i+1 && nums[j]==nums[j-1]) {
	    			++j;
	    			continue;
	    		}
	    		if (k != nums.length-1 && nums[k]==nums[k+1]) {
	    			--k;
	    			continue;
	    		}
		    	if (nums[i] + nums[j] + nums[k] < target) {
		    		++j;
		    	} else if (nums[i] + nums[j] + nums[k] > target) {
		    		--k;
		    	} else {
		    		int[] arr = {nums[i], nums[j], nums[k]};
		    		List<Integer> list = new LinkedList<Integer>();
		    		for (int n : arr) {
		    			list.add(n);
		    		}
		    		result.add(list);
		    		++j;
		    		--k;
		    	}
	    	}
    	}
    	return result;
    }
	    
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		int[] nums = new int[]{-2,0,0,2,2};
		List<List<Integer>> list = threeSum(nums);
		for (List<Integer> l : list) {
			System.out.println(l);
		}
	}

}
