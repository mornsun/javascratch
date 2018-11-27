package topcoder;

/**
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 (i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).
 You are given a target value to search. If found in the array return its index, otherwise return -1.
 You may assume no duplicate exists in the array.
 Your algorithm's runtime complexity must be in the order of O(log n).
 Example 1:
 Input: nums = [4,5,6,7,0,1,2], target = 0
 Output: 4
 Example 2:
 Input: nums = [4,5,6,7,0,1,2], target = 3
 Output: -1

Hide Tags Array Binary Search
Hide Similar Problems (M) Search in Rotated Sorted Array II (M) Find Minimum in Rotated Sorted Array

 * @author Chauncey
 * beat 81.23%
 */
public class SearchinRotatedSortedArray {

	public static int search(int[] nums, int target) {
		if (nums==null || nums.length==0)
			return -1;
		int lo = 0;
		int hi = nums.length-1;
		while (lo<hi) {
			int m = lo + (hi-lo>>1);
			if (nums[m] == target) {
				return m;
			}
			if (nums[m] < nums[hi]) {
				if (target > nums[m] && target <= nums[hi]) {
					lo = m + 1;
				} else {
					hi = m - 1;
				}
			} else {
				if (target < nums[m] && target >= nums[lo]) {
					hi = m - 1;
				} else {
					lo = m + 1;
				}
			}
		}
		return nums[lo] == target ? lo : -1;
	}

    public static int search1(int[] nums, int target) {
    	int lo = 0;
    	int hi = nums.length-1;
        while (lo < hi) {
        	int m = lo + ((hi-lo)>>1);
        	if (nums[m] > nums[hi]) {
        		lo = m + 1;
        	} else {
        		hi = m;
        	}
        }
        int pivot = lo;
    	lo = 0;
    	hi = nums.length-1;
        while (lo <= hi) {
        	int m = lo + ((hi-lo)>>1);
        	int idx = (pivot + m) % nums.length;
        	if (nums[idx] == target) {
        		return idx;
        	} else if (nums[idx] < target) {
        		lo = m + 1;
        	} else {
        		hi = m - 1;
        	}
        }
        return -1;
    }
    public static int search2(int[] nums, int target) {
    	int lo = 0;
    	int hi = nums.length-1;
        while (lo <= hi) {
        	int m = lo + ((hi-lo)>>1);
        	if (nums[m] == target) {
        		return m;
        	} else if (nums[m] > nums[hi]) {
        		if (target < nums[m] && target >= nums[lo])
        			hi = m - 1;
        		else
        			lo = m + 1;
        	} else {
        		if (target > nums[m] && target <= nums[hi])
        			lo = m + 1;
        		else
        			hi = m - 1;
        	}
        }
        return -1;
    }
    
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(search(new int[]{8,9}, 9)); //1
		System.out.println(search(new int[]{4,5,6,7,0,1,2}, 0)); //4
		System.out.println(search(new int[]{4,5,6,7,0,1,2}, 3)); //-1
		System.out.println(search(new int[]{3,1}, 3)); //0
	}

}
