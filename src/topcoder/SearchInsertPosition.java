/**
 * 
 */
package topcoder;

/**
 * @author Chauncey
 *
 */
public class SearchInsertPosition
{
    public static int searchInsert(int[] nums, int target) {
    	if (nums == null || nums.length == 0) return 0;
        int lo = 0;
        int hi = nums.length - 1;
        if (nums[hi] < target) return hi+1;
        while (lo < hi) {
        	int m = lo + ((hi-lo)>>1);
        	if (nums[m] == target) {
        		return m;
        	} else if (nums[m] < target) {
        		lo = m + 1;
        	} else {
        		hi = m;
        	}
        }
        return lo;
    }
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		System.out.println(searchInsert(new int[]{1,3,5,6}, 0));
	}

}
