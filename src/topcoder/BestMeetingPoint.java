package topcoder;

import java.util.*;

/**
 * A group of two or more people wants to meet and minimize the total travel distance. You are given a 2D grid of values 0 or 1, where each 1 marks the home of someone in the group. The distance is calculated using Manhattan Distance, where distance(p1, p2) = |p2.x - p1.x| + |p2.y - p1.y|.

For example, given three people living at (0,0), (0,4), and (2,2):

1 - 0 - 0 - 0 - 1
|   |   |   |   |
0 - 0 - 0 - 0 - 0
|   |   |   |   |
0 - 0 - 1 - 0 - 0
The point (0,2) is an ideal meeting point, as the total travel distance of 2+2+2=6 is minimal. So return 6.

Hint:

Try to solve it in one dimension first. How can this solution apply to the two dimension case?

Manhattan Distance

 * @author Chauncey
 *
 */
public class BestMeetingPoint
{
    public int minTotalDistance(int[][] grid) {
    	if (null==grid || grid.length==0 || null==grid[0] || grid[0].length==0) return 0;
    	ArrayList<Integer> ilist = new ArrayList<Integer>();
    	ArrayList<Integer> jlist = new ArrayList<Integer>();
    	for (int i=0; i<grid.length; ++i) {
    		for (int j=0; j<grid[0].length; ++j) {
    			if (grid[i][j] == 1) {
    				ilist.add(i);
    				jlist.add(j);
    			}
    		}
    	}
    	int sum = 0;
    	Collections.sort(jlist);
    	int imedian = ilist.get(ilist.size()/2);
    	int jmedian = jlist.get(jlist.size()/2);
    	System.out.println(imedian+":"+jmedian);
    	for (int i : ilist) {
    		sum += Math.abs(i-imedian);
    	}
    	for (int j : jlist) {
    		sum += Math.abs(j-jmedian);
    	}
    	return sum;
    }
	    
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		BestMeetingPoint solution = new BestMeetingPoint();
		int[][] grid = new int[][]{{1, 0, 0, 0, 1},
				{0, 0, 0, 0, 0},
				{0, 0, 1, 0, 0}};
		System.out.println(solution.minTotalDistance(grid));
	}

}
