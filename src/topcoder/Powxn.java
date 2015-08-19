/**
 * 
 */
package topcoder;

import java.util.*;

/**
 * Implement pow(x, n).

Hide Tags Math Binary Search
Hide Similar Problems (M) Sqrt(x)

 * @author Chauncey
 *
 */
public class Powxn
{
    public static double myPow(double x, int n) {
    	double val = x;
    	double[] pows = new double[32];
    	for (int i = 0; i<pows.length; ++i, val*=val) {
    		pows[i] = val;
    	}
    	val = 1;
    	if (n == 0 || x == 1) {
    		return 1;
    	} else if (n > 0) {
    		int i=0, p=1;
	        while (n != 0) {
	        	if (i<30) {
	        		p <<= 1;
	        		++i;
	        	}
	        	if (n - p < 0) {
		        	do {
		        		p >>= 1;
		        		--i;
		        	} while (n - p < 0);
	        	}
	        	val *= pows[i];
	        	n -= p;
	        }
    	} else {
    		int i=0, p=1;
	        while (n != 0) {
	        	if (i<30) {
	        		p <<= 1;
	        		++i;
	        	}
	        	if (n + p > 0) {
		        	do {
		        		p >>= 1;
		        		--i;
		        	} while (n + p > 0);
	        	}
	        	val /= pows[i];
	        	n += p;
	        }
    	}
        return val;
    }
	    
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		/*System.out.println(row);
		for (int i=0; i< matrix.length; ++i) {
			for (int j=0; j<matrix[i].length; ++j) {
				System.out.print(matrix[i][j]);
			}
			System.out.println();
		}*/
		System.out.println(Integer.MAX_VALUE);
		System.out.println(myPow(2.0,Integer.MAX_VALUE));
	}

}
