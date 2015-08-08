/**
 * 
 */
package topcoder;

import java.util.*;

/**
 * @author cherry
 *
 */
public class SpiralMatrix
{
    public static List<Integer> spiralOrder(int[][] matrix) {
    	List<Integer> res = new LinkedList<Integer>();
    	if (matrix == null || matrix.length == 0) return res;
    	int beginX = 0, endX = matrix[0].length-1; //internal margin
    	int beginY = 0, endY = matrix.length-1;
    	while (true) {
	    	for (int i = beginX; i<=endX; ++i) {
	    		res.add(matrix[beginY][i]);
	    	}
	    	if (++beginY > endY) break;
	    	for (int i = beginY; i<=endY; ++i) {
	    		res.add(matrix[i][endX]);
	    	}
	    	if (--endX < beginX) break;
	    	for (int i = endX; i>=beginX; --i) {
	    		res.add(matrix[endY][i]);
	    	}
	    	if (--endY < beginY) break;
	    	for (int i = endY; i>=beginY; --i) {
	    		res.add(matrix[i][beginX]);
	    	}
	    	if (++beginX > endX) break;
    	}
    	return res;
    }
	    
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		int[][] matrix = new int[][]{
				{ 1, 2, 3 },
				{ 4, 5, 6 },
				{ 7, 8, 9 },
				{ 10, 11, 12 },
				{ 13, 14, 15 }
		};
		List<Integer> list = spiralOrder(matrix);
    	System.out.println(list);
	}

}
