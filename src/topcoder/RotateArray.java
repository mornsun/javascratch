package topcoder;

/**
 * Rotate an array of n elements to the right by k steps.

For example, with n = 7 and k = 3, the array [1,2,3,4,5,6,7] is rotated to [5,6,7,1,2,3,4].

Note:
Try to come up as many solutions as you can, there are at least 3 different ways to solve this problem.

[show hint]

Related problem: Reverse Words in a String II

Credits:
Special thanks to @Freezen for adding this problem and creating all test cases.

Hide Tags Array
Hide Similar Problems (M) Rotate List (M) Reverse Words in a String II

 * @author Chauncey
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
