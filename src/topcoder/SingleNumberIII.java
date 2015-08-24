package topcoder;

import java.util.*;

/**
 * Write a program to find the n-th ugly number.

Ugly numbers are positive numbers whose prime factors only include 2, 3, 5. For example, 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 is the sequence of the first 10 ugly numbers.

Note that 1 is typically treated as an ugly number.

Hint:

The naive approach is to call isUgly for every number until you reach the nth one. Most numbers are not ugly. Try to focus your effort on generating only the ugly ones.
An ugly number must be multiplied by either 2, 3, or 5 from a smaller ugly number.
The key is how to maintain the order of the ugly numbers. Try a similar approach of merging from three sorted lists: L1, L2, and L3.
Assume you have Uk, the kth ugly number. Then Uk+1 must be Min(L1 * 2, L2 * 3, L3 * 5).
Credits:
Special thanks to @jianchao.li.fighter for adding this problem and creating all test cases.

Hide Tags Dynamic Programming Heap Math
Hide Similar Problems (H) Merge k Sorted Lists (E) Count Primes (E) Ugly Number


 * @author Chauncey
 *
 */
public class SingleNumberIII
{
    public int nthUglyNumber(int n) {
    	if (n<=1) return 1;
    	HashSet<Long> set = new HashSet<Long>(); //use long to avoid integer overflow
        PriorityQueue<Long> heap =  new PriorityQueue<Long>();
        heap.add((long)1);
        set.add((long)1);
        long[] nexts = new long[3];
        long num = 1;
        for (int i=0; i<n; ++i) {
        	num = heap.poll();
        	set.remove(num);
        	nexts[0] = num * 2;
        	nexts[1] = num * 3;
        	nexts[2] = num * 5;
        	for (long next : nexts) {
        		if (!set.contains(next)) {
        			heap.add(next);
        			set.add(next);
        		}
        	}
        }
        return (int)num;
    }
	    
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		SingleNumberIII solution = new SingleNumberIII();
		
		System.out.println(solution.nthUglyNumber(1600));
	}

}
