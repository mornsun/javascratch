/**
 * 
 */
package topcoder;

/**
 * Divide two integers without using multiplication, division and mod operator.

If it is overflow, return MAX_INT.

Hide Tags Math Binary Search

 * @author Chauncey
 *
 */
public class xDivideTwoIntegers
{
    public int divide(int dividend, int divisor) {
    	if (dividend == 0 || divisor == 0) return 0;
    	boolean positive = true;
        if (dividend<0 && divisor>0 || dividend>0 && divisor<0) positive = false;
        long a = (dividend < 0 ? -(long)dividend : dividend);
        long b = (divisor < 0 ? -(long)divisor : divisor);
        long res = 0;
        while (a >= b) {
        	for (long i=1, multi = b; a >= multi; i<<=1, multi<<=1) {
        		a -= multi;
        		res += i;
        	}
        }
        return positive ? (int)(res>Integer.MAX_VALUE ? Integer.MAX_VALUE : res) : (int)-res;
    }
    /**
     * @param args
     */
    public static void main(String[] args)
    {
    	xDivideTwoIntegers solution = new xDivideTwoIntegers();
    	System.out.println(solution.divide(555, 112));
    	System.out.println(solution.divide(-2147483648, -1));
    }

}
