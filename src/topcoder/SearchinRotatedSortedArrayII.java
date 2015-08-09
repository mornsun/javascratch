package topcoder;

/**
 * @author Chauncey
 *
 */
public class SearchinRotatedSortedArrayII {

    public static boolean search(int[] nums, int target) {
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
		System.out.println(search(nums, 8));
	}

}
