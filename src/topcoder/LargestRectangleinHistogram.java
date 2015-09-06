package topcoder;

import java.util.*;

/**
 * Given n non-negative integers representing the histogram's bar height where the width of each bar is 1, find the area of largest rectangle in the histogram.


Above is a histogram where width of each bar is 1, given height = [2,1,5,6,2,3].


The largest rectangle is shown in the shaded area, which has area = 10 unit.

For example,
Given height = [2,1,5,6,2,3],
return 10.

Hide Tags Array Stack
Hide Similar Problems (H) Maximal Rectangle

 * @author Chauncey
 *
 */
public class LargestRectangleinHistogram
{
    public int largestRectangleArea(int[] height) {
    	if (height == null || height.length==0) return 0;
    	LinkedList<Integer> stack = new LinkedList<Integer>();
    	int max = 0;
    	for (int i=0; i<height.length; ) {
    		Integer top = stack.peek();
    		if (top==null || height[i] > height[top]) {
    			stack.push(i);
    			++i;
    		} else {
    			stack.pop();
    			Integer prev = stack.peek();
    			int area = height[top]*(prev==null ? i : i-prev-1);
    			//System.out.println(height[top]+":"+(prev==null ? i : i-prev-1));
    			if (max < area) {
    				max = area;
    			}
    		}
    	}
    	while (!stack.isEmpty()) {
			int top = stack.pop();
			Integer prev = stack.peek();
			int area = height[top]*(prev==null ? height.length : height.length-prev-1);
			//System.out.println(height[top]+"-"+(prev==null ? height.length : height.length-prev-1));
			if (max < area) {
				max = area;
			}
    	}
    	return max;
    }
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		LargestRectangleinHistogram solution = new LargestRectangleinHistogram();
		System.out.println(solution.largestRectangleArea(new int[]{2,1,5,6,2,3}));
	}

}
