/**
 * 
 */
package topcoder;

import java.util.*;

/**
 * Given a set of points in the xy-plane, determine the minimum area of a rectangle formed from these points, with sides parallel to the x and y axes.
 If there isn't any rectangle, return 0.

 Example 1:
 Input: [[1,1],[1,3],[3,1],[3,3],[2,2]]
 Output: 4
 Example 2:
 Input: [[1,1],[1,3],[3,1],[3,3],[4,1],[4,3]]
 Output: 2

 Note:
 1 <= points.length <= 500
 0 <= points[i][0] <= 40000
 0 <= points[i][1] <= 40000
 All points are distinct.

 Related Topics
 Hash Table

 * @author Chauncey
 * beat %
 */
public class LC_939_Minimum_Area_Rectangle
{
    public int minAreaRect(int[][] points) {
        if (points==null || points.length<=3 || points[0]==null || points[0].length!=2)
            return 0;

        HashMap<Integer, HashSet<Integer>> xMap = new HashMap<>();

        for (int[] point : points) {
            HashSet<Integer> list = xMap.get(point[0]);
            if (list == null) {
                list = new HashSet<>();
                xMap.put(point[0], list);
            }
            list.add(point[1]);
        }

        int min = Integer.MAX_VALUE;
        Object[] entries = xMap.entrySet().toArray();

        for (int i=0; i<entries.length-1; ++i) {
            Map.Entry<Integer, HashSet<Integer>> entry = (Map.Entry<Integer, HashSet<Integer>>)entries[i];
            Object[] list = entry.getValue().toArray();
            int x1 = entry.getKey();

            for (int ky1=0; ky1 < list.length-1; ++ky1) {
                int y1 = (Integer)list[ky1];

                for (int ky2=ky1+1; ky2 < list.length; ++ky2) {
                    int y2 = (Integer)list[ky2];

                    for (int j=i+1; j<entries.length; ++j) {
                        Map.Entry<Integer, HashSet<Integer>> entry1 = (Map.Entry<Integer, HashSet<Integer>>)entries[j];
                        int x2 = entry1.getKey();
                        if (entry1.getValue().contains(y1) && entry1.getValue().contains(y2)) {
                            min = Math.min(min, Math.abs(y2-y1)*Math.abs(x2-x1));
                        }
                    }

                }
            }
        }
        return min==Integer.MAX_VALUE ? 0 : min;
    }

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();

		LC_939_Minimum_Area_Rectangle solution = new LC_939_Minimum_Area_Rectangle();
		System.out.println(solution.minAreaRect(new int[][]{{1,1},{1,3},{3,1},{3,3},{2,2}})); //4
        System.out.println(solution.minAreaRect(new int[][]{{1,1},{1,3},{3,1},{3,3},{4,1},{4,3}})); //2

		System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
	}

}
