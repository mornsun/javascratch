package lintcode;

import java.util.*;

/**
 * Given a boolean 2D matrix, find the number of islands.

Have you met this question in a real interview? Yes
Example
Given graph:

[
  [1, 1, 0, 0, 0],
  [0, 1, 0, 0, 1],
  [0, 0, 0, 1, 1],
  [0, 0, 0, 0, 0],
  [0, 0, 0, 0, 1]
]
return 3.

Note
0 is represented as the sea, 1 is represented as the island. If two 1 is adjacent, we consider them in the same island. We only consider up/down/left/right adjacent.

Related Problems Expand 
Hard Number of Islands II 11 %

 * @author Chauncey
 *
 */
public class NumberofIslands
{
    /**
     * @param grid a boolean 2D matrix
     * @return an integer
     */
    public int numIslands(boolean[][] grid) {
        if (null == grid || grid.length==0 || grid[0].length==0) return 0;
        int res = 0;
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        for (int i=0; i<grid.length; ++i) {
            for (int j=0; j<grid[0].length; ++j) {
                if (grid[i][j] == true && visited[i][j] == false) {
                    dfs(grid, visited, i, j);
                    ++res;
                }
            }
        }
        return res;
    }

    private void dfs(boolean[][] grid, boolean[][] visited, int i, int j) {
        visited[i][j] = true;
        if (i>0 && grid[i-1][j]==true && visited[i-1][j]==false)
            dfs(grid, visited, i-1, j);
        if (i<grid.length-1 && grid[i+1][j]==true && visited[i+1][j]==false)
            dfs(grid, visited, i+1, j);
        if (j>0 && grid[i][j-1]==true && visited[i][j-1]==false)
            dfs(grid, visited, i, j-1);
        if (j<grid[0].length-1 && grid[i][j+1]==true && visited[i][j+1]==false)
            dfs(grid, visited, i, j+1);
    }
    
    /**
     * @param args
     */
    public static void main(String[] args)
    {
        NumberofIslands solution = new NumberofIslands();
        boolean[][] matrix = new boolean[][]{
                  {true, true, false, false, false},
                  {false, true, false, false, true},
                  {false, false, false, true, true},
                  {false, false, false, false, false},
                  {false, false, false, false, true}
        };

        //char[][] matrix = new char[][]{{'1'}};
        System.out.println(solution.numIslands(matrix));
    }

}
