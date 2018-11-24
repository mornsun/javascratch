/**
 * 
 */
package topcoder;

import java.util.HashSet;
import java.util.LinkedList;

/**
 * In a given 2D binary array A, there are two islands.  (An island is a 4-directionally connected group of 1s not connected to any other 1s.)
 Now, we may change 0s to 1s so as to connect the two islands together to form 1 island.
 Return the smallest number of 0s that must be flipped.  (It is guaranteed that the answer is at least 1.)

 Example 1:
 Input: [[0,1],[1,0]]
 Output: 1
 Example 2:
 Input: [[0,1,0],[0,0,0],[0,0,1]]
 Output: 2
 Example 3:
 Input: [[1,1,1,1,1],[1,0,0,0,1],[1,0,1,0,1],[1,0,0,0,1],[1,1,1,1,1]]
 Output: 1

 Note:
 1 <= A.length = A[0].length <= 100
 A[i][j] == 0 or A[i][j] == 1

 Related Topics:
 Dynamic Programming

 * @author Chauncey
 * beat 33.28%
 */
public class LC_934_Shortest_Bridge
{
	int[][] step = new int[][]{{-1,1,0,0},{0,0,-1,1}};

	public int shortestBridge(int[][] A) {
		if (A==null || A.length==0 || A[0].length==0)
			return 0;

		int y = A.length;
		int x = A[0].length;
		for (int i=0; i<A.length; ++i) {
			for (int j=0; j<A[0].length; ++j) {
				if (A[i][j] == 1) {
					y = i;
					x = j;
					break;
				}
			}
			if (x!=A[0].length)
				break;
		}
		//System.out.println(y+":"+x);
		if (y==A.length) // no land
			return 0;

		LinkedList<int[]> stack = new LinkedList<>();
		HashSet<Integer> seen = new HashSet<>();
		HashSet<Integer> target = new HashSet<>();
		int[] start = new int[]{y,x};
		stack.add(start);
		seen.add(y*A[0].length+x);
		//System.out.println(seen);
		while (!stack.isEmpty()) {
			int[] pt = stack.pollLast();
			for (int i=0; i<step[0].length; ++i) {
				int yy = pt[0] + step[0][i];
				if (yy < 0 || yy >= A.length)
					continue;
				int xx = pt[1] + step[1][i];
				if (xx < 0 || xx >= A[0].length)
					continue;
				int[] node = new int[]{yy, xx};
				int code = yy*A[0].length+xx;
				if (A[yy][xx] == 0) {
					target.add(code);
					continue;
				}
				if (seen.contains(code))
					continue;
				stack.addLast(node);
				seen.add(code);
				//System.out.println(yy+":"+xx);
			}
		}
		//System.out.println(seen);
		//System.out.println(target);

		int ans = 1;
		LinkedList<int[]> nextq = new LinkedList<>();
		for (int t : target) {
			nextq.addLast(new int[]{t/A[0].length, t%A[0].length});
		}

		while(!nextq.isEmpty()) {
			LinkedList<int[]> currq = nextq;
			nextq = new LinkedList<>();
			while (!currq.isEmpty()) {
				int[] pt = currq.poll();

				for (int i=0; i<step[0].length; ++i) {
					int yy = pt[0] + step[0][i];
					if (yy < 0 || yy >= A.length)
						continue;
					int xx = pt[1] + step[1][i];
					if (xx < 0 || xx >= A[0].length)
						continue;

					int[] node = new int[]{yy, xx};
					int code = yy*A[0].length+xx;
					if (A[yy][xx] == 1) {
						if (!seen.contains(code))
							return ans;
						continue;
					}
					if (target.contains(code))
						continue;
					nextq.offer(node);
					target.add(code);
				}
			}
			ans++;
		}
		return -1;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();

		LC_934_Shortest_Bridge solution = new LC_934_Shortest_Bridge();
		System.out.println(solution.shortestBridge(new int[][]{{0,1},{1,0}})); //1
		System.out.println(solution.shortestBridge(new int[][]{{0,1,0},{0,0,0},{0,0,1}})); //2
		System.out.println(solution.shortestBridge(new int[][]{{1,1,1,1,1},
															   {1,0,0,0,1},
															   {1,0,1,0,1},
															   {1,0,0,0,1},
															   {1,1,1,1,1}})); //1


		System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
	}

}
