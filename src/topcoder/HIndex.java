package topcoder;

import java.util.*;

/**
 * Given an array of citations (each citation is a non-negative integer) of a researcher, write a function to compute the researcher's h-index.

According to the definition of h-index on Wikipedia: "A scientist has index h if h of his/her N papers have at least h citations each, and the other N − h papers have no more than h citations each."

For example, given citations = [3, 0, 6, 1, 5], which means the researcher has 5 papers in total and each of them had received 3, 0, 6, 1, 5 citations respectively. Since the researcher has 3 papers with at least 3 citations each and the remaining two with no more than 3 citations each, his h-index is 3.

Note: If there are several possible values for h, the maximum one is taken as the h-index.

Hint:

An easy approach is to sort the array first.
What are the possible values of h-index?
A faster approach is to use extra space.
Credits:
Special thanks to @jianchao.li.fighter for adding this problem and creating all test cases.

Hide Tags Hash Table Sort
Hide Similar Problems (M) H-Index II

 * @author Chauncey
 *
 */
public class HIndex
{
    public int hIndex(int[] citations) {
        if (citations==null || citations.length==0) return 0;
        int[] cnt = new int[citations.length+1];
        for (int citation : citations) {
        	if (citation == 0) continue;
        	if (citation > citations.length)
        		++cnt[citations.length];
        	else
        		++cnt[citation];
        }
        int sum = 0;
        for (int i=cnt.length-1; i!=0; --i) {
        	sum += cnt[i];
        	if (sum >= i)
        		return i;
        }
        return 0;
    }
    public int hIndex1(int[] citations) {
        if (citations==null || citations.length==0) return 0;
        Arrays.sort(citations);
        int lo = 0;
        int hi = citations.length-1;
        if (citations[hi]<1) return 0;
        while (lo<hi) {
        	int m = lo+(hi-lo>>1);
        	if (citations[m] >= citations.length - m) {
        		hi = m;
        	} else {
        		lo = m + 1;
        	}
        }
        return citations.length - lo;
    }
	    
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		HIndex solution = new HIndex();
		System.out.println(solution.hIndex1(new int[]{2, 0, 6, 1, 5}));
		System.out.println(solution.hIndex(new int[]{2, 0, 6, 1, 5}));
		System.out.println(solution.hIndex1(new int[]{3, 0, 6, 1, 5}));
		System.out.println(solution.hIndex(new int[]{3, 0, 6, 1, 5}));
		System.out.println(solution.hIndex(new int[]{0, 0}));
	}

}
