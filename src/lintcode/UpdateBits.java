package lintcode;

import java.util.*;

/**
 * Given two 32-bit numbers, N and M, and two bit positions, i and j. Write a method to set all bits between i and j in N equal to M (e g , M becomes a substring of N located at i and starting at j)

Have you met this question in a real interview? Yes
Example
Given N=(10000000000)2, M=(10101)2, i=2, j=6

return N=(10001010100)2

Note
In the function, the numbers N and M will given in decimal, you should also return a decimal number.

Challenge
Minimum number of operations?

Clarification
You can assume that the bits j through i have enough space to fit all of M. That is, if M=10011， you can assume that there are at least 5 bits between j and i. You would not, for example, have j=3 and i=2, because M could not fully fit between bit 3 and bit 2.

Tags Expand 
Cracking The Coding Interview Bit Manipulation


Related Problems Expand 
Hard Binary Representation 17 %

 * @author Chauncey
 *
 */
public class UpdateBits
{
    /**
     *@param n, m: Two integer
     *@param i, j: Two bit positions
     *return: An integer
     */
    public int updateBits(int n, int m, int i, int j) {
    	if (i > j) return n;
    	int mask = 0xffffffff;
    	for (int k=i; k<=j; ++k) {
    		mask <<= 1;
    	}
    	for (int k=0; k<i; ++k) {
    		mask <<= 1;
    		mask |= 1;
    		m <<= 1;
    	}
    	return n & mask | m;
    }
    
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		UpdateBits solution = new UpdateBits();

		//10
		System.out.println(Integer.toString(0x400, 2));
		System.out.println(Integer.toString(0x15, 2));
		System.out.println(Integer.toString(solution.updateBits(0x400, 0x15, 2, 6), 2));
	}

}
