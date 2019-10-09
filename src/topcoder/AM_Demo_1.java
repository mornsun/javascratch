/**
 * 
 */
package topcoder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 *
 *
 * @author Chauncey
 */
public class AM_Demo_1
{
	// METHOD SIGNATURE BEGINS, THIS METHOD IS REQUIRED
	public List<Integer> cellCompete(int[] states, int days)
	{
		// WRITE YOUR CODE HERE
		for (int i=0; i<days; ++i) {
			int prev = 0;
			for (int j=0; j<states.length; ++j) {
				int next = j==states.length-1 ? 0 : states[j+1];
				if (prev==next) {
					prev = states[j];
					states[j] = 0;
				} else {
					prev = states[j];
					states[j] = 1;
				}
			}
		}

		List<Integer> res = new ArrayList<>();
		for (int state : states) {
			res.add(state);
		}
		return res;
	}
	// METHOD SIGNATURE ENDS

	// METHOD SIGNATURE BEGINS, THIS METHOD IS REQUIRED
	public int generalizedGCD(int num, int[] arr)
	{
		// WRITE YOUR CODE HERE
		if (arr==null || arr.length==0)
			return 0;
		int max = Math.abs(arr[0]);
		for (int i=1; i<arr.length; ++i) {
			int min = Math.abs(arr[i]);
			while (max>0) {
				if (max<min) {
					int tmp = max;
					max = min;
					min = tmp;
				}
				max %= min;
			}
			max = min;
		}
		return max;
	}
	// METHOD SIGNATURE ENDS

    /**
	 * @param args
	 */
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();

		AM_Demo_1 solution = new AM_Demo_1();
        System.out.println(solution.cellCompete(new int[]{1,0,0,0,0,1,0,0}, 1)); //0 1 0 0 1 0 1 0
		System.out.println(solution.cellCompete(new int[]{1,1,1,0,1,1,1,1}, 2)); //0 0 0 0 0 1 1 0
		System.out.println(solution.generalizedGCD(5, new int[]{2,3,4,5,6})); //1
		System.out.println(solution.generalizedGCD(5, new int[]{2,4,6,8,10})); //2
		System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
	}

}
