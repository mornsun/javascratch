package topcoder;

import java.util.*;

/**
 * Given a list of non negative integers, arrange them such that they form the largest number.

For example, given [3, 30, 34, 5, 9], the largest formed number is 9534330.

Note: The result may be very large, so you need to return a string instead of an integer.

Credits:
Special thanks to @ts for adding this problem and creating all test cases.

Hide Tags Sort

 * @author Chauncey
 *
 */
public class LargestNumber
{
	private Comparator<String> _string_comp =  new Comparator<String>(){ 
        public int compare(String s1, String s2) {
        	return (s2+s1).compareTo(s1+s2);
        }
    };
    public String largestNumber(int[] nums) {
        if (nums == null || nums.length == 0) return "";
        String[] strs = new String[nums.length];
        int i = 1;
        for (; i<nums.length; ++i)
        	if (nums[i] != nums[i-1]) break;
        if (i == nums.length && nums[0] == 0) return "0";
        for (i=0; i<nums.length; ++i) strs[i] = String.valueOf(nums[i]);
        Arrays.sort(strs, _string_comp);
        StringBuilder sb = new StringBuilder();
        for (i=0; i<nums.length; ++i) sb.append(strs[i]);
        return sb.toString();
    }
    
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		LargestNumber solution = new LargestNumber();
		
		//System.out.println(solution.largestNumber(new int[]{3, 30, 34, 5, 9}));
		System.out.println(solution.largestNumber(new int[]{1212,121}));
		System.out.println(solution.largestNumber(new int[]{232123,2321}));
		System.out.println(solution.largestNumber(new int[]{0,0}));
	}

}
