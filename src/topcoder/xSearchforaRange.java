/**
 * 
 */
package topcoder;

/**
 * Given a sorted array of integers, find the starting and ending position of a given target value.

Your algorithm's runtime complexity must be in the order of O(log n).

If the target is not found in the array, return [-1, -1].

For example,
Given [5, 7, 7, 8, 8, 10] and target value 8,
return [3, 4].

Hide Tags Array Binary Search
Hide Similar Problems (M) Search Insert Position

 * @author Chauncey
 *
 */
public class xSearchforaRange
{
    public static int[] searchRange1(int[] nums, int target) {
    	if (nums == null || nums.length == 0) return new int[]{-1,-1};
    	int lo = 0;
    	int hi = nums.length-1;
    	while (lo < hi) {
    		int m = lo + ((hi-lo)>>1);
    		if (nums[m] >= target) {
    			hi = m;
    		} else {
    			lo = m + 1;
    		}
    	}
    	if (nums[lo] != target)
    		return new int[]{-1,-1};
    	int lrange = lo;
    	lo = 0;
    	hi = nums.length-1;
    	while (lo < hi) {
    		int m = lo + ((hi-lo+1)>>1);
    		if (nums[m] <= target) {
    			lo = m;
    		} else {
    			hi = m - 1;
    		}
    	}
    	int rrange = lo;
        return new int[]{lrange, rrange};
    }
    
    public static int[] searchRange(int[] nums, int target) {
    	if (nums == null || nums.length == 0) return new int[]{-1,-1};
    	int lo = 0;
    	int hi = nums.length-1;
    	int lrange = -1;
    	int rrange = -1;
    	while (lo <= hi) {
    		int mid = lo + ((hi-lo)>>1);
    		if (nums[mid] == target) {
    			int k = mid;
    			lrange = lo;
    			while (lrange < k) {
    				int m = lrange + ((k-lrange)>>1);
    				if (nums[m] >= target) {
    					k = m;
    				} else {
    					lrange = m + 1;
    				}
    			}
    			k = mid;
    			rrange = hi;
    			while (k < rrange) {
    				int m = k + ((rrange-k+1)>>1);
    				if (nums[m] <= target) {
    					k = m;
    				} else {
    					rrange = m - 1;
    				}
    			}
    			break;
    		} else if (nums[mid] > target) {
    			hi = mid - 1;
    		} else {
    			lo = mid + 1;
    		}
    	}
        return new int[]{lrange, rrange};
    }
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		int[] res = searchRange(new int[]{5, 7, 7, 7, 8, 8}, 8);
		for (int n : res) {
			System.out.println(n);
		}
	}

}
