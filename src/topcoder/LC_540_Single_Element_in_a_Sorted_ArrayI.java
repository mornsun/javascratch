/**
 * 
 */
package topcoder;

/**
 * Given a sorted array consisting of only integers where every element appears twice except for one element which appears once. Find this single element that appears only once.
 Example 1:
 Input: [1,1,2,3,3,4,4,8,8]
 Output: 2

 Example 2:
 Input: [3,3,7,7,10,11,11]
 Output: 10

 Note: Your solution should run in O(log n) time and O(1) space.

 Related Topics:

 * @author Chauncey
 * beat 37.25%
 */
public class LC_540_Single_Element_in_a_Sorted_ArrayI
{
	public int singleNonDuplicate(int[] nums) {
		if (nums==null || nums.length==0)
			return 0;
		int lo = 0;
		int hi = nums.length;
		while (lo<=hi) {
			int m = lo + (hi-lo>>1);
			if ((m&1)==0) {
				if (m<nums.length-1 && nums[m]==nums[m+1]) {
					lo = m + 1;
				} else if (m>0 && nums[m]==nums[m-1]) {
					hi = m - 1;
				} else {
					return nums[m];
				}
			} else {
				if (m<nums.length-1 && nums[m]==nums[m+1]) {
					hi = m - 1;
				} else if (m>0 && nums[m]==nums[m-1]) {
					lo = m + 1;
				} else {
					return nums[m];
				}
			}
			//System.out.println(lo+":"+hi+":"+m+":"+nums[m]);
		}
		return 0;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();

		LC_540_Single_Element_in_a_Sorted_ArrayI solution = new LC_540_Single_Element_in_a_Sorted_ArrayI();
		System.out.println(solution.singleNonDuplicate(new int[]{1,1,2,3,3,4,4,8,8})); //2
		System.out.println(solution.singleNonDuplicate(new int[]{3,3,7,7,10,11,11})); //10

		System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
	}

}
