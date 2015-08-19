package topcoder;

/**
 * Reverse digits of an integer.

Example1: x = 123, return 321
Example2: x = -123, return -321

click to show spoilers.

Have you thought about this?
Here are some good questions to ask before coding. Bonus points for you if you have already thought through this!

If the integer's last digit is 0, what should the output be? ie, cases such as 10, 100.

Did you notice that the reversed integer might overflow? Assume the input is a 32-bit integer, then the reverse of 1000000003 overflows. How should you handle such cases?

For the purpose of this problem, assume that your function returns 0 when the reversed integer overflows.

Update (2014-11-10):
Test cases had been added to test the overflow behavior.

Hide Tags Math
Hide Similar Problems (E) String to Integer (atoi)

 * @author Chauncey
 *
 */
public class ReverseInteger {

	public static int binary_search(int[] a, int num)
	{
		int lo = 0;
		int hi = a.length-1;

		while (lo < hi) { //<=
			int mid = hi - (hi - lo) / 2; //lo+
			if (num >= a[mid]) { //<=
				lo = mid;
			} else {
				hi = mid - 1;
			}
			System.out.println(lo+":"+a[lo]+" "+hi+":"+a[hi]);
		}
		if (num == a[lo])
			return lo;

		return -1;
	}

    public static int reverse(int x) {
        long res = 0;
        while (x != 0) {
        	res *= 10;
        	res += x%10;
        	x /= 10;
        }
    	if (res > Integer.MAX_VALUE || res<Integer.MIN_VALUE) {
    		return (0x7FFFFFFF);
    	}
        return (int)res;
    }
    
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int[] a = new int[] { 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 55, 55, 55, 55, 55, 67, 71, 73, 79, 83, 89, 97 };
		System.out.println(reverse(-2147483648));
		System.out.println(reverse(-2147483412));
		System.out.println(Integer.MAX_VALUE);
	}

}
