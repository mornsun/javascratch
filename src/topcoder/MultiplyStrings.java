package topcoder;

import java.util.*;

/**
 * Given two numbers represented as strings, return multiplication of the numbers as a string.

Note: The numbers can be arbitrarily large and are non-negative.

Hide Tags Math String
Hide Similar Problems (M) Add Two Numbers (E) Plus One (E) Add Binary

 * @author Chauncey
 *
 */
public class MultiplyStrings
{
    public String multiply(String num1, String num2) {
        if (num1 == null || num2==null) return "0";
        num1 = num1.trim();
        num2 = num2.trim();
        if (num1.length() > num2.length()) { //optimize for less plus operations
        	String tmp = num1;
        	num1 = num2;
        	num2 = tmp;
        }
        int l1 = num1.length(), l2 = num2.length();
        if (l1 == 0) return "0";
        byte[] n1 = new byte[l1], n2 = new byte[l2];
        byte[] res = new byte[l1+l2];
        for (int i=0; i<l1; ++i) n1[l1-1-i] = (byte)(num1.charAt(i) - '0');
        for (int i=0; i<l2; ++i) n2[l2-1-i] = (byte)(num2.charAt(i) - '0');
        for (int i=0; i<l1; ++i) {
        	if (n1[i] == 0) continue;
        	int carry = 0;
        	int j = 0;
        	for (; j<l2; ++j) {
        		int product = n1[i]*n2[j]+carry+res[i+j];
        		carry = product/10;
        		res[i+j] = (byte)(product%10);
        	}
        	while (carry != 0) {
        		res[i+j++] = (byte)(carry%10);
        		carry /= 10;
        	}
        }
        StringBuilder sb = new StringBuilder(res.length);
        int n = res.length;
        while (n>0 && res[--n] == 0);
        for (; n>=0; --n) sb.append((char)(res[n]+'0'));
        return sb.toString();
    }
	    
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		MultiplyStrings solution = new MultiplyStrings();
		
		System.out.println(solution.multiply("99", "99"));
	}

}
