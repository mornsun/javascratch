/**
 * 
 */
package topcoder;

/**
 * Find the contiguous subarray within an array (containing at least one number) which has the largest product.

For example, given the array [2,3,-2,4],
the contiguous subarray [2,3] has the largest product = 6.

Hide Tags Array Dynamic Programming
Hide Similar Problems (M) Maximum Subarray (M) Product of Array Except Self

 * @author Chauncey
 *
 */
public class MaximumProductSubarray
{
    public static int maxSubProduct(int[] nums, int lo, int hi) {
    	if (lo == hi) return nums[lo];
    	int n_minus = 0;
    	int first_minus = -1;
    	int last_minus = -1;
        for (int i=lo; i<=hi; ++i) {
        	if (nums[i] < 0) {
        		++n_minus;
        		if (first_minus == -1) {
        			first_minus = i;
        			last_minus = i;
        		} else {
        			last_minus = i;
        		}
        	}
        }
        if ((n_minus & 0x01) == 0) {
        	int product = 1;
            for (int i=lo; i<=hi; ++i) {
            	product *= nums[i];
            }
        	return product;
        } else {
        	int product1 = 1;
        	for (int i=lo; i<first_minus; ++i) {
            	product1 *= nums[i];
        	}
        	int product2 = 1;
        	for (int i=first_minus+1; i<last_minus; ++i) {
            	product2 *= nums[i];
        	}
        	int product3 = 1;
        	for (int i=last_minus+1; i<=hi; ++i) {
            	product3 *= nums[i];
        	}
        	if (first_minus == last_minus) {
        		return product1 > product3 ? product1 : product3;
        	} else {
        		int product_l = product1*product2*nums[first_minus];
        		int product_r = product2*product3*nums[last_minus];
        		return product_l > product_r ? product_l : product_r;
        	}
        }
    }
    public static int maxProduct(int[] nums) {
    	if (null == nums || nums.length == 0) return 0;
    	int lo = 0;
    	int max = Integer.MIN_VALUE;
        for (int i=0; i<nums.length; ++i) {
        	if (nums[i] == 0) {
        		if (lo == i) {
        			++lo;
        		} else {
        			int res = maxSubProduct(nums, lo, i-1);
        			if (res > max) max = res;
        			lo = i+1;
        		}
        	}
        }
        if (lo != nums.length) {
			int res = maxSubProduct(nums, lo, nums.length-1);
			if (res > max) max = res;
        }
    	return (max < 0 && lo != 0) ? 0 : max;
    }
    /**
     * @param args
     */
    public static void main(String[] args)
    {
        // TODO Auto-generated method stub
        int[] prices = new int[]{-2,0,-2};
        int res = maxProduct(prices);
        System.out.println(res);
    }

}
