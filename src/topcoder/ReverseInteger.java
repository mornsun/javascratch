/**
 * Given an array of non-negative integers, you are initially positioned at the first index of the array.
 * Each element in the array represents your maximum jump length at that position.
 * Determine if you are able to reach the last index.
 * For example:
 * A = [2,3,1,1,4], return true.
 * A = [3,2,1,0,4], return false.
 */
package topcoder;

/**
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
