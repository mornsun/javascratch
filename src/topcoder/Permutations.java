/**
 * 
 */
package topcoder;

import java.util.*;

/**
 * @author cherry
 *
 */
public class Permutations
{
    public final static int permutation_num(int n, int r)
    {
        int res = 1;
        for (int i=n-r+1; i<=n; ++i) {
            res *= i;
        }
        return res;
    }
    
    public final static List<Integer> getPermutation(int[] nums, int k) {
        Integer[] res = new Integer[nums.length];
        ArrayList<Integer> vac = new ArrayList<Integer>(nums.length);
        for (int i=0; i<nums.length; ++i) {
            vac.add(nums[i]);
        }
        for (int i=nums.length-1; i>=0; --i) {
            int pn = permutation_num(i,i);
            int b = k/pn;
            k = k%pn;
            res[nums.length-i-1] = vac.get(b);
            vac.remove(b);
        }
        return Arrays.asList(res);
    }
    public static List<List<Integer>> permute(int[] nums) {
    	if (null == nums || nums.length == 0) return null;
    	int size = permutation_num(nums.length, nums.length);
    	List<List<Integer>> res = new LinkedList<List<Integer>>();
    	for (int i=0; i<size; ++i) {
    		res.add(getPermutation(nums, i));
    	}
    	return res;
    }
    
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		int[] nums = new int[]{1,2,3};
		System.out.println(permute(nums));
	}

}
