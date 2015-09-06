package topcoder;

import java.util.*;

/**
 * Write an algorithm that counts the number of ways you can paint a fence with N posts using K colors
 * such that no more than 2 adjacent fence posts are painted with the same color.
 * 
 * @author Chauncey
 *
 */
public class FencePainter
{
    public int numWays(int n, int k) {
    	if (n<=0) return 0;
    	if (n==1) return k;
    	int fsame = k, fdiff = k*(k-1);
    	for (int i=3; i<=n; ++i) {
    		int prev_fdiff = fdiff;
    		fdiff = (fsame+fdiff)*(k-1);
    		fsame = prev_fdiff;
    	}
    	return fsame+fdiff;
    }
    
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		FencePainter solution = new FencePainter();
		
		System.out.println(solution.numWays(4,4));
	}

}
