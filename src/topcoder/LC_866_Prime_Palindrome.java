/**
 * 
 */
package topcoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

/**
 * Find the smallest prime palindrome greater than or equal to N.
 Recall that a number is prime if it's only divisors are 1 and itself, and it is greater than 1.
 For example, 2,3,5,7,11 and 13 are primes.
 Recall that a number is a palindrome if it reads the same from left to right as it does from right to left.
 For example, 12321 is a palindrome.

 Example 1:
 Input: 6
 Output: 7

 Example 2:
 Input: 8
 Output: 11

 Example 3:
 Input: 13
 Output: 101

 Note:
 1 <= N <= 10^8
 The answer is guaranteed to exist and be less than 2 * 10^8.

 Related Topics
 Math

 * @author Chauncey
 * beat 22%
 */
public class LC_866_Prime_Palindrome
{
    private static ArrayList<Integer> primeList = null;

    public int primePalindrome(int N) {
        if (primeList == null) {
            primeList = new ArrayList<>();
            for (int k=1; k<3000; ++k) {
                int test = k;
                int num = k;
                while (test > 0) {
                    num *= 10;
                    num += test % 10;
                    test /= 10;
                }
                if (isPrime(num))
                    primeList.add(num);
            }
            for (int k=1; k<30000; ++k) {
                int test = k/10;
                int num = k;
                while (test>0) {
                    num *= 10;
                    num += test % 10;
                    test /= 10;
                }
                if (isPrime(num))
                    primeList.add(num);
            }
            Collections.sort(primeList);
        }

        int lo = 1;
        int hi = primeList.size()-1;
        while (lo<hi) {
            int m = lo + (hi-lo>>1);
            if (primeList.get(m)<N)
                lo = m+1;
            else
                hi = m;
        }
        return primeList.get(lo);
    }

    private static boolean isPrime(int n) {
        for (int i=2; i*i<=n; ++i) {
            if (n%i==0)
                return false;
        }
        return true;
    }

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();

		LC_866_Prime_Palindrome solution = new LC_866_Prime_Palindrome();
		System.out.println(solution.primePalindrome(6)); //7
        System.out.println(solution.primePalindrome(8)); //11
        System.out.println(solution.primePalindrome(13)); //101
        System.out.println(solution.primePalindrome(1)); //2
        System.out.println(solution.primePalindrome(3073704)); //3075703

		System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
	}

}
