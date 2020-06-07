/**
 * 
 */
package topcoder;

import java.util.ArrayList;
import java.util.Collections;

/**
 *   There are N students in a class. Some of them are friends, while some are not. Their friendship is transitive in nature. For example, if A is a direct friend of B, and B is a direct friend of C, then A is an indirect friend of C. And we defined a friend circle is a group of students who are direct or indirect friends.
 *
 * Given a N*N matrix M representing the friend relationship between students in the class. If M[i][j] = 1, then the ith and jth students are direct friends with each other, otherwise not. And you have to output the total number of friend circles among all the students.
 *
 * Example 1:
 *
 * Input:
 * [[1,1,0],
 *  [1,1,0],
 *  [0,0,1]]
 * Output: 2
 * Explanation:The 0th and 1st students are direct friends, so they are in a friend circle.
 * The 2nd student himself is in a friend circle. So return 2.
 *
 * Example 2:
 *
 * Input:
 * [[1,1,0],
 *  [1,1,1],
 *  [0,1,1]]
 * Output: 1
 * Explanation:The 0th and 1st students are direct friends, the 1st and 2nd students are direct friends,
 * so the 0th and 2nd students are indirect friends. All of them are in the same friend circle, so return 1.
 *
 * Note:
 *
 *     N is in range [1,200].
 *     M[i][i] = 1 for all students.
 *     If M[i][j] = 1, then M[j][i] = 1.
 *
 * DFS, Union Find
 *
 * @author Chauncey
 * Runtime: 0 ms, faster than 100.00% of Java online submissions for Friend Circles.
 * Memory Usage: 40.3 MB, less than 59.97% of Java online submissions for Friend Circles.
 */
public class LC_547_Friend_Circles
{
    public int findCircleNum(int[][] M) {
        if (M==null || M.length==0 || M[0]==null || M.length!=M[0].length)
            return 0;
        int n = M.length;
        int[] visited = new int[n];
        int circle=0;
        for (int i=0; i<n; ++i) {
            if (visited[i]==0) {
                circle++;
                visited[i] = circle;
                circleHelper(M, visited, circle, i);
            }
        }
        //System.out.println(Arrays.toString(visited));
        return circle;
    }

    private void circleHelper(int[][] M, int[] visited, int circle, int id) {
        if (id >= M.length)
            return;
        for (int i=0; i<M.length; ++i) {
            if (i==id || M[id][i]==0 || visited[i]>0)
                continue;
            visited[i] = circle;
            circleHelper(M, visited, circle, i);
        }
    }

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();

		LC_547_Friend_Circles solution = new LC_547_Friend_Circles();
		System.out.println(solution.findCircleNum(new int[][]{{1,1,0},{1,1,0},{0,0,1}})); //2
        System.out.println(solution.findCircleNum(new int[][]{{1,0,0,1},{0,1,1,0},{0,1,1,1},{1,0,1,1}})); //2

        System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
	}

}
