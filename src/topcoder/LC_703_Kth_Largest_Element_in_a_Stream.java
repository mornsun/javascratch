/**
 * 
 */
package topcoder;

import java.util.Arrays;
import java.util.Collection;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

/**
 * 	Design a class to find the kth largest element in a stream. Note that it is the kth largest element in the sorted order, not the kth distinct element.
 *
 * Implement KthLargest class:
 *
 * KthLargest(int k, int[] nums) Initializes the object with the integer k and the stream of integers nums.
 * int add(int val) Appends the integer val to the stream and returns the element representing the kth largest element in the stream.
 *
 * Example 1:
 *
 * Input
 * ["KthLargest", "add", "add", "add", "add", "add"]
 * [[3, [4, 5, 8, 2]], [3], [5], [10], [9], [4]]
 * Output
 * [null, 4, 5, 5, 8, 8]
 *
 * Explanation
 * KthLargest kthLargest = new KthLargest(3, [4, 5, 8, 2]);
 * kthLargest.add(3);   // return 4
 * kthLargest.add(5);   // return 5
 * kthLargest.add(10);  // return 5
 * kthLargest.add(9);   // return 8
 * kthLargest.add(4);   // return 8
 *
 * Constraints:
 *
 * 1 <= k <= 104
 * 0 <= nums.length <= 104
 * -104 <= nums[i] <= 104
 * -104 <= val <= 104
 * At most 104 calls will be made to add.
 * It is guaranteed that there will be at least k elements in the array when you search for the kth element.
 *
 * @author Chauncey
 * Runtime: 16 ms, faster than 70.10% of Java online submissions for Kth Largest Element in a Stream.
 * Memory Usage: 44.2 MB, less than 69.11% of Java online submissions for Kth Largest Element in a Stream.
 */
public class LC_703_Kth_Largest_Element_in_a_Stream
{
	private PriorityQueue<Integer> _pq;
	private int _k;

	public LC_703_Kth_Largest_Element_in_a_Stream(int k, int[] nums) {
		_k = k;
		_pq = new PriorityQueue<>();
		for (int num : nums) {
			if (_pq.size()>=k) {
				if (num>_pq.peek()) {
					_pq.poll();
					_pq.offer(num);
				}
			} else {
				_pq.offer(num);
			}
		}
	}

	public int add(int val) {
		if (_pq.size()>=_k) {
			if (val>_pq.peek()) {
				_pq.poll();
				_pq.offer(val);
			}
			return _pq.peek();
		} else {
			_pq.offer(val);
		}
		if (_pq.size()==_k) {
			return _pq.peek();
		}
		return -1;
	}

		/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();

		LC_703_Kth_Largest_Element_in_a_Stream solution = new LC_703_Kth_Largest_Element_in_a_Stream(3, new int[]{4, 5, 8, 2});
        System.out.println(solution.add(3)); //4
		System.out.println(solution.add(5)); //5
		System.out.println(solution.add(10)); //5
		System.out.println(solution.add(9)); //8
		System.out.println(solution.add(4)); //8
		solution = new LC_703_Kth_Largest_Element_in_a_Stream(1, new int[]{});
		System.out.println(solution.add(-3)); //-3
		System.out.println(solution.add(-2)); //-2
		System.out.println(solution.add(-4)); //-2
		System.out.println(solution.add(0)); //0
		System.out.println(solution.add(4)); //4
		System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
	}

}
