/**
 * 
 */
package topcoder;

import java.util.*;

/**
 * 	Given two numbers arr1 and arr2 in base -2, return the result of adding them together.
 *
 * Each number is given in array format:  as an array of 0s and 1s, from most significant bit to least significant bit.  For example, arr = [1,1,0,1] represents the number (-2)^3 + (-2)^2 + (-2)^0 = -3.  A number arr in array format is also guaranteed to have no leading zeros: either arr == [0] or arr[0] == 1.
 *
 * Return the result of adding arr1 and arr2 in the same format: as an array of 0s and 1s with no leading zeros.
 *
 * Example 1:
 *
 * Input: arr1 = [1,1,1,1,1], arr2 = [1,0,1]
 * Output: [1,0,0,0,0]
 * Explanation: arr1 represents 11, arr2 represents 5, the output represents 16.
 *
 * Note:
 *
 * 1 <= arr1.length <= 1000
 * 1 <= arr2.length <= 1000
 * arr1 and arr2 have no leading zeros
 * arr1[i] is 0 or 1
 * arr2[i] is 0 or 1
 *
 * @author Chauncey
 * Runtime: 2 ms, faster than 67.97% of Java online submissions for Adding Two Negabinary Numbers.
 * Memory Usage: 38.3 MB, less than 100.00% of Java online submissions for Adding Two Negabinary Numbers.
 */
public class LC_1073_Adding_Two_Negabinary_Numbers
{
	public int[] addNegabinary(int[] arr1, int[] arr2) {

		if (arr1==null || arr1.length==0) return arr2;
		if (arr2==null || arr2.length==0) return arr1;

		LinkedList<Integer> res = new LinkedList<>();
		int carry = 0;
		for (int i=1; i<=arr1.length || i<=arr2.length; ++i) {
			int v1 = i<=arr1.length ? arr1[arr1.length-i] : 0;
			int v2 = i<=arr2.length ? arr2[arr2.length-i] : 0;
			if (v1==v2) {
				if (v1==0) {
					switch(carry) {
						case 1:
							res.add(1);
							carry = 0;
							break;
						case -1:
							res.add(1);
							carry = 1;
							break;
						default:
							res.add(0);
							carry = 0;
							break;
					}
				} else if(v1==1) {
					switch(carry) {
						case 1:
							res.add(1);
							carry = -1;
							break;
						case -1:
							res.add(1);
							carry = 0;
							break;
						default:
							res.add(0);
							carry = -1;
							break;
					}
				}
			} else {
				switch(carry) {
					case 1:
						res.add(0);
						carry = -1;
						break;
					case -1:
						res.add(0);
						carry = 0;
						break;
					default:
						res.add(1);
						carry = 0;
						break;
				}
			}
		}
		switch(carry) {
			case 1:
				res.add(1);
				break;
			case -1:
				res.add(1);
				res.add(1);
				break;
			default:
				break;
		}

		while (res.size()>1 && res.peekLast()==0)
			res.pollLast();
		int n = res.size();
		int[] r = new int[n];
		for (int i=0; i<n; ++i) {
			r[i] = res.pollLast();
		}
		return r;
	}

    /**
	 * @param args
	 */
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();

		LC_1073_Adding_Two_Negabinary_Numbers solution = new LC_1073_Adding_Two_Negabinary_Numbers();
        System.out.println(Arrays.toString(solution.addNegabinary(new int[]{1,1,1,1,1}, new int[]{1,0,1}))); //10000
		System.out.println(Arrays.toString(solution.addNegabinary(new int[]{1}, new int[]{1,1}))); //0
		System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
	}

}
