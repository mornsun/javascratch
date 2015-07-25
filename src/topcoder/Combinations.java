/**
 * 
 */
package topcoder;

import java.util.*;

/**
 * @author cherry
 *
 */
public class Combinations
{
	private final static boolean nextCombination(Integer[] nums, int n) {
		/*for (int e: nums) {
			System.out.print(e);
		}
		System.out.println();*/
		int[] index = new int[n+1];
		int max = 1;
		for (int k=0; k<nums.length; ++k) {
			index[nums[k]] = k+1;
			if (nums[k] > max) max = nums[k];
		}
		int i = max < n-1 ? max : n-1;
		for (; i>0; --i) {
			if(index[i] != 0 && index[i+1] == 0)
				break;
		}
		if (i==0) {
			return false;
		}
		int num = ++nums[index[i]-1];
		for (int k=index[i]; k<nums.length; ++k) {
			nums[k] = ++num;
		}
		return true;
	}
    public static List<List<Integer>> combine(int n, int k) {
    	if (k<1 || n<k) return null;
        List<List<Integer>> res = new LinkedList<List<Integer>>();
        Integer[] comb = new Integer[k];
        for (int i=0; i<k; ++i) {
        	comb[i] = i+1;
        }
        List<Integer> one_res = new ArrayList<Integer>(Arrays.asList(comb));//Arrays.asList(comb);
        res.add(one_res);
        while (nextCombination(comb, n)) {
        	one_res = new ArrayList<Integer>(Arrays.asList(comb));
            res.add(one_res);
        }
        return res;
    }
	    
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		List<List<Integer>> list = combine(4,3);
		System.out.println(list);
		
	}

}
