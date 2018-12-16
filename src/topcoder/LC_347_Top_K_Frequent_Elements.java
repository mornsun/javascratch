/**
 * 
 */
package topcoder;

import java.util.*;

/**
 * Given a non-empty array of integers, return the k most frequent elements.

 Example 1:

 Input: nums = [1,1,1,2,2,3], k = 2
 Output: [1,2]
 Example 2:

 Input: nums = [1], k = 1
 Output: [1]
 Note:

 You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
 Your algorithm's time complexity must be better than O(n log n), where n is the array's size.

 Related Topics
 Hash table, Heap

 Similar Questions
 Word Frequency, Kth Largest Element in an Array, Sort Characters By Frequency, Split Array into Consecutive Subsequences, Top K Frequent Words

 * @author Chauncey
 * beat 32.74%
 */
public class LC_347_Top_K_Frequent_Elements
{
    public List<Integer> topKFrequent(int[] nums, int k) {
        LinkedList<Integer> ans = new LinkedList<>();
        if (nums==null || nums.length==0 || k>nums.length) {
            return ans;
        }

        HashMap<Integer, Integer> map =  new HashMap<>();
        for (int n : nums) {
            map.put(n, map.getOrDefault(n, 0)+1);
        }

        PriorityQueue<int[]> heap = new PriorityQueue<>((a,b)->(a[0]-b[0]));
        for (Map.Entry<Integer, Integer> en : map.entrySet()) {
            heap.add(new int[]{en.getValue(),en.getKey()});
            if (heap.size() > k) {
                heap.poll();
            }
        }

        while (!heap.isEmpty()) {
            ans.addFirst(heap.poll()[1]);
        }

        return ans;
    }

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();

		LC_347_Top_K_Frequent_Elements solution = new LC_347_Top_K_Frequent_Elements();
		System.out.println(solution.topKFrequent(new int[]{1,1,1,2,2,3}, 2)); //[1,2]
        System.out.println(solution.topKFrequent(new int[]{1}, 1)); //[1]

		System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
	}
}
