/**
 * 
 */
package topcoder;

import java.util.*;

/**
 * 	We have a set of items: the i-th item has value values[i] and label labels[i].
 * Then, we choose a subset S of these items, such that:
 *
 * |S| <= num_wanted
 * For every label L, the number of items in S with label L is <= use_limit.
 * Return the largest possible sum of the subset S.
 *
 * Example 1:
 *
 * Input: values = [5,4,3,2,1], labels = [1,1,2,2,3], num_wanted = 3, use_limit = 1
 * Output: 9
 * Explanation: The subset chosen is the first, third, and fifth item.
 *
 * Example 2:
 *
 * Input: values = [5,4,3,2,1], labels = [1,3,3,3,2], num_wanted = 3, use_limit = 2
 * Output: 12
 * Explanation: The subset chosen is the first, second, and third item.
 *
 * Example 3:
 *
 * Input: values = [9,8,8,7,6], labels = [0,0,0,1,1], num_wanted = 3, use_limit = 1
 * Output: 16
 * Explanation: The subset chosen is the first and fourth item.
 *
 * Example 4:
 *
 * Input: values = [9,8,8,7,6], labels = [0,0,0,1,1], num_wanted = 3, use_limit = 2
 * Output: 24
 * Explanation: The subset chosen is the first, second, and fourth item.
 *
 * Note:
 *
 * 1 <= values.length == labels.length <= 20000
 * 0 <= values[i], labels[i] <= 20000
 * 1 <= num_wanted, use_limit <= values.length
 *
 * @author Chauncey
 * Runtime: 51 ms, faster than 37.24% of Java online submissions for Largest Values From Labels.
 * Memory Usage: 39.3 MB, less than 100.00% of Java online submissions for Largest Values From Labels.
 */
public class LC_1090_Largest_Values_From_Labels
{
	public int largestValsFromLabels(int[] values, int[] labels, int num_wanted, int use_limit) {

		if (values==null || values.length==0 || labels==null || labels.length!=values.length)
			return 0;

		int n = values.length;
		int[][] items = new int[n][];
		for (int i=0; i<n; ++i)
			items[i] = new int[]{values[i], labels[i]};
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1,o2)->o2[0]-o1[0]);
		pq.addAll(Arrays.asList(items));
		HashMap<Integer, Integer> cntmap = new HashMap<>();
		int res = 0;
		while (!pq.isEmpty() && num_wanted>0) {
			int[] item = pq.poll();
			int cnt = cntmap.getOrDefault(item[1], 0);
			//System.out.printf("[%d,%d]", item[0], item[1]);
			if (cnt>=use_limit) continue;
			res += item[0];
			--num_wanted;
			cntmap.put(item[1], cnt+1);
		}
		//System.out.println();
		return res;

	}

    /**
	 * @param args
	 */
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();

		LC_1090_Largest_Values_From_Labels solution = new LC_1090_Largest_Values_From_Labels();
        System.out.println(solution.largestValsFromLabels(new int[]{5,4,3,2,1}, new int[]{1,1,2,2,3}, 3, 1)); //9
		System.out.println(solution.largestValsFromLabels(new int[]{5,4,3,2,1}, new int[]{1,3,3,3,2}, 3, 2)); //12
		System.out.println(solution.largestValsFromLabels(new int[]{9,8,8,7,6}, new int[]{0,0,0,1,1}, 3, 1)); //16
		System.out.println(solution.largestValsFromLabels(new int[]{9,8,8,7,6}, new int[]{0,0,0,1,1}, 3, 2)); //24
		System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
	}

}
