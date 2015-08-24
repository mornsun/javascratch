package topcoder;

import java.util.*;

/**
 * Given an array of size n, find the majority element. The majority element is the element that appears more than ⌊ n/2 ⌋ times.

You may assume that the array is non-empty and the majority element always exist in the array.

Credits:
Special thanks to @ts for adding this problem and creating all test cases.

Hide Tags Divide and Conquer Array Bit Manipulation
Hide Similar Problems (M) Majority Element II

 * @author Chauncey
 *
 */
public class MajorityElement
{
    public List<Integer> majorityElement(int[] nums) {
        List<Integer> res = new ArrayList<Integer>();
        if (nums == null || nums.length == 0) return res;
        if (nums.length == 1) {
        	res.add(nums[0]);
        	return res;
        }
        int v1 = nums[0], c1 = 1;
        int v2 = nums[0], c2 = 0;
        for (int i=1; i<nums.length; ++i) {
        	if (nums[i] == v1){
        		++c1;
        	} else if (nums[i] == v2){
        		++c2;
        	} else if (c1 == 0) {
        		v1 = nums[i];
        		c1 = 1;
        	} else if (c2 == 0) {
        		v2 = nums[i];
        		c2 = 1;
        	} else {
        		--c1;
        		--c2;
        	}
        }
        if (c1 != 0) {
        	c1 = nums.length/3+1;
        }
        if (c2 != 0) {
        	c2 = nums.length/3+1;
        }
        for (int num : nums) {
        	if (c1==0 && c2==0) {
        		break;
        	} else if (num == v1) {
        		if (c1 != 0) {
        			--c1;
        			if (c1 == 0) {
        				res.add(v1);
        			}
        		}
        	} else if (num == v2) {
        		if (c2 != 0) {
        			--c2;
        			if (c2 == 0) {
        				res.add(v2);
        			}
        		}
        	}
        }
        return res;
    }
	    
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		MajorityElement solution = new MajorityElement();
		
		System.out.println(solution.majorityElement(new int[]{1, 1, 1, 2, 2, 2, 3, 3, 3, 3, 1}));
	}

}
