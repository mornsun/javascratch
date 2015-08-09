package topcoder;

/**
 * @author Chauncey
 *
 */
public class SearchinRotatedSortedArray {

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
    public static int search(int[] nums, int target) {
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
		int[] nums = new int[]{8,9};
		System.out.println(search(nums, 9));
	}

}
