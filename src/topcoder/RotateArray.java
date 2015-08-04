/**
 * Given an array of non-negative integers, you are initially positioned at the first index of the array.
 * Each element in the array represents your maximum jump length at that position.
 * Determine if you are able to reach the last index.
 * For example:
 * A = [2,3,1,1,4], return true.
 * A = [3,2,1,0,4], return false.
 */
package topcoder;

/**
 * @author cherry
 *
 */
public class RotateArray {

	/**
	 * 
	 * @param nums
	 * @param begin inclusive
	 * @param end inclusive
	 */
    public static void reverse(int[] nums, int begin, int end)
    {
    	//0 1 2 3 4 5
    	int mid = begin + ((end+1-begin)>>1);
    	for (int i=begin; i<mid; ++i) {
    	    int right = end-i+begin;
    		int temp = nums[i];
    		nums[i] = nums[right];
    		nums[right] = temp;
    		//nums[i] ^= nums[end-i+begin];
    		//nums[end-i+begin] ^= nums[i];
    		//nums[i] ^= nums[end-i+begin];
    	}
    }
    
    public static void rotate(int[] nums, int k) {
    	k = k % nums.length;
    	reverse(nums, 0, nums.length-1);
    	reverse(nums, 0, k-1);
    	reverse(nums, k, nums.length-1);
    }
    
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums = new int[]{1,2,3,4,5,6,7};
		rotate(nums, 3);
		for (int num : nums) {
			System.out.print(num+",");
		}
		System.out.println();
	}

}
