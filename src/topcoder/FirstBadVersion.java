package topcoder;

import java.util.*;

/**
 * You are a product manager and currently leading a team to develop a new product. Unfortunately, the latest version of your product fails the quality check. Since each version is developed based on the previous version, all the versions after a bad version are also bad.

Suppose you have n versions [1, 2, ..., n] and you want to find out the first bad one, which causes all the following ones to be bad.

You are given an API bool isBadVersion(version) which will return whether version is bad. Implement a function to find the first bad version. You should minimize the number of calls to the API.

Credits:
Special thanks to @jianchao.li.fighter for adding this problem and creating all test cases.

Hide Tags Binary Search
Hide Similar Problems (M) Search for a Range (M) Search Insert Position

 * @author Chauncey
 *
 */
public class FirstBadVersion
{
	boolean isBadVersion(int version) {
		if (version>=88) return true;
		return false;
	}
    public int firstBadVersion(int n) {
    	if (n<1) return 0;
        int lo = 1;
        int hi = n;
        while (lo<hi) {
        	int m = lo + (hi-lo>>1);
        	if (isBadVersion(m)) {
        		hi = m;
        	} else {
        		lo = m + 1;
        	}
        }
        return lo;
    }
	    
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		FirstBadVersion solution = new FirstBadVersion();
		for (int i=100; i<120; ++i) {
			System.out.println(i+":"+solution.firstBadVersion(i));
		}
	}

}
