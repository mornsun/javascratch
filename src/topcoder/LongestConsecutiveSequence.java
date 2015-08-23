package topcoder;

import java.util.*;

/**
 * Given an unsorted array of integers, find the length of the longest consecutive elements sequence.

For example,
Given [100, 4, 200, 1, 3, 2],
The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.

Your algorithm should run in O(n) complexity.

Hide Tags Array


 * @author Chauncey
 *
 */
public class LongestConsecutiveSequence
{
    public int longestConsecutive(int[] nums) {
    	if (null == nums || nums.length == 0) return 0;
    	HashMap<Integer, Boolean> hm = new HashMap<Integer, Boolean>((int)(nums.length/0.75+1));
    	for (int num : nums) {
    		hm.put(num, false);
    	}
    	int max = 0;
    	for (int num : nums) {
    		if (hm.get(num) == true) continue;
    		int lo = num-1;
    		while (hm.get(lo) != null) {
    			hm.put(lo--, true);
    		}
    		while (hm.get(++num) != null) {
    			hm.put(num, true);
    		}
    		max = Math.max(max, num-lo-1);
    	}
        return max;
    }
	    
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		LongestConsecutiveSequence solution = new LongestConsecutiveSequence();
		
		System.out.println(solution.longestConsecutive(new int[]{100, 4, 200, 1, 3, 2}));
	}

}
