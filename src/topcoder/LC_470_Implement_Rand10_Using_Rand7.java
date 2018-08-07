/**
 * 
 */
package topcoder;

import java.util.*;

/**
 * Given a function rand7 which generates a uniform random integer in the range 1 to 7, write a function rand10 which generates a uniform random integer in the range 1 to 10.

Do NOT use system's Math.random().

 

Example 1:

Input: 1
Output: [7]
Example 2:

Input: 2
Output: [8,4]
Example 3:

Input: 3
Output: [8,1,10]
 

Note:

rand7 is predefined.
Each testcase has one argument: n, the number of times that rand10 is called.
 

Follow up:

What is the expected value for the number of calls to rand7() function?
Could you minimize the number of calls to rand7()?

Related Topics 
Random
Rejection Sampling

 * @author Chauncey
 *
 */
public class LC_470_Implement_Rand10_Using_Rand7
{
    public int rand10_1()
    {
    	int a1 = rand7()-1;
    	while (a1 == 6) {
    		a1 = rand7()-1;
    	}
    	int a2 = rand7();
    	if (a1 == 5) {
    		while (a2 > 5) {
    			a2 = rand7();
    		}
    	}
        int n = a1 * 7 + a2;
        return (n % 10) + 1;
    }

    public int rand10()
    {
	    int x = (rand7()-1)*7 + rand7()-1;
	    return x<=39 ? x%10 +1 : rand10();
    }

	private static Random rand = new Random();
    private static int rand7()
    {
    	return rand.nextInt(7) + 1;
    }
        
    /**
     * @param args
     */
    public static void main(String[] args)
    {
        long startTime = System.currentTimeMillis();
        
        LC_470_Implement_Rand10_Using_Rand7 solution = new LC_470_Implement_Rand10_Using_Rand7();
        System.out.println(solution.rand10());
        System.out.println(solution.rand10() + "," + solution.rand10());
        System.out.println(solution.rand10() + "," + solution.rand10() + "," + solution.rand10());
        
        int sum=0;
        for (int i=0; i<100000; ++i) {
        	sum += solution.rand10();
        }
        System.out.println(sum/100000.0);
        
        System.out.println("elapsed:" + (System.currentTimeMillis() - startTime));
    }

}
