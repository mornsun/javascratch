package topcoder;

/**
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 (i.e., [0,0,1,2,2,5,6] might become [2,5,6,0,0,1,2]).
 You are given a target value to search. If found in the array return true, otherwise return false.

 Example 1:
 Input: nums = [2,5,6,0,0,1,2], target = 0
 Output: true

 Example 2:
 Input: nums = [2,5,6,0,0,1,2], target = 3
 Output: false

 Follow up:
 This is a follow up problem to Search in Rotated Sorted Array, where nums may contain duplicates.
 Would this affect the run-time complexity? How and why?

Hide Tags Array Binary Search
Hide Similar Problems (H) Search in Rotated Sorted Array

 * @author Chauncey
 * beat 100%
 */
public class xSearchinRotatedSortedArrayII {

	public static boolean search(int[] nums, int target) {
		if (nums==null || nums.length==0)
			return false;
		int lo = 0;
		int hi = nums.length-1;
		while (lo<hi) {
			int m = lo + (hi-lo>>1);
			if (nums[m] == target) {
				return true;
			}
			if (nums[m] < nums[lo]) {
				if (nums[m] < target && target <= nums[hi])
					lo = m + 1;
				else
					hi = m - 1;
			} else if (nums[m] > nums[lo]) {
				if (nums[lo] <= target && target < nums[m])
					hi = m - 1;
				else
					lo = m + 1;
			} else {
				if (nums[m] < nums[hi]) { // [1, 1, 1, 2, 3]
					if (nums[m] < target && target <= nums[hi])
						lo = m + 1;
					else
						return false;
				} else if (nums[m] > nums[hi]) { // [3, 3, 3, 4, 1]
					lo = m + 1;
				} else { // [3, 1, 2, 3, 3, 3, 3]
					++lo;
					--hi;
				}
			}
		}
		return target == nums[lo] ? true : false;
	}

    public static boolean search1(int[] nums, int target) {
    	int lo = 0;
    	int hi = nums.length-1;
        while (lo <= hi) {
        	int m = lo + ((hi-lo)>>1);
        	if (nums[m] == target) {
        		return true;
        	} else if (nums[m] < nums[lo]) {
        		if (target > nums[m] && target <= nums[hi])
        			lo = m + 1;
        		else
        			hi = m - 1;
        	} else if (nums[m] > nums[lo]){
        		if (target < nums[m] && target >= nums[lo])
        			hi = m - 1;
        		else
        			lo = m + 1;
        	} else { //==
        		if (nums[m] < nums[hi]) {
        			if (target > nums[m] && target <= nums[hi])
        				lo = m + 1;
        			else
        				hi = m - 1;
        		} else if (nums[m] > nums[hi]) {
        			lo = m + 1;
        		} else {
        			++lo;
        			--hi;
        		}
        	}
        }
        return false;
    }
    
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums = new int[]{8,8};
		System.out.println(search(new int[]{8,8}, 8)); // true
		System.out.println(search(new int[]{2,5,6,0,0,1,2}, 0)); // true
		System.out.println(search(new int[]{2,5,6,0,0,1,2}, 3)); // false
	}

}
