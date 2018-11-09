/**
 * 
 */
package topcoder;

import java.util.*;

/**
 * Given an array nums, we call (i, j) an important reverse pair if i < j and nums[i] > 2*nums[j].

You need to return the number of important reverse pairs in the given array.

Example1:

Input: [1,3,2,3,1]
Output: 2
Example2:

Input: [2,4,3,5,1]
Output: 3
Note:
The length of the given array will not exceed 50,000.
All the numbers in the input array are in the range of 32-bit integer.

 Related Topic
 Divide and Conquer, Binary Indexed Tree, Segment Tree, Binary Search Tree

 * @author Chauncey
 *
 */
public class LC_493_Reverse_Pairs
{
    public int reversePairs(int[] nums) {
        
    }

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();

		LC_493_Reverse_Pairs solution = new LC_493_Reverse_Pairs();
		System.out.println(solution.superEggDrop(1, 2)); //2
        System.out.println(solution.superEggDrop(2, 6)); //3
        System.out.println(solution.superEggDrop(3, 14)); //4
        System.out.println(solution.superEggDrop(1, 1)); //1
        System.out.println(solution.superEggDrop(1, 3)); //3
        System.out.println(solution.superEggDrop(2, 1)); //1
        System.out.println(solution.superEggDrop(2, 7)); //4
        System.out.println(solution.superEggDrop(6, 10000)); //16
		
		System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
	}

}
