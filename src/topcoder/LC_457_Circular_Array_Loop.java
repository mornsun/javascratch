/**
 * 
 */
package topcoder;

/**
 * 	You are given a circular array nums of positive and negative integers. If a number k at an index is positive, then move forward k steps. Conversely, if it's negative (-k), move backward k steps. Since the array is circular, you may assume that the last element's next element is the first element, and the first element's previous element is the last element.
 *
 * Determine if there is a loop (or a cycle) in nums. A cycle must start and end at the same index and the cycle's length > 1. Furthermore, movements in a cycle must all follow a single direction. In other words, a cycle must not consist of both forward and backward movements.
 *
 * Example 1:
 *
 * Input: [2,-1,1,2,2]
 * Output: true
 * Explanation: There is a cycle, from index 0 -> 2 -> 3 -> 0. The cycle's length is 3.
 *
 * Example 2:
 *
 * Input: [-1,2]
 * Output: false
 * Explanation: The movement from index 1 -> 1 -> 1 ... is not a cycle, because the cycle's length is 1. By definition the cycle's length must be greater than 1.
 *
 * Example 3:
 *
 * Input: [-2,1,-1,-2,-2]
 * Output: false
 * Explanation: The movement from index 1 -> 2 -> 1 -> ... is not a cycle, because movement from index 1 -> 2 is a forward movement, but movement from index 2 -> 1 is a backward movement. All movements in a cycle must follow a single direction.
 *
 * Note:
 *
 * -1000 ≤ nums[i] ≤ 1000
 * nums[i] ≠ 0
 * 1 ≤ nums.length ≤ 5000
 *
 * Follow up:
 *
 * Could you solve it in O(n) time complexity and O(1) extra space complexity?
 *
 * Array, Two Pointer
 * 
 * @author Chauncey
 * Runtime: 0 ms, faster than 100.00% of Java online submissions for Circular Array Loop.
 * Memory Usage: 34.2 MB, less than 100.00% of Java online submissions for Circular Array Loop.
 */
public class LC_457_Circular_Array_Loop
{
	public boolean circularArrayLoop(int[] nums) {
		if (nums==null || nums.length==0) return false;
		int n = nums.length;
		for (int i=0; i<n; ++i) {
			int label = i+1;
			if (nums[i]>1000) continue;
			boolean forward = nums[i]>0;
			int next = ((i+nums[i])%n+n)%n;
			int step = 0;
			nums[i] = 10000+label;
			while (nums[next]<=1000 && nums[next]>0==forward) {
				int curr = next;
				next = ((curr+nums[curr])%n+n)%n;
				nums[curr] = 10000+label;
				if (curr==next) {
					step=0;
					break;
				}
				++step;
			}
			if (nums[next]==10000+label && step!=0) return true;
		}
		return false;
	}

    /**
	 * @param args
	 */
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();

		LC_457_Circular_Array_Loop solution = new LC_457_Circular_Array_Loop();
		System.out.println(solution.circularArrayLoop(new int[]{2,-1,1,2,2})); //true
		System.out.println(solution.circularArrayLoop(new int[]{-1,-2,-3,-4,-5})); //false
		System.out.println(solution.circularArrayLoop(new int[]{-1,2})); //false
		System.out.println(solution.circularArrayLoop(new int[]{-2,1,-1,-2,-2})); //false
		System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
	}

}
