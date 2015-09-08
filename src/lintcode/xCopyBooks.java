package lintcode;

import java.util.*;

import topcoder.BinarySearchTreeIterator.TreeNode;
import topcoder.LinkedListCycle.ListNode;

/**
 * Given an array A of integer with size of n( means n books and number of pages of each book) and k people to copy the book. You must distribute the continuous id books to one people to copy. (You can give book A[1],A[2] to one people, but you cannot give book A[1], A[3] to one people, because book A[1] and A[3] is not continuous.) Each person have can copy one page per minute. Return the number of smallest minutes need to copy all the books.

Have you met this question in a real interview? Yes
Example
Given array A = [3,2,4], k = 2.

Return 5( First person spends 5 minutes to copy book 1 and book 2 and second person spends 4 minutes to copy book 3. )

Challenge
Could you do this in O(n*k) time ?

 * @author Chauncey
 *
 */
public class xCopyBooks
{
    /**
     * Dynamic programming
     * @param pages: an array of integers
     * @param k: an integer
     * @return: an integer
     */
    public int copyBooks(int[] pages, int k) {
    	if (pages == null || pages.length==0 || k==0) return 0;
    	if (k > pages.length) k = pages.length;
    	int[] f0 = new int[pages.length];
    	int[] fprev = new int[pages.length];
    	int[] fcurr = new int[pages.length];
    	f0[0] = pages[0];
    	fprev[0] = f0[0];
    	for (int j=1; j<pages.length; ++j) {
    		f0[j] = f0[j-1] + pages[j];
        	fprev[j] = f0[j];
    	}
    	for (int i=1; i<k; ++i) {
    		fcurr[i] = Math.max(fprev[i-1], pages[i]);
    		int left = i-1;
    		for (int right=i+1; right<pages.length; ++right) {
				int min = Integer.MAX_VALUE;
				while (left<right) {
					int next = Math.max(fprev[left], (f0[right]-f0[left]));
					if (next < min) min = next;
					else break;
					++left;
				}
				fcurr[right] = min;
    			if (left>=i) --left;
    		}
    		int[] tmp = fprev;
    		fprev = fcurr;
    		fcurr = tmp;
    	}
    	return fprev[pages.length-1];
    }
    /**
     * Binary search: O(nlog(sum / k)) sum是总页数
     * @param pages
     * @param k
     * @return
     */
    public int copyBooks2(int[] pages, int k) {
        // write your code here
    	int sum = 0;
    	for (int page: pages) {
    		sum += page;
    	}
        int l = 0;
        int r = sum;
        while( l <= r){
            int mid = l + (r - l) / 2;
            if(search(mid,pages,k))
                r = mid-1;
            else
                l = mid+1;
        }
        return l;
    }
    public boolean search(int total, int[] page, int k) {
        int count = 0;
        int sum = 0;
        for(int i = 0; i < page.length;) {
            if(sum + page[i] <= total)
                sum += page[i++];
            else if(page[i] <= total){
                sum = 0;
                count++;
            }
            else
                return false;
        }
        //System.out.println(total+":"+sum);
        if(sum != 0)
            count++;
        return count <= k;
    }
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		xCopyBooks solution = new xCopyBooks();

		//5
		System.out.println(solution.copyBooks(new int[]{3,2,4}, 2));
		//1012
		System.out.println(solution.copyBooks(new int[]{13,999,1,2,3,9,11}, 2));
		//3
		System.out.println(solution.copyBooks(new int[]{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}, 6));
		//175852
		System.out.println(solution.copyBooks(new int[]{7819,2380,9542,6257,7194,926,6166,3327,1259,
				7648,3269,4566,4050,3357,8684,6603,3808,2096,9286,9757,7449,8539,1907,5660,4932,2850,
				5784,5858,4659,6991,2522,6345,3971,5031,2067,1830,9476,6800,282,2467,8893,1551,5899,
				5897,8721,1434,5623,5834,1861,856,5512,316,5495,168,2852,5043,6313,3996,1459,5234,6135,
				6493,6834,7097,4980,4428,9889,6020,576,3832,8016,2835,6968,2438,1764,5781,7506,670,1349,
				1023,7612,5603,9542,6617,6849,6883,4413,1229,8216,2169,4911,4067,5328,7564,300,5541,
				7113,5849,2842,3234,679,8883,7871,8671,507,5737,3691,2648,6547,6099,4887,5568,1328,6586,
				6821,7728,2551,5246,9310,5144,870,6506,2292,4225,9022,3365,9706,2146,9784,2721,676,3442,
				686,5660,9800,894,1507,6612,4736,7058,7668,1030,2183,3192,152,9939,3280,8391,4375,7048,
				7494,5847,2964,5797,6451,2106,3941,4608,9529,7041,5004,7245,2709,3246,8076,7270,7073,
				8651,7224,5567,3345,198,1481,1975,7603,9350,1024}, 5));
	}

}
