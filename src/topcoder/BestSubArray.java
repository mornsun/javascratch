/**
 * 
 */
package topcoder;

import java.util.Arrays;

/**
 * @author Chauncey
 */
public class BestSubArray
{
    public int bestSubArrayBest(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int max = Integer.MIN_VALUE;
        int row = matrix.length;
        int col = matrix[0].length;

        for (int top=0; top<row; ++top) {
            int[] sums = new int[col];
            for (int bottom=top; bottom<row; ++bottom) {
                int cur_sum = 0;
                for (int x=0; x<col; ++x) {
                    sums[x] += matrix[bottom][x];
                    cur_sum += sums[x];
                    if (cur_sum >= max) {
                        max = cur_sum;
                    }
                    if (cur_sum < 0) {
                        cur_sum = 0;
                    }
                }
            }
        }

        return max;
    }

    public int bestSubArray(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int height = matrix.length;
        int width = matrix[0].length;
        int[][] dp = new int[height+1][width+1];
        for (int i=1; i<=height; ++i) {
            int sum = 0;
            for (int j=1; j<=width; ++j) {
                sum += matrix[i-1][j-1];
                dp[i][j] = dp[i-1][j] + sum;
            }
        }
        int max = Integer.MIN_VALUE;
        for (int rows=1; rows<=height; ++rows) {
            for (int i=rows; i<=height; ++i) {
                int sum = 0;
                for (int j=1; j<=width; ++j) {
                    if (sum<0)
                        sum = 0;
                    sum += dp[i][j] - dp[i-rows][j] - dp[i][j-1] + dp[i-rows][j-1];
                    if (sum>max) {
                        max = sum;
                    }
                }
            }
        }
        return max;
    }

    public int bruteForceBestSubArray(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int height = matrix.length;
        int width = matrix[0].length;
        int max = Integer.MIN_VALUE;
        for (int row=1; row<=height; ++row) {
            for (int col=1; col<=width; ++col) {
                for (int i = row; i <= height; ++i) {
                    for (int j = col; j <= width; ++j) {
                        int sum = 0;
                        for (int x=i-row; x<i; ++x)
                            for (int y = j-col; y<j; ++y) {
                                sum += matrix[x][y];
                        }
                        if (sum>max) {
                            max = sum;
                        }
                    }
                }
            }
        }
        return max;
    }

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		BestSubArray solution = new BestSubArray();
        System.out.println(solution.bruteForceBestSubArray(new int[][]{{0,0,0},{0,1,2},{0,3,0}}));//6
        System.out.println(solution.bruteForceBestSubArray(new int[][]{{0,0,0},{0,0,0},{0,1,2}}));//3
        System.out.println(solution.bruteForceBestSubArray(new int[][]{{100,0},{0,0}}));//100
        System.out.println(solution.bruteForceBestSubArray(new int[][]{{10,0,0},{10,0,0},{-10,0,0}}));//20
        System.out.println(solution.bruteForceBestSubArray(new int[][]{{-10,-20,-30},{-10,-20,-30},{-10,-20,-30}}));//-10

		System.out.println(solution.bestSubArray(new int[][]{{0,0,0},{0,1,2},{0,3,0}}));//6
        System.out.println(solution.bestSubArray(new int[][]{{0,0,0},{0,0,0},{0,1,2}}));//3
        System.out.println(solution.bestSubArray(new int[][]{{100,0},{0,0}}));//100
        System.out.println(solution.bestSubArray(new int[][]{{10,0,0},{10,0,0},{-10,0,0}}));//20
        System.out.println(solution.bestSubArray(new int[][]{{-10,-20,-30},{-10,-20,-30},{-10,-20,-30}}));//-10

        System.out.println(solution.bestSubArrayBest(new int[][]{{0,0,0},{0,1,2},{0,3,0}}));//6
        System.out.println(solution.bestSubArrayBest(new int[][]{{0,0,0},{0,0,0},{0,1,2}}));//3
        System.out.println(solution.bestSubArrayBest(new int[][]{{100,0},{0,0}}));//100
        System.out.println(solution.bestSubArrayBest(new int[][]{{10,0,0},{10,0,0},{-10,0,0}}));//20
        System.out.println(solution.bestSubArrayBest(new int[][]{{-10,-20,-30},{-10,-20,-30},{-10,-20,-30}}));//-10
	}

}
