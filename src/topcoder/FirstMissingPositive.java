package topcoder;

import java.util.Arrays;

/**
 * @author Chauncey
 *
 */
public class FirstMissingPositive {

	private static void bucket_sort(int[] nums) {
		for (int i=0; i<nums.length; ++i) {
			while (nums[i] != i + 1) {
				if (nums[i] <= 0 || nums[i] > nums.length || nums[i] == nums[nums[i]-1] )
					break;
				int idx = nums[i] - 1; // eg: set 1 to [0], 2 to [1]
				nums[i] = nums[idx];
				nums[idx] = idx + 1;
			}
		}
	}
    public static int firstMissingPositive(int[] nums) {
    	bucket_sort(nums);
        for (int i=0; i<nums.length; ++i) {
        	if (nums[i] != i+1) {
        		return i+1;
        	}
        }
        return nums.length + 1;
    }
    
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums = new int[]{3,4,-1,1};
    	for (int num : nums) {
    		System.out.print(num+", ");
    	}
    	System.out.println();
		System.out.println(firstMissingPositive(nums));
	}

}
