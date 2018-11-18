/**
 * 
 */
package topcoder;

import java.util.Arrays;
import java.util.HashSet;

/**
 * Starting with a positive integer N, we reorder the digits in any order (including the original order) such that the leading digit is not zero.
 Return true if and only if we can do this in a way such that the resulting number is a power of 2.


 Example 1:
 Input: 1
 Output: true

 Example 2:
 Input: 10
 Output: false

 Example 3:
 Input: 16
 Output: true

 Example 4:
 Input: 24
 Output: false

 Example 5:
 Input: 46
 Output: true

 Note:
 1 <= N <= 10^9

 Related Topics
 Math

 * @author Chauncey
 * beat 10%
 */
public class LC_869_Reordered_Power_of_2
{
    public boolean reorderedPowerOf2(int N) {
        if (N<=0)
            return false;

        int cnt=0;
        int[] digits = new int[10];
        while (N>0) {
            digits[cnt++] = N % 10;
            N /= 10;
        }

        return searchPermutation(digits, cnt, 0, 0);
    }

    //private static HashSet<Integer> power2 = new HashSet<Integer>(Arrays.asList(2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048, 4096, 8192, 16384, 32768));
    private static HashSet<Integer> power2 = new HashSet<Integer>(Arrays.asList(0x01, 0x02, 0x04, 0x08, 0x10, 0x20, 0x40, 0x80, 0x100, 0x200, 0x400, 0x800, 0x1000, 0x2000, 0x4000, 0x8000, 0x10000, 0x20000, 0x40000, 0x80000, 0x100000, 0x200000, 0x400000, 0x800000, 0x1000000, 0x2000000, 0x4000000, 0x8000000, 0x10000000, 0x20000000, 0x40000000, 0x80000000));

    private static boolean searchPermutation(int[] digits, int cnt, int idx, int num) {
        if (idx == cnt-1) {
            num = num * 10 + digits[idx];
            //System.out.println(idx+":"+cnt+":"+num);
            while(num>0 && (num&1)==0) {
                num >>= 1;
            }
            return num == 1 ? true : false;
        }

        for (int i=idx; i<cnt; ++i) {
            if (idx == 0 && digits[i] == 0)
                continue;
            swap(digits, idx, i);
            int curr = num * 10 + digits[idx];
            if (searchPermutation(digits, cnt, idx+1, curr))
                return true;
            swap(digits, idx, i);
        }

        return false;
    }

    public static void swap(int[] digits, int i, int j) {
        if (i==j)
            return;
        int t = digits[i];
        digits[i] = digits[j];
        digits[j] = t;
    }

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();

		LC_869_Reordered_Power_of_2 solution = new LC_869_Reordered_Power_of_2();
		System.out.println(solution.reorderedPowerOf2(1)); //T
        System.out.println(solution.reorderedPowerOf2(10)); //F
        System.out.println(solution.reorderedPowerOf2(16)); //T
        System.out.println(solution.reorderedPowerOf2(24)); //F
        System.out.println(solution.reorderedPowerOf2(46)); //T

		System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
	}

}
