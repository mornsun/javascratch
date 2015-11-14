package topcoder;

import java.util.*;

/**
 * An image is represented by a binary matrix with 0 as a white pixel and 1 as a black pixel. The black pixels are connected, i.e., there is only one black region. Pixels are connected horizontally and vertically. Given the location (x, y) of one of the black pixels, return the area of the smallest (axis-aligned) rectangle that encloses all black pixels.

For example, given the following image:

[
  "0010",
  "0110",
  "0100"
]
and x = 0, y = 2,
Return 6.

 * @author Chauncey
 *
 */
public class SmallestRectangleEnclosingBlackPixels
{
	/**BFS approach
	 * Ref. BinarySearch: https://leetcode.com/discuss/68246/c-java-python-binary-search-solution-with-explanation
	 * @param image
	 * @param x
	 * @param y
	 * @return
	 */
	public int minArea(char[][] image, int x, int y) {
		if (null==image || image.length==0 || image[0].length==0
				|| y<0 || y>=image.length || x<0 || x>=image[0].length) return -1;
		int[][] rect = new int[2][2];
		rect[0][0] = x; rect[0][1] = y; rect[1][0] = x; rect[1][1] = y;
		dfs(image, x, y, rect);
		return (rect[1][0]-rect[0][0]+1) * (rect[1][1]-rect[0][1]+1);
	}
	
	private void dfs(char[][] image, int x, int y, int[][] rect) {
		image[x][y] = '-';
		if (x-1>=0 && image[x-1][y]=='1') {
			rect[0][0] = Math.min(rect[0][0], x-1);
			dfs(image, x-1, y, rect);
		}
		if (y-1>=0 && image[x][y-1]=='1') {
			rect[0][1] = Math.min(rect[0][1], y-1);
			dfs(image, x, y-1, rect);
		}
		if (x+1<image.length && image[x+1][y]=='1') {
			rect[1][0] = Math.max(rect[1][0], x+1);
			dfs(image, x+1, y, rect);
		}
		if (y+1<image[0].length && image[x][y+1]=='1') {
			rect[1][1] = Math.max(rect[1][1], y+1);
			dfs(image, x, y+1, rect);
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		char[][] matrix = new char[][] {
				  {'0','0','1','0'},
				  {'0','1','1','0'},
				  {'0','1','0','0'}};
		SmallestRectangleEnclosingBlackPixels solution = new SmallestRectangleEnclosingBlackPixels();
		//8
		System.out.println(solution.minArea(matrix, 0, 2));
	}
}
