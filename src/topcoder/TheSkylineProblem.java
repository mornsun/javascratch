package topcoder;

import java.util.*;

/**
 * A city's skyline is the outer contour of the silhouette formed by all the buildings in that city when viewed from a distance. Now suppose you are given the locations and height of all the buildings as shown on a cityscape photo (Figure A), write a program to output the skyline formed by these buildings collectively (Figure B).

 Buildings  Skyline Contour
The geometric information of each building is represented by a triplet of integers [Li, Ri, Hi], where Li and Ri are the x coordinates of the left and right edge of the ith building, respectively, and Hi is its height. It is guaranteed that 0 ≤ Li, Ri ≤ INT_MAX, 0 < Hi ≤ INT_MAX, and Ri - Li > 0. You may assume all buildings are perfect rectangles grounded on an absolutely flat surface at height 0.

For instance, the dimensions of all buildings in Figure A are recorded as: [ [2 9 10], [3 7 15], [5 12 12], [15 20 10], [19 24 8] ] .

The output is a list of "key points" (red dots in Figure B) in the format of [ [x1,y1], [x2, y2], [x3, y3], ... ] that uniquely defines a skyline. A key point is the left endpoint of a horizontal line segment. Note that the last key point, where the rightmost building ends, is merely used to mark the termination of the skyline, and always has zero height. Also, the ground in between any two adjacent buildings should be considered part of the skyline contour.

For instance, the skyline in Figure B should be represented as:[ [2 10], [3 15], [7 12], [12 0], [15 10], [20 8], [24, 0] ].

Notes:

The number of buildings in any input list is guaranteed to be in the range [0, 10000].
The input list is already sorted in ascending order by the left x position Li.
The output list must be sorted by the x position.
There must be no consecutive horizontal lines of equal height in the output skyline. For instance, [...[2 3], [4 5], [7 5], [11 5], [12 7]...] is not acceptable; the three lines of height 5 should be merged into one in the final output as such: [...[2 3], [4 5], [12 7], ...]
Credits:
Special thanks to @stellari for adding this problem, creating these two awesome images and all test cases.

Hide Tags Divide and Conquer Heap

 * @author Chauncey
 *
 */
public class TheSkylineProblem
{
    public static List<List<Integer>> threeSum(int[] nums) {
    	List<List<Integer>> result = new LinkedList<List<Integer>>();
    	if (nums.length < 3)
    		return result;
    	Arrays.sort(nums);
    	int target = 0;
    	for (int i=0; i < nums.length-2; ++i) {
    		if (i>0 && nums[i]==nums[i-1]) {
    			continue;
    		}
	    	int j = i+1;
	    	int k = nums.length-1;
	    	while (j < k) {
	    		//System.out.println(nums[i]+":"+ nums[j] +":"+ nums[k]);
	    		if (j != i+1 && nums[j]==nums[j-1]) {
	    			++j;
	    			continue;
	    		}
	    		if (k != nums.length-1 && nums[k]==nums[k+1]) {
	    			--k;
	    			continue;
	    		}
		    	if (nums[i] + nums[j] + nums[k] < target) {
		    		++j;
		    	} else if (nums[i] + nums[j] + nums[k] > target) {
		    		--k;
		    	} else {
		    		int[] arr = {nums[i], nums[j], nums[k]};
		    		List<Integer> list = new LinkedList<Integer>();
		    		for (int n : arr) {
		    			list.add(n);
		    		}
		    		result.add(list);
		    		++j;
		    		--k;
		    	}
	    	}
    	}
    	return result;
    }
	    
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		int[] nums = new int[]{-2,0,0,2,2};
		List<List<Integer>> list = threeSum(nums);
		for (List<Integer> l : list) {
			System.out.println(l);
		}
	}

}
