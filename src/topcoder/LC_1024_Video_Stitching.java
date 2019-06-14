/**
 * 
 */
package topcoder;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 	You are given a series of video clips from a sporting event that lasted T seconds.  These video clips can be overlapping with each other and have varied lengths.
 *
 * Each video clip clips[i] is an interval: it starts at time clips[i][0] and ends at time clips[i][1].  We can cut these clips into segments freely: for example, a clip [0, 7] can be cut into segments [0, 1] + [1, 3] + [3, 7].
 *
 * Return the minimum number of clips needed so that we can cut the clips into segments that cover the entire sporting event ([0, T]).  If the task is impossible, return -1.
 *
 * Example 1:
 *
 * Input: clips = [[0,2],[4,6],[8,10],[1,9],[1,5],[5,9]], T = 10
 * Output: 3
 * Explanation:
 * We take the clips [0,2], [8,10], [1,9]; a total of 3 clips.
 * Then, we can reconstruct the sporting event as follows:
 * We cut [1,9] into segments [1,2] + [2,8] + [8,9].
 * Now we have segments [0,2] + [2,8] + [8,10] which cover the sporting event [0, 10].
 *
 * Example 2:
 *
 * Input: clips = [[0,1],[1,2]], T = 5
 * Output: -1
 * Explanation:
 * We can't cover [0,5] with only [0,1] and [0,2].
 *
 * Example 3:
 *
 * Input: clips = [[0,1],[6,8],[0,2],[5,6],[0,4],[0,3],[6,7],[1,3],[4,7],[1,4],[2,5],[2,6],[3,4],[4,5],[5,7],[6,9]], T = 9
 * Output: 3
 * Explanation:
 * We can take clips [0,4], [4,7], and [6,9].
 *
 * Example 4:
 *
 * Input: clips = [[0,4],[2,8]], T = 5
 * Output: 2
 * Explanation:
 * Notice you can have extra video after the event ends.
 *
 * Note:
 *
 * 1 <= clips.length <= 100
 * 0 <= clips[i][0], clips[i][1] <= 100
 * 0 <= T <= 100
 *
 * Dynamic Programming

 * @author Chauncey
 * Runtime: 32 ms, faster than 20.36%
 */
public class LC_1024_Video_Stitching
{
	public int videoStitching(int[][] clips, int T) {

		if (clips == null) return -1;
		if (clips.length==0 && T==0) return 0;
		else if (clips.length==0 || T==0) return -1;

		Arrays.sort(clips, (a, b) -> a[0] - b[0]);

		int start = 0;
		int res = 0;
		int max = 0;

		for (int[] clip : clips) {
			//System.out.println(clip[0]+":"+clip[1]);

			if (clip[0]<=start) {
				max = Math.max(max, clip[1]);
				if (max >= T) return res+1;
			} else if (clip[0]<=max) {
				res++;
				start = max;
				max = Math.max(max, clip[1]);
				if (max >= T) return res+1;
			} else {
				return -1;
			}
		}

		return -1;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();

		LC_1024_Video_Stitching solution = new LC_1024_Video_Stitching();
		//System.out.println(solution.videoStitching(new int[]{7,7,7,8,5,7,5,5,5,8,8}));

		System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
	}

}
