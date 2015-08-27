package topcoder;

import java.util.*;

/**
 * Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

Example 1:

11110
11010
11000
00000
Answer: 1

Example 2:

11000
11000
00100
00011
Answer: 3

Credits:
Special thanks to @mithmatt for adding this problem and creating all test cases.

Hide Tags Depth-first Search Breadth-first Search Union Find
Hide Similar Problems (M) Surrounded Regions

 * @author Chauncey
 *
 */
public class NumberofIslands
{
    public int numIslands(char[][] grid) {
    	if (null == grid || grid.length==0 || grid[0].length==0) return 0;
        int res = 0;
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        for (int i=0; i<grid.length; ++i) {
        	for (int j=0; j<grid[0].length; ++j) {
        		if (grid[i][j] == '1' && visited[i][j] == false) {
        			dfs(grid, visited, i, j);
        			++res;
        		}
        	}
        }
        return res;
    }

	private void dfs(char[][] grid, boolean[][] visited, int i, int j) {
		visited[i][j] = true;
		if (i>0 && grid[i-1][j]=='1' && visited[i-1][j]==false)
			dfs(grid, visited, i-1, j);
		if (i<grid.length-1 && grid[i+1][j]=='1' && visited[i+1][j]==false)
			dfs(grid, visited, i+1, j);
		if (j>0 && grid[i][j-1]=='1' && visited[i][j-1]==false)
			dfs(grid, visited, i, j-1);
		if (j<grid[0].length-1 && grid[i][j+1]=='1' && visited[i][j+1]==false)
			dfs(grid, visited, i, j+1);
	}
    
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		NumberofIslands solution = new NumberofIslands();
		char[][] matrix = new char[][]{
				{'1', '1', '0', '0', '0'},
				{'1', '1', '0', '0', '0'},
				{'0', '0', '1', '0', '0'},
				{'0', '0', '0', '1', '1'}
		};

		//char[][] matrix = new char[][]{{'1'}};
		System.out.println(solution.numIslands(matrix));
	}

}
