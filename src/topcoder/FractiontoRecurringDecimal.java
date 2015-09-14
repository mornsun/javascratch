package topcoder;

import java.util.*;

/**
 * Given two integers representing the numerator and denominator of a fraction, return the fraction in string format.

If the fractional part is repeating, enclose the repeating part in parentheses.

For example,

Given numerator = 1, denominator = 2, return "0.5".
Given numerator = 2, denominator = 1, return "2".
Given numerator = 2, denominator = 3, return "0.(6)".
Credits:
Special thanks to @Shangrila for adding this problem and creating all test cases.

Hide Tags Hash Table Math

 * @author Chauncey
 *
 */
public class FractiontoRecurringDecimal
{
    public String fractionToDecimal(int numerator, int denominator) {
    	StringBuilder sb = new StringBuilder();
    	if (numerator<0 && denominator>0 || numerator>0 && denominator<0) sb.append('-');
    	long n = numerator;
    	long d = denominator;
		if (n<0) n = -n;
		if (d<0) d = -d;
    	long divide = n/d;
    	long remaining = (int)(n-divide*d);
    	sb.append(String.valueOf(divide));
    	HashMap<Long, Integer> map = new HashMap<Long, Integer>();
    	if (remaining != 0) {
    		sb.append('.');
        	int i = sb.length();
        	while (remaining != 0) {
        		//System.out.println(n+":"+divide+":"+remaining);
        		Integer last = map.get(remaining);
        		if (last == null) {
        			map.put(remaining, i);
        		} else {
        			sb.insert(last.intValue(), '(');
        			sb.append(')');
        			break;
        		}
        		n = remaining*10;
        		divide = n/d;
        		remaining = (int)(n-divide*d);
        		sb.append(divide);
        		++i;
        	}
    	}
    	return sb.toString();
    }
	    
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		FractiontoRecurringDecimal solution = new FractiontoRecurringDecimal();
		
		//System.out.println(solution.fractionToDecimal(50,-8));
		System.out.println(solution.fractionToDecimal(7,-12));
		//System.out.println(solution.fractionToDecimal(-1, -2147483648));
		
	}

}
