/**
 * 
 */
package topcoder;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *  Given a matrix consists of 0 and 1, find the distance of the nearest 0 for each cell.
 *
 * The distance between two adjacent cells is 1.
 *
 * Example 1:
 *
 * Input:
 * [[0,0,0],
 *  [0,1,0],
 *  [0,0,0]]
 *
 * Output:
 * [[0,0,0],
 *  [0,1,0],
 *  [0,0,0]]
 *
 * Example 2:
 *
 * Input:
 * [[0,0,0],
 *  [0,1,0],
 *  [1,1,1]]
 *
 * Output:
 * [[0,0,0],
 *  [0,1,0],
 *  [1,2,1]]
 *
 * Note:
 *
 *     The number of elements of the given matrix will not exceed 10,000.
 *     There are at least one 0 in the given matrix.
 *     The cells are adjacent in only four directions: up, down, left and right.
 *
 * DFS, BFS
 *
 * @author Chauncey
 * Runtime: 0 ms, faster than 100.00% of Java online submissions for Friend Circles.
 * Memory Usage: 40.3 MB, less than 59.97% of Java online submissions for Friend Circles.
 */
public class LC_542_01_Matrix
{
    private final static int[] yDir = new int[]{-1, 0, 1, 0};
    private final static int[] xDir = new int[]{0, -1, 0, 1};

    public int[][] updateMatrix(int[][] matrix) {
        if (matrix==null || matrix.length==0 || matrix[0].length==0)
            return null;

        int m=matrix.length, n=matrix[0].length;
        int[][] res = new int[m][n];
        ArrayList<int[]> curr = new ArrayList<>();
        for (int i=0; i<m; ++i) {
            Arrays.fill(res[i], -1);
            for (int j=0; j<n; ++j) {
                if (matrix[i][j] == 0) {
                    res[i][j] = 0;
                    curr.add(new int[]{i,j});
                }
            }
        }

        int dist = 1;
        while (!curr.isEmpty()) {
            ArrayList<int[]> prev = curr;
            curr = new ArrayList<>();
            int sz = prev.size();
            for (int i=0; i<sz; ++i) {
                int[] pos = prev.get(i);
                for (int j=0; j<yDir.length; ++j) {
                    int y = pos[0]+yDir[j], x = pos[1]+xDir[j];
                    if (y<0 || y>=m || x<0 || x>=n)
                        continue;
                    if (res[y][x] == -1) {
                        res[y][x] = dist;
                        curr.add(new int[]{y,x});
                    }
                }
            }
            dist++;
        }

        return res;
    }

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();

		LC_542_01_Matrix solution = new LC_542_01_Matrix();
		System.out.println(solution.updateMatrix(new int[][]{{0,0,0},
                {0,1,0},
                {0,0,0}}));
        //        {{0,0,0},
        //            {0,1,0},
        //            {0,0,0}}
        System.out.println(solution.updateMatrix(new int[][]{{0,0,0},
                {0,1,0},
                {1,1,1}})); //2

        //[[0,0,0],
        // [0,1,0],
        // [1,2,1]]
        System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
	}

}
