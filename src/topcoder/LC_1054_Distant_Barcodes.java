/**
 * 
 */
package topcoder;

import java.util.*;

/**
 * 	In a warehouse, there is a row of barcodes, where the i-th barcode is barcodes[i].
 * Rearrange the barcodes so that no two adjacent barcodes are equal.  You may return any answer, and it is guaranteed an answer exists.
 *
 * Example 1:
 * Input: [1,1,1,2,2,2]
 * Output: [2,1,2,1,2,1]
 *
 * Example 2:
 * Input: [1,1,1,1,2,2,3,3]
 * Output: [1,3,1,3,2,1,2,1]
 *
 * Note:
 * 1 <= barcodes.length <= 10000
 * 1 <= barcodes[i] <= 10000
 *
 * Heap Sort

 * @author Chauncey
 * Runtime: 53 ms, faster than 61.07%
 */
public class LC_1054_Distant_Barcodes
{
	public int[] rearrangeBarcodes(int[] barcodes) {
		int[] res = new int[barcodes.length];
		if (barcodes==null || barcodes.length==1)
			return barcodes;

		Arrays.sort(barcodes);
		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> (b[0]-a[0]));
		int prev = 0;
		int cnt = 0;
		for (int b : barcodes) {
			if (b==prev)
				cnt++;
			else {
				if (prev != 0)
					pq.add(new int[]{cnt, prev});
				cnt = 1;
				prev = b;
			}
		}
		if (prev != 0)
			pq.add(new int[]{cnt, prev});

		if (pq.size()==1)
			return barcodes;

		int k=0;
		prev = 0;
		while (!pq.isEmpty()) {
			int[] most = pq.poll();
			if (most[1] != prev) {
				res[k++] = most[1];
				most[0]--;
				prev = most[1];
				if (most[0]!=0)
					pq.offer(most);
			} else {
				int[] more = pq.poll();
				pq.offer(most);
				res[k++] = more[1];
				more[0]--;
				prev = more[1];
				if (more[0]!=0)
					pq.offer(more);
			}
		}
		return res;

	}

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();

		LC_1054_Distant_Barcodes solution = new LC_1054_Distant_Barcodes();
		System.out.println(solution.rearrangeBarcodes(new int[]{7,7,7,8,5,7,5,5,5,8,8}));
		System.out.println(solution.rearrangeBarcodes(new int[]{1,1,1,2,2,2}));
		System.out.println(solution.rearrangeBarcodes(new int[]{1,1,1,1,2,2,3,3}));
		System.out.println(solution.rearrangeBarcodes(new int[]{1}));
		System.out.println(solution.rearrangeBarcodes(new int[]{1,1,2}));

		System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
	}

}
