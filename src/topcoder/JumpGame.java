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
public class JumpGame {

	public static boolean solute(int[] nums)
	{
		int reach = 0;
		for (int i=0; i<=reach && reach<nums.length-1; ++i) {
			int to = i+nums[i];
			if (reach < to) {
				reach = to;
			}
		}
		return reach >= nums.length-1;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int[] a = new int[]{0};
		System.out.println(JumpGame.solute(a));
	}

}
